package com.a13rain.unodir.aqualife.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "aquarium")
public class Aquarium {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idx")
    private int idx;
    private String name;
    private long date;
    private int size_t;
    private int x;
    private int y;
    private int Z;
    private String summary;
    private byte[] sumnail;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getSize_t() {
        return size_t;
    }

    public void setSize_t(int size_t) {
        this.size_t = size_t;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return Z;
    }

    public void setZ(int z) {
        Z = z;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public byte[] getSumnail() {
        return sumnail;
    }

    public void setSumnail(byte[] sumnail) {
        this.sumnail = sumnail;
    }
}
