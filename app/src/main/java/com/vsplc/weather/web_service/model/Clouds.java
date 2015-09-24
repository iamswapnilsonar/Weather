package com.vsplc.weather.web_service.model;

import com.google.gson.annotations.Expose;

public class Clouds {

    @Expose
    private Double all;

    /**
     * @return The all
     */
    public Double getAll() {
        return all;
    }

    /**
     * @param all The all
     */
    public void setAll(Double all) {
        this.all = all;
    }
    
}
