public class World {
    public static final int TS = 16;
    private int tileW, tileH;
    private boolean[] collisions;
    private Player father;
    private Player kidnapper;


    public World(int tileW, int tileH) {
        this.tileW = tileW;
        this.tileH = tileH;
        //todo add tile class
        collisions = new boolean[tileW * tileH];
        //todo: place players in position
        kidnapper = new Player(1, 2, true, this);
        father = new Player(8, 6, false, this);
    }

    public boolean getCollision(int x, int y) {
        if (x < 0 || x >= tileW || y < 0 || y >= tileH) {
            return true;
        }
        return collisions[x + y * tileW];
    }

    //todo fix colision
    public boolean getPlayerCollision() {
        return Math.abs(father.getPosX() - kidnapper.getPosX()) < 10 && Math.abs(father.getPosY() - kidnapper.getPosY()) < 10;
    }

    //todo use bayesian generator
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

}
