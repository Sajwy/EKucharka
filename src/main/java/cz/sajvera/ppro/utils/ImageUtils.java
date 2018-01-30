package cz.sajvera.ppro.utils;

import cz.sajvera.ppro.model.Fotka;
import org.primefaces.model.UploadedFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ImageUtils {

    private static final String UPLOAD_CESTA = "C:\\Users\\Miroslav\\IdeaProjects\\EKucharka\\src\\main\\webapp\\upload\\";
    private static final String DB_CESTA = "/upload/";

    public static Fotka vytvorFotku(UploadedFile uploadedFile) {
        Fotka fotka = null;
        try {
            BufferedImage inputImage = ImageIO.read(uploadedFile.getInputstream());

            int wM, wS, wV, hM, hS, hV;
            wM = wS = wV = inputImage.getWidth();
            hM = hS = hV = inputImage.getHeight();

            int max_width_Maly = 130;
            int max_height_Maly = 94;
            int max_width_Stredni = 250;
            int max_height_Stredni = 200;
            int max_width_Velky = 1024;

            //pro maly
            hM = (hM * max_width_Maly) / wM;
            wM = max_width_Maly;

            if (hM > max_height_Maly)
            {
                wM = (wM * max_height_Maly) / hM;
                hM = max_height_Maly;
            }

            //pro stredni
            hS = (hS * max_width_Stredni) / wS;
            wS = max_width_Stredni;

            if (hS > max_height_Stredni)
            {
                wS = (wS * max_height_Stredni) / hS;
                hS = max_height_Stredni;
            }

            //pro velky
            hV = (hV * max_width_Velky) / wV;
            wV = max_width_Velky;

            String pripona = uploadedFile.getFileName().substring(uploadedFile.getFileName().lastIndexOf(".") + 1);
            String cas = new Date().getTime() + "";
            String fileNameVelky = "VObr" + cas + "." + pripona;
            String fileNameStredni = "SObr" + cas + "." + pripona;
            String fileNameMaly = "MObr" + cas + "." + pripona;

            BufferedImage outputImageMaly = new BufferedImage(wM, hM, inputImage.TYPE_INT_RGB);
            BufferedImage outputImageStredni = new BufferedImage(wS, hS, BufferedImage.TYPE_INT_RGB);
            BufferedImage outputImageVelky = new BufferedImage(wV, hV, BufferedImage.TYPE_INT_RGB);

            Graphics2D g2dMaly = outputImageMaly.createGraphics();
            g2dMaly.drawImage(inputImage, 0, 0, wM, hM, null);
            g2dMaly.dispose();

            Graphics2D g2dStredni = outputImageStredni.createGraphics();
            g2dStredni.drawImage(inputImage, 0, 0, wS, hS, null);
            g2dStredni.dispose();

            Graphics2D g2dVelky = outputImageVelky.createGraphics();
            g2dVelky.drawImage(inputImage, 0, 0, wV, hV, null);
            g2dVelky.dispose();

            ImageIO.write(outputImageMaly, pripona, new File(UPLOAD_CESTA + fileNameMaly));
            ImageIO.write(outputImageStredni, pripona, new File(UPLOAD_CESTA + fileNameStredni));
            ImageIO.write(outputImageVelky, pripona, new File(UPLOAD_CESTA + fileNameVelky));

            fotka = new Fotka(DB_CESTA + fileNameMaly, DB_CESTA + fileNameStredni, DB_CESTA + fileNameVelky);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fotka;
    }

    public static void smazFotku(Fotka fotka) {
        String nazevMala = fotka.getMala().substring(fotka.getMala().lastIndexOf("/") + 1);
        String nazevStredni = fotka.getStredni().substring(fotka.getStredni().lastIndexOf("/") + 1);;
        String nazevVelka = fotka.getVelka().substring(fotka.getVelka().lastIndexOf("/") + 1);;

        File mala = new File(UPLOAD_CESTA + nazevMala);
        File stredni = new File(UPLOAD_CESTA + nazevStredni);
        File velka = new File(UPLOAD_CESTA + nazevVelka);

        if(mala.exists()) {
            mala.delete();
            stredni.delete();
            velka.delete();
        }
    }

}
