package com.tistory.thepassion.pacelable;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent intent = getIntent();

        Book book = intent.getParcelableExtra("book");
        ((TextView) findViewById(R.id.txtSerial)).setText(String.valueOf(book.getSerial()));
        ((TextView) findViewById(R.id.txtIsbn)).setText(book.getIsbn());
        ((TextView) findViewById(R.id.txtTitle)).setText(book.getTitle());
        ((ImageView) findViewById(R.id.imageView)).setImageBitmap(book.getImage());

//        int serial = intent.getIntExtra("serial", 0);
//        String isbn = intent.getStringExtra("isbn");
//        String title = intent.getStringExtra("title");
//        Bitmap image = intent.getParcelableExtra("image");
//
//        ((TextView) findViewById(R.id.txtSerial)).setText(String.valueOf(serial));
//        ((TextView) findViewById(R.id.txtIsbn)).setText(isbn);
//        ((TextView) findViewById(R.id.txtTitle)).setText(title);
//        ((ImageView) findViewById(R.id.imageView)).setImageBitmap(image);
    }
}
