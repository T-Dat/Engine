import java.awt.event.KeyEvent;

public class Player extends GameObject {

    private float speed;
    private int tileX, tileY;
    private float offX, offY;
    private int up, down, left, right;
    private boolean kidnapper;
    private World world;

    public Player(int posX, int posY, boolean wasd, boolean kidnapper, World world) {
        this.world = world;
        this.kidnapper = kidnapper;
        speed = kidnapper ? 90: 100;

        controlLayout(wasd);

        this.tileX = posX;
        this.tileY = posY;
        this.offX = 0;
        this.offY = 0;
        this.posX = posX * World.TS;
        this.posY = posY * World.TS;
        this.width = World.TS;
        this.height = World.TS;
    }

    private void controlLayout(boolean wasd) {
        if (wasd) {
            up = KeyEvent.VK_W;
            down = KeyEvent.VK_S;
            left = KeyEvent.VK_A;
            right = KeyEvent.VK_D;
        } else {
            up = KeyEvent.VK_UP;
            down = KeyEvent.VK_DOWN;
            left = KeyEvent.VK_LEFT;
            right = KeyEvent.VK_RIGHT;
        }
    }

    public void moveLeft(float dt) {
        if (world.getCollision(tileX - 1,tileY) || world.getCollision(tileX - 1,tileY + (int) Math.signum((int) offY))) {
            if (offX > 0) {
                offX -= dt*speed;
                if (offX <0) {
                    offX = 0;
                }
            } else {
                offX = 0;
            }
        } else {
            offX -= dt*speed;
        }
        if (offX < -World.TS /2) {
            tileX--;
            offX += World.TS;
        }
        posX = tileX * World.TS + offX;
    }

    public void moveRight(float dt) {
        if (world.getCollision(tileX + 1,tileY) || world.getCollision(tileX + 1,tileY + (int) Math.signum((int) offY))) {
            if (offX < 0) {
                offX += dt*speed;
                if (offX > 0) {
                    offX = 0;
                }
            } else {
                offX = 0;
            }
        } else {
            offX += dt*speed;
        }
        if (offX > World.TS /2) {
            tileX++;
            offX -= World.TS;
        }
        posX = tileX * World.TS + offX;
    }

    public void moveUp(float dt) {
        if (world.getCollision(tileX,tileY - 1) || world.getCollision(tileX + (int) Math.signum((int) offX),tileY - 1)) {
            if (offY > 0) {
                offY -= dt*speed;
                if (offY < 0) {
                    offY = 0;
                }
            } else {
                offY = 0;
            }
        } else {
            offY -= dt*speed;
        }
        if (offY < -World.TS /2) {
            tileY--;
            offY += World.TS;
        }
        posY = tileY * World.TS + offY;
    }

    public void moveDown(float dt) {
        if (world.getCollision(tileX,tileY + 1) || world.getCollision(tileX + (int) Math.signum((int) offX),tileY + 1)) {
            if (offY < 0) {
                offY += dt*speed;
                if (offY > 0) {
                    offY = 0;
                }
            } else {
                offY = 0;
            }
        } else {
            offY += dt*speed;
        }
        if (offY > World.TS /2) {
            tileY++;
            offY -= World.TS;
        }
        posY = tileY * World.TS + offY;
    }

    @Override
    public void update(GameContainer gc, float dt) {

        if (gc.getInput().isKey(right)) {
            moveRight(dt);
        }

        if (gc.getInput().isKey(left)) {
            moveLeft(dt);
        }

        if (gc.getInput().isKey(up)) {
            moveUp(dt);
        }

        if (gc.getInput().isKey(down)) {
            moveDown(dt);
        }
    }

    @Override
    public void render(Renderer r) {
        int color = kidnapper ? 0xffff0000: 0xff00ff00;
        r.fillRect((int) posX, (int) posY, width - 1, height - 1, color);
    }
}
