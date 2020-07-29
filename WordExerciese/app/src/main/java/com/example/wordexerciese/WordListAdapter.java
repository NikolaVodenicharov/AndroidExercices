package com.example.wordexerciese;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private final LayoutInflater inflater;
    private List<Word> words;

    public WordListAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (words != null){
            Word current = words.get(position);
            holder.wordItemView.setText(current.getWord());
        }
        else{
            holder.wordItemView.setText("No word");
        }
    }

    @Override
    public int getItemCount() {
        if (words == null){
            return 0;
        }

        return words.size();
    }

    public void setWords(List<Word> words){
        this.words = words;
        notifyDataSetChanged();
    }

     public class WordViewHolder extends RecyclerView.ViewHolder{
        private final TextView wordItemView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordItemView =itemView.findViewById(R.id.textView);
        }
    }
}
