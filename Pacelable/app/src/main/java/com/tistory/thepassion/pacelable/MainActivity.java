package com.tistory.thepassion.pacelable;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources(); Drawable d = res.getDrawable(R.drawable.book);
        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();

        book = new Book(1, "1-1-1-1", "슬럼독 밀리어네어", bitmap);

        Intent intent = new Intent(getApplicationContext(), BookActivity.class);
        intent.putExtra("book", book);

//        intent.putExtra("serial", book.getSerial());
//        intent.putExtra("isbn", book.getIsbn());
//        intent.putExtra("title", book.getTitle());
//        intent.putExtra("image", book.getImage());

        startActivity(intent);
    }
}
