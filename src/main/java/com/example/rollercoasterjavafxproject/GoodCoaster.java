package com.example.rollercoasterjavafxproject;
import java.io.*;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Scanner;

public class GoodCoaster extends RollerCoaster implements Serializable {
    private static final long serialVerionUID = 1L;
    private float rating;
    private String country;
    private String manufacturer;
    private int duels;
    transient public Image goodCoasterImage;



    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getDuels() {
        return duels;
    }

    public void setDuels(int duels) {
        this.duels = duels;
    }

    public GoodCoaster(String name, String park, int rank, float rating, String country, String manufacturer, int duels) {
        super(name, park, rank);
        this.rating = rating;
        this.country = country;
        this.manufacturer = manufacturer;
        this.duels = duels;

        goodCoasters.add(this);
    }

    public static ArrayList<GoodCoaster> goodCoasters = new ArrayList<>();

    public static ArrayList<GoodCoaster> getGoodCoasters() {
        return goodCoasters;
    }

    public static void setGoodCoasters(ArrayList<GoodCoaster> goodCoasters) {
        GoodCoaster.goodCoasters = goodCoasters;
    }

    public static void readGoodData() throws Exception{
        File coasterData = new File("GoodCoasterData");
        Scanner dataScanner = new Scanner(coasterData);
        String data = "";
        Scanner lineScanner = new Scanner(data);


        while(dataScanner.hasNextLine()){

            data = dataScanner.nextLine();
            String name = data;

            data = dataScanner.nextLine();

            int rankEnds = data.indexOf(" ");
            int rank = Integer.parseInt((data.substring(0,rankEnds+1)).trim());
            data = dataScanner.nextLine();
            String park =data;
            data = dataScanner.nextLine();
            String country = data;
            data = dataScanner.nextLine();
            String manufacturer = data;
            data = dataScanner.nextLine();
            String ratingString = data;
            ratingString = ratingString.replace(",",".");
            ratingString = ratingString.replace("%","");
            float rating = Float.parseFloat(ratingString);
            data = dataScanner.nextLine();
            data = data.replace(",","");
            data = data.replace(" duels","");
            int duels = Integer.parseInt(data);

            new GoodCoaster(name,park,rank,rating,country,manufacturer,duels);
        }



    }

    public String toString(){
        return rank+": "+name ;
    }

    static void saveData() throws Exception{
        FileOutputStream filesOut = new FileOutputStream("goodObjects");
        ObjectOutputStream objectsOut = new ObjectOutputStream(filesOut);
        objectsOut.writeObject(goodCoasters);
        filesOut.close();
        objectsOut.close();
    }

    static void restoreData() throws Exception{
        FileInputStream filesIn = new FileInputStream("goodObjects");
        ObjectInputStream objectsIn = new ObjectInputStream(filesIn);
        goodCoasters = (ArrayList<GoodCoaster>) objectsIn.readObject();
        objectsIn.close();
        filesIn.close();
    }



    @Serial
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
// read NON-transient fields
        s.defaultReadObject();
        // read transient fields
        Image maybeImage = null;
        try {
            maybeImage = SwingFXUtils.toFXImage(ImageIO.read(s), null);
        } catch (Exception ex) {
            // do nothing
        }
        goodCoasterImage = maybeImage;
    }

    // implements custom serialize for
    //     transient posterImage field
    @Serial
    private void writeObject(ObjectOutputStream s) throws IOException {
        // write NON-transient fields
        s.defaultWriteObject();
        // write transient fields
        if (goodCoasterImage != null) {
            ImageIO.write(SwingFXUtils.fromFXImage(goodCoasterImage, null), "png", s);
        }
    }


}
