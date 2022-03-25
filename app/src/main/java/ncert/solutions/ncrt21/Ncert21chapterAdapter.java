package ncert.solutions.ncrt21;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import ncert.solutions.R;
import ncert.solutions.pdfviewdown.pdfviewdow;

public class Ncert21chapterAdapter extends FirebaseRecyclerAdapter<Ncert21chapterModal, Ncert21chapterAdapter.Ncert21chapterHolder> {

    public Ncert21chapterAdapter(@NonNull FirebaseRecyclerOptions<Ncert21chapterModal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Ncert21chapterAdapter.Ncert21chapterHolder holder, int position, @NonNull Ncert21chapterModal model) {
        String url = model.getChapterUrl();
        holder.ncert21chapterNam.setText(String.valueOf(model.getChapterName()));
        holder.Ncert21chapterDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), pdfviewdow.class);
                intent.putExtra("URL", url);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public Ncert21chapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ncert21chapterrow, parent, false);
        return new Ncert21chapterHolder(view);
    }

    public class Ncert21chapterHolder extends RecyclerView.ViewHolder {
        TextView ncert21chapterNam;
        Button Ncert21chapterDown;

        public Ncert21chapterHolder(@NonNull View itemView) {
            super(itemView);
            ncert21chapterNam = (TextView) itemView.findViewById(R.id.ncert21chapterNam);
            Ncert21chapterDown = (Button) itemView.findViewById(R.id.Ncert21chapterDown);
        }
    }
}
