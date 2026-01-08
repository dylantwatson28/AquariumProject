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
    public Image backgroundPic;



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
        SharkPic = Toolkit.getDefaultToolkit().getImage("Shark.jpg");
        ShrimpPic = Toolkit.getDefaultToolkit().getImage("Shrimp.jpg");
        backgroundPic = Toolkit.getDefaultToolkit().getImage("water.jpg");
        fish1 = new Fish(WIDTH / 2, HEIGHT / 2);
        //FishPic = new Fish(randx, randy);
        shark1 = new Shark(100, 100);
        shark1.dx = -shark1.dx;
        shrimp1 = new Shrimp(200,200);




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
        Fish.move();
        Shark.move();
        Shrimp.move();

    }
    public void crashing(){
        if (fish1.hitbox.intersects(shark1.hitbox) && shark1.isCrashing == false){
            System.out.println("explode!");
            shark1.height += 50;
            //astroid1.height = astroid1.height + 50; another option
            shark1.isCrashing = true;
            shark1.dy = -shark1.dy;
            shark1.dy = -shark1.dy;
            fish1.isAlive = false;
        }
        if (shark1.hitbox.intersects(shark1.hitbox)){
            System.out.println("no intersection");
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
        g.drawImage(FishPic, Fish.xpos,Fish.ypos,Fish.WIDTH,Fish.HEIGHT,null);
        g.drawImage(SharkPic, Shark.xpos,Shark.ypos,Shark.WIDTH,Shark.HEIGHT,null);
        g.drawImage(ShrimpPic,Shrimp.xpos,Shrimp.ypos,Shrimp.WIDTH,Shrimp.HEIGHT,null);

        g.dispose();

        bufferStrategy.show();
    }
}