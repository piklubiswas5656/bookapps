package ncert.solutions.ncrt21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ncert.solutions.R;

public class NCERT21 extends AppCompatActivity {
    private CardView class1221, class1121, class1021, class921, class821, class721, class621, class521, class421, class321, class221, class121;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncert21);
        hook();
        buttonclik();
    }


    private void hook() {
        class1221 = (CardView) findViewById(R.id.class1221);
    }

    private void buttonclik() {
        class1221.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NCERT21.this, Ncert21booklist.class));
            }
        });
    }
}