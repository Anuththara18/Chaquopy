package com.anuththara18.chaquopy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.editTextTextPersonName);
        et2 = (EditText)findViewById(R.id.editTextTextPersonName2);
        btn = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.textView);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        Python py = Python.getInstance();
        PyObject pyobj = py.getModule("script");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                            // function name, arguments
               PyObject obj = pyobj.callAttr("main", et1.getText().toString(), et2.getText().toString());
               tv.setText(obj.toString());
            }
        });

    }
}