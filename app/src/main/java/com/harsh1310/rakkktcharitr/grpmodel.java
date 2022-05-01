package com.harsh1310.rakkktcharitr;

public class grpmodel {
    private String grpname;
    private int mFlagImage;

    public grpmodel(String countryName, int flagImage) {
        grpname = countryName;
        mFlagImage = flagImage;
    }

    public String getCountryName() {
        return grpname;
    }

    public int getFlagImage() {
        return mFlagImage;
    }
}
