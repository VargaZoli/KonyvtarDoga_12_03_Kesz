package com.example.konyvtardoga_12_03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView titleTextView = findViewById(R.id.detailTitle);
        TextView authorTextView = findViewById(R.id.detailAuthor);
        TextView pagesTextView = findViewById(R.id.detailPages);
        TextView yearTextView = findViewById(R.id.detailYear);
        Button backButton = findViewById(R.id.backButton);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        int pages = intent.getIntExtra("pages", 0);

        Random random = new Random();
        int randomYear = random.nextInt(2024-1950+1)+1950;

        titleTextView.setText("Cím: " + title);
        authorTextView.setText("Szerző: " + author);
        pagesTextView.setText("Oldalszám: " + pages);
        yearTextView.setText("Random évszám: " + randomYear);

        backButton.setOnClickListener(v -> finish());
    }
}