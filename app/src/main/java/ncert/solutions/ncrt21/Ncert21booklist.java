package ncert.solutions.ncrt21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import ncert.solutions.R;

public class Ncert21booklist extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Ncert21bookAdapter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncert21booklist);
        recyclerView = (RecyclerView) findViewById(R.id.ncert21booklistrecylc);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        try {
            FirebaseRecyclerOptions<Ncert21bookModal> options =
                    new FirebaseRecyclerOptions.Builder<Ncert21bookModal>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Subject21"), Ncert21bookModal.class)
                            .build();
            adpter = new Ncert21bookAdapter(options);
        } catch (Exception e) {
            e.printStackTrace();
        }


        recyclerView.setAdapter(adpter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adpter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adpter.stopListening();
    }
}