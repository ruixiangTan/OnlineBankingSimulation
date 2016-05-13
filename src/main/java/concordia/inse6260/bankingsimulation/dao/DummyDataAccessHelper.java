package concordia.inse6260.bankingsimulation.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ruixiang on 11/4/2015.
 */
public final class DummyDataAccessHelper {

    public static String nameFilePath = "src/main/resources/data/dummyNames.txt";
    //public static String addressFilePath = "src/main/resources/data/dummyAddresses.txt";
    public static String emailFilePath = "src/main/resources/data/dummyEmails.txt";

    public static String generateRandomItem(String filePath) {
        File file = new File(filePath);
        ArrayList<String> list = new ArrayList<>();
        BufferedReader br = null;
        String line;
        try {

            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null)
                list.add(line);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Random randomIndex = new Random();

        return list.get(randomIndex.nextInt(30));
    }

}
