package com.igor.hamsteratlas.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@Entity
public class Hamster implements Parcelable {

    public Hamster () {

    }

    public Hamster(Parcel in){
        mName = in.readString();
        mDescription = in.readString();
        mPinned = in.readInt() == 1;
        mImage = in.readString();

    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int mId;


    @SerializedName("title")
    @ColumnInfo(name = "name")
    private String mName;


    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String mDescription;

    @SerializedName("pinned")
    @ColumnInfo(name = "pinned")
    private Boolean mPinned;

    @SerializedName("image")
    @ColumnInfo(name = "image")
    private String mImage;



    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }


    public Boolean isPinned() {
        if(mPinned == null) return false;
        return mPinned;
    }

    public void setPinned(Boolean pinned) {
        mPinned = pinned;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mDescription);
        parcel.writeInt(isPinned() ? 1 : 0);
        parcel.writeString(mImage);
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Hamster createFromParcel(Parcel in) {
            return new Hamster(in);
        }
        public Hamster[] newArray(int size) {
            return new Hamster[size];
        }
    };
}
