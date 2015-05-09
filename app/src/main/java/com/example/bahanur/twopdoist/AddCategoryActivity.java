package com.example.bahanur.twopdoist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

import com.example.bahanur.common.AppConst;


public class AddCategoryActivity extends Activity {
    private EditText nameEt;
    private static final String TAG = "AddCategoryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#4285f4"));
        nameEt = (EditText) findViewById(R.id.editTextCategoryName);
    }

    public void okClicked(View v) {
        if(nameEt == null) {
            return;
        }
        String name = nameEt.getText().toString();
        Intent data = new Intent();
        data.putExtra(AppConst.CATEGORY_NAME_FOR_INTENT, name);
        setResult(RESULT_OK, data);
        finish();
    }

}
