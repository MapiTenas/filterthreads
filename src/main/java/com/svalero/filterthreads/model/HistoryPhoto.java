package com.svalero.filterthreads.model;

import com.opencsv.bean.CsvBindByPosition;

public class HistoryPhoto {
    // Class atributes, binded by position to allow the cast from CSV. 
    @CsvBindByPosition(position = 0)
    private String  photoName;

    @CsvBindByPosition(position = 1)
    private String  filterNames; 

    @CsvBindByPosition(position = 2)
    private String  datePhoto;

    // Class constructor

    public HistoryPhoto() {
    }

    // Getters and setters
    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getFilterNames() {
        return filterNames;
    }

    public void setFilterNames(String filterNames) {
        this.filterNames = filterNames;
    }

    public String getDatePhoto() {
        return datePhoto;
    }

    public void setDatePhoto(String datePhoto) {
        this.datePhoto = datePhoto;
    }
    
}
