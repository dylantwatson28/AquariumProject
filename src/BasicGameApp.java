import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class BasicGameApp implements Runnable {
    final int WIDTH = 1000;
    final int HEIGHT = 700;


    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;
    private Image FishPic;
    private Fish fish1;
    private Image SharkPic;
    private Shark shark1;
    private Image ShrimpPic;
    private Shrimp shrimp1;
    private Image WaterPic;
    public Image backgroundPic;
    public Image sharkwinpic;


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {
        //
        setUpGraphics();
        //(int)(Math.random() * range);
        //range is 0-9
        //(int)(Math.random() * range) + start;
        // akes range 1-10
        int randx = (int) (Math.random() * 10) + 1;
        // make range 1 - 999
        randx = (int) (Math.random() * 999) + 1;
        int randy = (int) (Math.random() * 699) + 1;

        //variable and objects
        //create (construct) the objects needed for the game and load up
        FishPic = Toolkit.getDefaultToolkit().getImage("Fish.png");//load the picture
        SharkPic = Toolkit.getDefaultToolkit().getImage("Shark.png");
        ShrimpPic = Toolkit.getDefaultToolkit().getImage("Shrimp.png");
        backgroundPic = Toolkit.getDefaultToolkit().getImage("Water.png");
        sharkwinpic = Toolkit.getDefaultToolkit().getImage("shark win cs.jpeg");
        fish1 = new Fish(WIDTH/2, HEIGHT/2);
        //FishPic = new Fish(randx, randy);
        shark1 = new Shark(randx, randy);
        shark1.dx = -shark1.dx;
        randx = (int) (Math.random() * 999) + 1;
        shrimp1 = new Shrimp(randx, randy);


    }// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {

        //for the moment we will loop things forever.
        while (true) {

            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(20); // sleep for 10 ms
        }
    }


    public void moveThings() {
        //calls the move( ) code in the objects
        fish1.move();
        shark1.move();
        shrimp1.move();
        crashing();

    }

    public void crashing() {
        if (shark1.hitbox.intersects(fish1.hitbox) && shark1.isCrashing == false && fish1.isAlive == true && shark1.isAlive == true) {
            System.out.println("chomp!");
            shark1.height += 15;
            //shark expands after intersection with fish
            //shark1.height = shark1.height + 50; (another option)
            shark1.isCrashing = true;
            shark1.dy = -shark1.dy;
            fish1.dy = -fish1.dy;
            fish1.isAlive = false;
        }
        if (!shark1.hitbox.intersects(fish1.hitbox)) {
//            System.out.println("intersection");
            shark1.isCrashing = false;
        }
        if (fish1.hitbox.intersects(shrimp1.hitbox)&& shrimp1.isCrashing == false && shrimp1.isAlive == true && fish1.isAlive == true){
            System.out.println("crunch!");
            fish1.height += 10;
            //fish expands after intersecting with shrimp
            shrimp1.isCrashing = true;
            //fish1.dy = -fish1.dy;
            //shrimp1.dy = -shrimp1.dy;
            shrimp1.isAlive = false;
        }
        if (!fish1.hitbox.intersects(shrimp1.hitbox)){
//            System.out.println("intersection!!");
            shrimp1.isCrashing = false;
        }
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");


    }
//


    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        //draw the image of fish
//        System.out.println(FishPic.toString());
        g.drawImage(backgroundPic,0,0,WIDTH,HEIGHT,null);
        g.drawImage(sharkwinpic,0,0, WIDTH,HEIGHT,null);
        g.setColor(Color.GREEN);
        g.drawRect(shark1.hitbox.x, shark1.hitbox.y, shark1.hitbox.width, shark1.hitbox.height);
        g.drawRect(fish1.hitbox.x, fish1.hitbox.y, fish1.hitbox.width, fish1.hitbox.height);

        if (fish1.isAlive == true){
        g.drawImage(FishPic, fish1.xpos, fish1.ypos, fish1.width, fish1.height, null);
        }
        g.drawImage(SharkPic, shark1.xpos, shark1.ypos, shark1.width, shark1.height, null);

        if (shrimp1.isAlive == true){
            g.drawImage(ShrimpPic, shrimp1.xpos, shrimp1.ypos, shrimp1.width, shrimp1.height, null);
            //makes shrimp able to die
        }


        g.dispose();

        bufferStrategy.show();
    }
}