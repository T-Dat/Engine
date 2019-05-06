public abstract class AbstractGame {
    public static final int TS = 16;
    protected int tileW, tileH;
    public abstract void update(GameContainer gc, float dt);
    public abstract void render(GameContainer gc, Renderer r);

    public static int getTS() {
        return TS;
    }

    public int getTileW() {
        return tileW;
    }

    public int getTileH() {
        return tileH;
    }
}
