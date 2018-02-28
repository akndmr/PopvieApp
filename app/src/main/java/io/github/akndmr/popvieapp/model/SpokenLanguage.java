package io.github.akndmr.popvieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AKIN on 25.02.2018.
 */

public class SpokenLanguage implements Parcelable {

    public static final Parcelable.Creator<SpokenLanguage> CREATOR = new Parcelable.Creator<SpokenLanguage>() {
        @Override
        public SpokenLanguage createFromParcel(Parcel source) {
            return new SpokenLanguage(source);
        }

        @Override
        public SpokenLanguage[] newArray(int size) {
            return new SpokenLanguage[size];
        }
    };
    @SerializedName("iso_639_1")
    @Expose
    private String iso6391;
    @SerializedName("name")
    @Expose
    private String name;

    public SpokenLanguage() {
    }

    protected SpokenLanguage(Parcel in) {
        this.iso6391 = in.readString();
        this.name = in.readString();
    }

    public String getIso6391() {
        return iso6391;
    }


    /*//////////////////////
    //All about parcelable//
    /////////////////////*/

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.iso6391);
        dest.writeString(this.name);
    }
}