package ncert.solutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ncert.solutions.ncrt21.NCERT21;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ncrtnew2021(View view) {
       startActivity(new Intent(getApplicationContext(),NCERT21.class));
    }
}