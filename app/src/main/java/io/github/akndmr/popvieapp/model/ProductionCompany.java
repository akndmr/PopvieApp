package io.github.akndmr.popvieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AKIN on 25.02.2018.
 */

public class ProductionCompany implements Parcelable {

    public static final Parcelable.Creator<ProductionCompany> CREATOR = new Parcelable.Creator<ProductionCompany>() {
        @Override
        public ProductionCompany createFromParcel(Parcel source) {
            return new ProductionCompany(source);
        }

        @Override
        public ProductionCompany[] newArray(int size) {
            return new ProductionCompany[size];
        }
    };
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private int id;

    public ProductionCompany() {
    }

    protected ProductionCompany(Parcel in) {
        this.name = in.readString();
        this.id = in.readInt();
    }

    public String getName() {
        return name;
    }


    /*//////////////////////
    //All about parcelable//
    /////////////////////*/

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.id);
    }
}
