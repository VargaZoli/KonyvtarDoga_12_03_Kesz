package com.example.konyvtardoga_12_03;



import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Book> books = new ArrayList<>();
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText titleInput = findViewById(R.id.titleInput);
        EditText authorInput = findViewById(R.id.authorInput);
        EditText pagesInput = findViewById(R.id.pagesInput);
        Button addButton = findViewById(R.id.addButton);
        ListView bookListView = findViewById(R.id.bookListView);

        adapter = new BookAdapter(this, books);
        bookListView.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String author = authorInput.getText().toString();
            String pagesStr = pagesInput.getText().toString();

            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(author) || TextUtils.isEmpty(pagesStr)) {
                Toast.makeText(this, "Minden mező kitöltése kötelező!", Toast.LENGTH_SHORT).show();
                return;
            }

            int pages = Integer.parseInt(pagesStr);
            if (pages < 50) {
                Toast.makeText(this, "Az oldalszám legalább 50 kell hogy legyen!", Toast.LENGTH_SHORT).show();
                return;
            }

            books.add(new Book(title, author, pages));
            adapter.notifyDataSetChanged();
            titleInput.setText("");
            authorInput.setText("");
            pagesInput.setText("");
        });
    }
}
