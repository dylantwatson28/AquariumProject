import java.awt.*;
    public class Shark {
        public String name;
        public int xpos;
        public int ypos;
        public int dx;
        public int dy;
        public int width;
        public int height;
        public Rectangle hitbox;
        public boolean isAlive;
        public boolean isCrashing;
        //variables

        public Shark(int pXpos, int pYpos){
            xpos = pXpos;
            ypos = pYpos;
            dx = 8;
            dy = 8;
            //how fast it goes
            width = 85;
            height = 85;
            //measurements of object
            isAlive = true;
            isCrashing = false;
            hitbox = new Rectangle (xpos,ypos,width,height);
            //constructor
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