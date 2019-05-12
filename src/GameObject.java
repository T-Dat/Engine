public abstract class GameObject {
    protected float posX, posY;
    protected int width, height;

    public abstract  void update(GameContainer gc, float dt);
    public abstract void render(Renderer r);

    public float getPosX() {
        return posX;
    }

    public void setPosX(float poxX) {
        this.posX = poxX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
