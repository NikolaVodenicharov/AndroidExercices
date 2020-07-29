package com.example.wordexerciese;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "word")
    public String mWord;

    public Word(String mWord) {
        this.mWord = mWord;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return mWord;
    }
}
