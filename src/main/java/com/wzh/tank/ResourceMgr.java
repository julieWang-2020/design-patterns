package com.wzh.tank;

import com.wzh.tank.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {

    public static BufferedImage tankL,tankU,tankR,tankD;
    public static BufferedImage bulletL,bulletU,bulletR,bulletD;

    public static BufferedImage[] explodes=new BufferedImage[16];
    static {
        try {
            tankU= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            tankL= ImageUtils.rotateImage(tankU,-90);
            tankR= ImageUtils.rotateImage(tankU,90);
            tankD= ImageUtils.rotateImage(tankU,180);


            bulletU= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletL=ImageUtils.rotateImage(bulletU,-90);
            bulletR=ImageUtils.rotateImage(bulletU,90);
            bulletD=ImageUtils.rotateImage(bulletU,180);

            for(int i=0;i<16;i++)
                explodes[i]= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
