package Collision;

//Bounding Box
public class BBox {

    private int minX, maxX, minY, maxY;

    public BBox(int minX, int maxX, int minY, int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public boolean isColliding(BBox other){
        return maxX >= other.minX && minX <= other.maxX
                && maxY >= other.minY && minY <= other.maxY;
    }

    public boolean isDownColliding(BBox other){
        return maxY >= other.minY &&
                maxX >= other.minX && minX <= other.maxX;
    }
}
