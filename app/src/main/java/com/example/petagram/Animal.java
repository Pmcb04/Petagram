package com.example.petagram;

import android.os.Parcel;
import android.os.Parcelable;

class Animal implements Parcelable {

    private int imageDog;
    private String nameDog;
    private int rateDog;
    private boolean like;

    public Animal(int imageDog, String nameDog, int rateDog) {
        this.imageDog = imageDog;
        this.nameDog = nameDog;
        this.rateDog = rateDog;
        like = false;
    }


    protected Animal(Parcel in) {
        imageDog = in.readInt();
        nameDog = in.readString();
        rateDog = in.readInt();
        like = false;
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    public int getImageDog() {
        return imageDog;
    }

    public String getNameDog() {
        return nameDog;
    }

    public int getRateDog() {
        return rateDog;
    }

    public boolean isLike() { return like; }

    public void setLike(boolean like) {
        this.like = like;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageDog);
        parcel.writeString(nameDog);
        parcel.writeInt(rateDog);
    }
}