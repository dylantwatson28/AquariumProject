import java.awt.*;
    public class Fish {
        public String name;
        public int xpos;
        public int ypos;
        public int dx;
        public int dy;
        public int width;
        public int height;
        public boolean isAlive;
        public Rectangle hitbox;
        public boolean isCrashing;

        public Fish(int pXpos, int pYpos){
            xpos = pXpos;
            ypos = pYpos;
            dx = 8;
            dy = 8;
            width = 60;
            height = 60;
            isAlive = true;
            isCrashing = false;
            hitbox = new Rectangle(xpos,ypos,width,height);
        }
        public void move(){
            if(xpos>=1000 - width){
                dx=-dx;
            }
            if(xpos<=0){
                dx=-dx;
            }
            if(ypos>=700){
                dy=-dy;
            }
            if(ypos<=0){
                dy=-dy;
            }
            xpos=xpos+dx;
            ypos=ypos+dy;
            hitbox = new Rectangle(xpos,ypos,width,height);
        }
    }
