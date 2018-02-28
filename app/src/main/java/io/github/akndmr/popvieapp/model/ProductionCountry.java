package io.github.akndmr.popvieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AKIN on 25.02.2018.
 */

public class ProductionCountry implements Parcelable {

    public static final Parcelable.Creator<ProductionCountry> CREATOR = new Parcelable.Creator<ProductionCountry>() {
        @Override
        public ProductionCountry createFromParcel(Parcel source) {
            return new ProductionCountry(source);
        }

        @Override
        public ProductionCountry[] newArray(int size) {
            return new ProductionCountry[size];
        }
    };
    @SerializedName("iso_3166_1")
    @Expose
    private String iso31661;
    @SerializedName("name")
    @Expose
    private String name;

    public ProductionCountry() {
    }

    protected ProductionCountry(Parcel in) {
        this.iso31661 = in.readString();
        this.name = in.readString();
    }

    public String getIso31661() {
        return iso31661;
    }


        /*//////////////////////
        //All about parcelable//
        /////////////////////*/

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
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
        dest.writeString(this.iso31661);
        dest.writeString(this.name);
    }
}
