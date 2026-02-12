import java.awt.*;

public class sharkwin {
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
    //variables

    public sharkwin(int pXpos, int pYpos){
        xpos = pXpos;
        ypos = pYpos;
        dx = 0;
        dy = 0;
        //how fast it goes
        width = 400;
        height = 400;
        isAlive = false;
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
