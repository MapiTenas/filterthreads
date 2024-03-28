package com.svalero.filterthreads.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.svalero.filterthreads.model.HistoryPhoto;

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
            filters = filters.replaceAll("\\[|\\]", "");

            String[] log = { photoName, filters, dateTimeFormatted}; 
            writer.writeNext(log);
            // closing writer connection 
            writer.close(); 
        } 
        catch (IOException e) { 
            e.printStackTrace(); 
        } 
    }
    //We transform the csv entries into a Bean. This allows each entry to be cast as an object in the list.
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<HistoryPhoto> writeHistory() throws FileNotFoundException{
        CSVReader csvReader = new CSVReader(new FileReader(new File(ARCHCSV)));
        CsvToBean<HistoryPhoto> csvBean = new CsvToBeanBuilder(csvReader)
            .withType(HistoryPhoto.class)
            .withSeparator(',')
            .withIgnoreLeadingWhiteSpace(true)
            .withIgnoreEmptyLine(true)
            .build();
        List<HistoryPhoto> historyData = csvBean.parse();    
        return historyData;
    }

    
}
