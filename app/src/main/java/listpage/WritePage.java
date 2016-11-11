package listpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import intro.MainActivity;
import psj.hahaha.R;

/**
 * Created by user on 2016-11-11.
 */

public class WritePage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write);
    }

    public void goMain(View v){

        Intent mainIntent = new Intent(WritePage.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }


    public void goContent(View v){

        Intent contentIntent = new Intent(WritePage.this, ContentPage.class);
        EditText eTitle = (EditText) findViewById(R.id.editTitle);
        EditText eMain = (EditText) findViewById(R.id.editMain);
        contentIntent.putExtra("title",eTitle.getText().toString());
        contentIntent.putExtra("main",eMain.getText().toString());
        startActivity(contentIntent);
        finish();
    }
}