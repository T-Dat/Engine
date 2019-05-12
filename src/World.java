public class World {
    public static final int TS = 16;
    protected int tileW, tileH;
    private boolean[] collisions;
    private Player father;
    private Player kidnapper;


    public World(int tileW, int tileH) {
        this.tileW = tileW;
        this.tileH = tileH;
        collisions = new boolean[tileW * tileH];
        kidnapper = new Player(3, 2, true, this);
        father = new Player(8, 6, false, this);
    }

    //TODO: move that to renderer
    public void render(GameContainer gc, Renderer r) {
        for (int y = 0; y < tileH; y++) {
            for (int x = 0; x < tileW; x++) {
                if (collisions[x + y * tileW]) {
                    r.fillRect(x * TS, y * TS, TS, TS, 0xff0f0f0f);
                } else {
                    r.fillRect(x * TS, y * TS, TS, TS, 0xfff9f9f9);
                }
            }

        }
        if (getPlayerCollision()) {
            gc.getRenderer().drawText("Finished!!!", gc.getWidth() / 2, gc.getHeight() / 2, 0xff0000ff);
        }
    }

    public boolean getCollision(int x, int y) {
        if (x < 0 || x >= tileW || y < 0 || y >= tileH) {
            return true;
        }
        return collisions[x + y * tileW];
    }

    public boolean getPlayerCollision() {
        return Math.abs(father.posX - kidnapper.posX) < 10 && Math.abs(father.posY - kidnapper.posY) < 10;
    }

    public void createLevel() {
        for (int i = 0; i < collisions.length; i++) {
            collisions[i] = false;
        }
        collisions[5] = true;
        collisions[8] = true;
        collisions[20] = true;
        collisions[100] = true;
        collisions[160] = true;
        collisions[250] = true;
        for (int i = 0; i < tileW; i++) {
            collisions[i] = true;
            collisions[280 + i] = true;
        }
        for (int i = 0; i < tileH; i++) {
            collisions[i * tileW + 19] = true;
            collisions[i * tileW] = true;
        }

    }

    public int getTileW() {
        return tileW;
    }

    public int getTileH() {
        return tileH;
    }

    public Player getFather() {
        return father;
    }

    public Player getKidnapper() {
        return kidnapper;
    }

    public boolean[] getCollisions() {
        return collisions;
    }
}
