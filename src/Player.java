import java.awt.event.KeyEvent;

public class Player extends GameObject {

    private float speed = 100;
    private int tileX, tileY;
    private float offX, offY;

    private float fallDistance = 0;
    private float fallSpeed = 10;
    private float jump = -5;
    private boolean ground = false;


    public Player(int posX, int posY) {
        this.tag = "player";
        this.tileX = posX;
        this.tileY = posY;
        this.offX = 0;
        this.offY = 0;
        this.posX = posX * GameManager2.TS;
        this.posY = posY * GameManager2.TS;
        this.width = GameManager2.TS;
        this.height = GameManager2.TS;
    }

    @Override
    public void update(GameContainer gc, GameManager2 gm, float dt) {


        if (gc.getInput().isKey(KeyEvent.VK_D)) {
            if (gm.getCollision(tileX + 1,tileY) || gm.getCollision(tileX + 1,tileY + (int) Math.signum((int) offY))) {
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
        }

        if (gc.getInput().isKey(KeyEvent.VK_A)) {
            if (gm.getCollision(tileX - 1,tileY) || gm.getCollision(tileX - 1,tileY + (int) Math.signum((int) offY))) {
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
        }

        fallDistance += dt*fallSpeed;


        if (gc.getInput().isKey(KeyEvent.VK_W) && ground) {
            fallDistance = jump;
            ground = false;
        }
        offY += fallDistance;

        if (fallDistance < 0) {
            if ((gm.getCollision(tileX + (int) Math.signum((int) offX), tileY - 1) || gm.getCollision(tileX, tileY - 1)) && offY < 0) {
                fallDistance = 0;
                offY = 0;
            }
        }


        if (fallDistance > 0) {
            if ((gm.getCollision(tileX + (int) Math.signum((int) offX), tileY + 1) || gm.getCollision(tileX, tileY + 1)) && offY > 0) {
                fallDistance = 0;
                offY = 0;
                ground = true;
            }
        }


        //final position
        if (offY > GameManager2.TS /2) {
            tileY++;
            offY -= GameManager2.TS;
        }

        if (offY < -GameManager2.TS /2) {
            tileY--;
            offY += GameManager2.TS;
        }

        if (offX > GameManager2.TS /2) {
            tileX++;
            offX -= GameManager2.TS;
        }

        if (offX < -GameManager2.TS /2) {
            tileX--;
            offX += GameManager2.TS;
        }

        posX = tileX * GameManager2.TS + offX;
        posY = tileY * GameManager2.TS + offY;
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.fillRect((int) posX, (int) posY, width - 1, height - 1, 0xff00ff00);
    }
}
