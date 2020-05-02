package com.example.jokesandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class JokesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        TextView jokeTV = findViewById(R.id.JokeTextview);

        if(getIntent()!=null) {
            String jokefromIntent = getIntent().getStringExtra("joke");
            jokeTV.setText(jokefromIntent);
        }

    }
}
