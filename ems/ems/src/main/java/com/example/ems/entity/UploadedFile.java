package com.example.ems.entity;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "files")
public class UploadedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String fileName;

    @Lob
    @Column(nullable = false)
    private byte[] data;

    public int getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UploadedFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
