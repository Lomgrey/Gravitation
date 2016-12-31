package Collision;


import javafx.scene.canvas.GraphicsContext;

public class Entity {

    public int x, y, w, h;

    public Entity(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void draw(GraphicsContext g){
        g.strokeRect(x, y, w, h);
    }

    public BBox bbox(){
        return new BBox(x, x + w, y, y + h);
    }

    public boolean isColliding(Entity other){
        return bbox().isColliding(other.bbox());
    }

    public boolean isDounColliding(Entity other){
        return bbox().isDownColliding(other.bbox());
    }
}
