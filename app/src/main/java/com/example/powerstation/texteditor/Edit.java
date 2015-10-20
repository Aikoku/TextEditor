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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Edit extends Activity {

    private static final String TAG = "MainActivity";
    private static final String FILENAME = "txtFile.txt";
    EditText fromFileView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        String textFromFileString = readFromFile(FILENAME);
        final EditText fromFileView = (EditText) findViewById(R.id.editText);
        fromFileView.setText(textFromFileString);
        fromFileView.setSelection(fromFileView.getText().length());

        Button mButton = (Button) findViewById(R.id.saveBtn);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                writeToFile(fromFileView.getText().toString(), FILENAME);
            }
        });
    }

    private void writeToFile(String data, String fileName) {
        try {
            OutputStreamWriter outputStreamWriter = new
                    OutputStreamWriter(openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            goToFirstScreen(new View(this));
        } catch (IOException e) {
            Toast.makeText(this, "Cant write file", Toast.LENGTH_LONG);
        }
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
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Cant read file", Toast.LENGTH_LONG);
        } catch (IOException e) {
            Toast.makeText(this, "Cant read file", Toast.LENGTH_LONG);
        }
        return fileContent;
    }

    public void goToFirstScreen(View view) {
        Intent goToFirstIntent = new Intent(this, MainActivity.class);
        startActivity(goToFirstIntent);
    }
}

