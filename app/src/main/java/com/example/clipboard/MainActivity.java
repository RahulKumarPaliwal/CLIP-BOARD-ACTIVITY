package com.example.clipboard;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {
    ClipboardManager clipbm;
    ClipData clipd;
    Button b1,b2;
    EditText t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();

                    }
                })
                .check();

        t1 = (EditText) findViewById(R.id.TxtCopy);
        t2 = (EditText) findViewById(R.id.Txtpaste);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        clipbm= (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text;
                text = t1.getText().toString();

                clipd = ClipData.newPlainText("text", text);
                clipbm.setPrimaryClip(clipd);

                Toast.makeText(getApplicationContext(), "Text Copied",
                        Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ClipData abc = clipbm.getPrimaryClip();
                ClipData.Item item = abc.getItemAt(0);

                String text = item.getText().toString();
                t2.setText(text);

                Toast.makeText(getApplicationContext(), "Thanks for using this app",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}