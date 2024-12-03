package com.example.konyvtardoga_12_03;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class BookAdapter extends android.widget.BaseAdapter {
    private Context context;
    private List<Book> books;


    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_book, parent, false);
        }

        Book currentBook = books.get(position);

        TextView titleView = convertView.findViewById(R.id.bookTitle);
        TextView authorView = convertView.findViewById(R.id.bookAuthor);
        TextView pagesView = convertView.findViewById(R.id.bookPages);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        titleView.setText(currentBook.getTitle());
        authorView.setText(currentBook.getAuthor());
        pagesView.setText("Oldalszám: " + currentBook.getPages());

        deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Törlés megerősítése")
                    .setMessage("Biztosan törölni szeretné ezt a könyvet?")
                    .setPositiveButton("Igen", (dialog, which) -> {
                        books.remove(position);
                        notifyDataSetChanged();
                    })
                    .setNegativeButton("Mégse", null)
                    .show();
        });

        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("title", currentBook.getTitle());
            intent.putExtra("author", currentBook.getAuthor());
            intent.putExtra("pages", currentBook.getPages());
            context.startActivity(intent);
        });

        return convertView;
    }
}
