package com.vsplc.weather.web_service.model;

import com.google.gson.annotations.Expose;

public class Sys {

    @Expose
    private Double type;
    @Expose
    private Double id;
    @Expose
    private Double message;
    @Expose
    private String country;
    @Expose
    private Double sunrise;
    @Expose
    private Double sunset;

    /**
     * @return The type
     */
    public Double getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(Double type) {
        this.type = type;
    }

    /**
     * @return The id
     */
    public Double getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Double id) {
        this.id = id;
    }

    /**
     * @return The message
     */
    public Double getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(Double message) {
        this.message = message;
    }

    /**
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return The sunrise
     */
    public Double getSunrise() {
        return sunrise;
    }

    /**
     * @param sunrise The sunrise
     */
    public void setSunrise(Double sunrise) {
        this.sunrise = sunrise;
    }

    /**
     * @return The sunset
     */
    public Double getSunset() {
        return sunset;
    }

    /**
     * @param sunset The sunset
     */
    public void setSunset(Double sunset) {
        this.sunset = sunset;
    }
}
