public abstract class GameObject {
    protected String tag;
    protected float posX, posY;
    protected int width, height;



    protected boolean dead = false;


    public abstract  void update(GameContainer gc, GameManager2 gm, float dt);
    public abstract void render(GameContainer gc, Renderer r);

    public boolean isDead() {
        return dead;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

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
