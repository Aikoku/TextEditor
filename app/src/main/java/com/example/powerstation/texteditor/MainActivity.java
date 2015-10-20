package com.example.powerstation.texteditor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private static final String FILENAME = "txtFile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = this.getFileStreamPath(FILENAME);
        if (file == null || !file.exists()) {
            Log.e(TAG, "swagerino: file not found");
            file = new File(this.getFilesDir(), FILENAME);
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage().toString());
            }

        }


        String textFromFileString = readFromFile(FILENAME);
        TextView fromFileView = (TextView) findViewById(R.id.textView);
        fromFileView.setText(textFromFileString);

    }

    private String readFromFile(String fileName) {
        String fileContent = "";
        try {
            InputStream inputStream = openFileInput(fileName);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                fileContent = stringBuilder.toString();
            }
        }
       catch (IOException e) {
            Log.e(TAG, "Can not read file: " + e.toString());
        }
        return fileContent;
    }

    public void goToEditScreen(View view) {
        Intent goSecondIntent = new Intent(this, Edit.class);
        startActivity(goSecondIntent);

    }
}




