package com.example.wordexerciese;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> allWords;

    public WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        allWords = mWordDao.getAlphabetizedWords();
    }

    public LiveData<List<Word>> getAllWords(){
        return  allWords;
    }

    public void insert(Word word){
        WordRoomDatabase.databaseWriteExecutor.execute(() ->{
            mWordDao.insert(word);
        });
    }
}
