import java.awt.event.KeyEvent;

public class Player extends GameObject {

    private float speed;
    private int tileX, tileY;
    private float offX, offY;
    private int up, down, left, right;

    public Player(int posX, int posY, boolean wasd, boolean kidnapper) {
        if (kidnapper) {
            this.tag = "kidnapper";
            speed = 90;
        } else {
            this.tag = "Father";
            speed = 100;
        }

        controlLayout(wasd);

        this.tileX = posX;
        this.tileY = posY;
        this.offX = 0;
        this.offY = 0;
        this.posX = posX * GameManager.TS;
        this.posY = posY * GameManager.TS;
        this.width = GameManager.TS;
        this.height = GameManager.TS;
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

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {

        if (gc.getInput().isKey(right)) {
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

        if (gc.getInput().isKey(left)) {
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

        if (gc.getInput().isKey(up)) {
            if (gm.getCollision(tileX,tileY - 1) || gm.getCollision(tileX + (int) Math.signum((int) offX),tileY - 1)) {
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
        }

        if (gc.getInput().isKey(down)) {
            if (gm.getCollision(tileX,tileY + 1) || gm.getCollision(tileX + (int) Math.signum((int) offX),tileY + 1)) {
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
        }



        //final position
        if (offY > GameManager.TS /2) {
            tileY++;
            offY -= GameManager.TS;
        }

        if (offY < -GameManager.TS /2) {
            tileY--;
            offY += GameManager.TS;
        }

        if (offX > GameManager.TS /2) {
            tileX++;
            offX -= GameManager.TS;
        }

        if (offX < -GameManager.TS /2) {
            tileX--;
            offX += GameManager.TS;
        }

        posX = tileX * GameManager.TS + offX;
        posY = tileY * GameManager.TS + offY;
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        int color;
        if (tag.equals("kidnapper")) {
            color = 0xffff0000;
        } else {
            color = 0xff00ff00;
        }
        r.fillRect((int) posX, (int) posY, width - 1, height - 1, color);
    }
}
