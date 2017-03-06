package com.sdm.frogger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by spss on 17-3-6.
 */

public class TouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TouchView(this));
    }
}
