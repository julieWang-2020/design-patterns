package com.wzh.tank;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main( String[] args ) throws InterruptedException {
//        Frame f=new Frame();
//        f.setSize(800,600);
//        f.setResizable(false);
//        f.setTitle("tank war");
//        f.setVisible(true);
//
//        f.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//
//                System.exit(0);
//            }
//        });

        TankFrame tf=new TankFrame();
        while (true){
            Thread.sleep(500);
//            tf.x+=200;
            tf.repaint();
        }
    }
}
