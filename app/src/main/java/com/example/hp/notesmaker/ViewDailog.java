package com.example.hp.notesmaker;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;


public class ViewDailog extends MainActivity{

    public static String fileName;
    public static String text;

    public static void showDialog(final Context context)
    {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        Button btn = (Button)dialog.findViewById(R.id.save);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText editText = (EditText)dialog.findViewById(R.id.filename);
                fileName = editText.getText().toString();
                text = MainActivity.txtSpeechInput.getText().toString();
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(text.getBytes());
                    fos.close();
                    Toast.makeText(context,"saved at " +  context.getFilesDir(),  Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                    Toast.makeText(context,"File not found ",  Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error ",  Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
