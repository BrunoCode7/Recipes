package com.example.android.recipes.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Baraa Hesham on 6/8/2018.
 */
public class Steps implements Parcelable{
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public Steps(){ }

    public Steps(String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }


    public Steps(Parcel parcel){
        this.description=parcel.readString();
        this.shortDescription=parcel.readString();
        this.thumbnailURL=parcel.readString();
        this.videoURL=parcel.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeString(shortDescription);
        parcel.writeString(thumbnailURL);
        parcel.writeString(videoURL);
    }
    public static Creator<Steps> CREATOR=new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel parcel) {
            return new Steps(parcel);
        }

        @Override
        public Steps[] newArray(int i) {
            return new Steps[i];
        }
    };
}
