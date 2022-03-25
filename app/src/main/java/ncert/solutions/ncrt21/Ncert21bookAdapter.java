package ncert.solutions.ncrt21;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import ncert.solutions.MainActivity;
import ncert.solutions.R;

public class Ncert21bookAdapter extends FirebaseRecyclerAdapter<Ncert21bookModal, Ncert21bookAdapter.Ncert21bookHolder> {


    public Ncert21bookAdapter(@NonNull FirebaseRecyclerOptions<Ncert21bookModal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Ncert21bookAdapter.Ncert21bookHolder holder, int position, @NonNull Ncert21bookModal model) {
        String sub = model.getSubId();
        holder.ncrt21bookrowsubname.setText(String.valueOf(model.getSubName()));
        holder.ncrt21bookrownumber.setText(String.valueOf(model.getSubNum()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), Ncert21chapterlist.class);
                intent.putExtra("SUB", sub);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public Ncert21bookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ncert21bookrow, parent, false);
        return new Ncert21bookHolder(view);
    }

    public class Ncert21bookHolder extends RecyclerView.ViewHolder {
        TextView ncrt21bookrownumber;
        TextView ncrt21bookrowsubname;

        public Ncert21bookHolder(@NonNull View itemView) {
            super(itemView);
            ncrt21bookrownumber = (TextView) itemView.findViewById(R.id.ncrt21bookrownumber);
            ncrt21bookrowsubname = (TextView) itemView.findViewById(R.id.ncrt21bookrowsubname);

        }
    }
}
