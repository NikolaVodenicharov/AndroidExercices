package com.example.wordexerciese;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository repository;
    private LiveData<List<Word>> words;

    public WordViewModel(@NonNull Application application) {
        super(application);
        this.repository = new WordRepository(application);
        this.words = repository.getAllWords();
    }

    public LiveData<List<Word>> getWords() {
        return words;
    }

    public void insert (Word word){
        this.repository.insert(word);
    }
}
