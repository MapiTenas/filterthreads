package com.svalero.filterthreads.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.opencsv.CSVWriter;

public class HistoryUtils {

    private final static String ARCHCSV = "src\\main\\resources\\com\\svalero\\filterthreads\\filterHistory.csv";

    public static void saveHistory(String photoName, String filters){
        // Open history file from resources
        File file = new File(ARCHCSV); 
        try { 
            // create FileWriter & CSVWriter objects with history file
            FileWriter outputfile = new FileWriter(file, true); 
            CSVWriter writer = new CSVWriter(outputfile); 
            // add history log to csv
            Calendar now = Calendar.getInstance();
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTimeFormatted = dateTimeFormat.format(now.getTime());
            String[] log = { photoName, filters, dateTimeFormatted}; 
            writer.writeNext(log);
            // closing writer connection 
            writer.close(); 
        } 
        catch (IOException e) { 
            e.printStackTrace(); 
        } 
    }

    
}
