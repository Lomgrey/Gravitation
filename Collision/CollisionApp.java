package Collision;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class CollisionApp extends Application {

    private final int W = 800;
    private final int H = 600;

    private final int MOVE = 16;
    private final int GRAVITY = 3;

    private GraphicsContext g;
    private AnimationTimer timer;

    //drawing figures
    private Entity rect;
    private Entity floor;

    private Parent createContent(){
        Pane root = new Pane();

        Canvas canvas = new Canvas(W, H);
        g = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        rect = new Entity(375, 100, 50, 50);
        floor = new Entity(200, 400, 400, 20);

//        render();

        return root;
    }

    private void onUpdate(){
        gravitation(rect);
        render();
    }

    private void render(){
        g.clearRect(0 , 0 ,W, H);
        checkState();

        floor.draw(g);
        rect.draw(g);

        g.strokeText(rect.isColliding(floor) ? "Collision" : "NO  Collision", 600, 50);
    }

    private void checkState(){
        if(rect.isDounColliding(floor))
            move(KeyCode.W, GRAVITY);
    }

    private void gravitation(Entity entity){
        move(KeyCode.S, GRAVITY);
    }

    private void move(KeyCode e, int move){
        switch (e){
            case W: rect.y -= move;
                break;
            case S: rect.y += move;
                break;
            case A: rect.x -= move;
                break;
            case D: rect.x += move;
                break;
            default:
                break;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());

        scene.setOnKeyPressed(e ->{
            move(e.getCode(), MOVE);
            if(rect.isDounColliding(floor))
                move(e.getCode(), -MOVE);

            render();
        });


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
