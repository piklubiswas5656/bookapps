package ncert.solutions.ncrt21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import ncert.solutions.R;

public class Ncert21chapterlist extends AppCompatActivity {
    private String chil;
    private RecyclerView recyclerView;
    private Ncert21chapterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncert21chapterlist);
        recyclerView = (RecyclerView) findViewById(R.id.ncrt21chapterRecyle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chil = getIntent().getStringExtra("SUB");
        try {
            FirebaseRecyclerOptions<Ncert21chapterModal> options =
                    new FirebaseRecyclerOptions.Builder<Ncert21chapterModal>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child(chil), Ncert21chapterModal.class)
                            .build();
            adapter = new Ncert21chapterAdapter(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(adapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}