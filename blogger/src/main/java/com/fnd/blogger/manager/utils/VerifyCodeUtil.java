package com.fnd.blogger.manager.utils;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import javax.imageio.ImageIO;

public class VerifyCodeUtil {
    public static final String VERIFY_CODES = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    /**
     * 获取随机颜色值
     * @param minColor
     * @param maxColor
     * @return
     */
    private static Color getRandomColor(int minColor, int maxColor) {

        Random random = new Random();
        // 保存minColor最大不会超过255
        if (minColor > 255)
            minColor = 255;
        // 保存minColor最大不会超过255
        if (maxColor > 255)
            maxColor = 255;
        // 获得红色的随机颜色值
        int red = minColor + random.nextInt(maxColor - minColor);
        // 获得绿色的随机颜色值
        int green = minColor + random.nextInt(maxColor - minColor);
        // 获得蓝色的随机颜色值
        int blue = minColor + random.nextInt(maxColor - minColor);
        return new Color(red, green, blue);

    }

    /**
     * 使用指定源生成验证码
     * @param verifySize    验证码长度
     * @return
     */
    public static String generateVerifyCode(int verifySize){
        String sources = VERIFY_CODES;
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for(int i = 0; i < verifySize; i++){
            verifyCode.append(sources.charAt(rand.nextInt(codesLen-1)));
        }
        return verifyCode.toString();
    }

    /**
     * 随机生成字体、文字大小
     * @return
     */
    public static Font getRandomFont() {
        String[] fonts = {"Georgia", "Verdana", "Arial", "Tahoma", "Time News Roman", "Courier New", "Arial Black", "Quantzite"};
        int fontIndex = (int)Math.round(Math.random() * (fonts.length - 1));
        int fontSize = (int) Math.round(Math.random() * 4 + 16);
        return new Font(fonts[fontIndex], Font.PLAIN, fontSize);
    }

    public static void outputImage(int w, int h, OutputStream os, String code) throws IOException {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();// 获得用于输出文字的Graphics对象
        Random random = new Random();
        g.setColor(getRandomColor(180, 250));// 随机设置要填充的颜色
        g.fillRect(0, 0, w, h);// 填充图形背景
        // 设置初始字体
        g.setFont(new Font("Times New Roman", Font.ITALIC, h));
        g.setColor(getRandomColor(120, 180));// 随机设置字体颜色

        //干扰线
        for(int i=0;i<155;i++){
            int x=random.nextInt(w);
            int y=random.nextInt(h);
            int x1=random.nextInt(w);
            int y1=random.nextInt(h);
            g.drawLine(x, y, x+x1, y+y1);
        }
        String[] codes=code.split("");
        for (int i = 0; i < codes.length; i++) {
            //随机生成字体和字体的大小
            g.setFont(getRandomFont());
            //随机生成字体的颜色
            g.setColor(getRandomColor(10, 100));
            // 在图形上输出验证码字符，x和y都是随机生成的
            g.drawString(codes[i], 14 * i + random.nextInt(7), h - random.nextInt(5));
        }
        g.dispose();
        ImageIO.write(image, "jpg", os);
    }
}
