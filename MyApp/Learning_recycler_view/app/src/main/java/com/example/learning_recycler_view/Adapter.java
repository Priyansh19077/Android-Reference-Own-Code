package com.example.learning_recycler_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {


    private List<ModelClass> modelClassList;
    public Adapter(List<ModelClass> modelClassList) {
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        int resource=modelClassList.get(position).getImageResource();
        String title1=modelClassList.get(position).getTitle();
        String body1=modelClassList.get(position).getBody();
        holder.setData(resource,title1,body1);
    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title;
        private TextView body;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            title=itemView.findViewById(R.id.title);
            body=itemView.findViewById(R.id.body);
        }
        private void setData(int resource,String titleText, String bodyText)
        {
            imageView.setImageResource(resource);
            title.setText(titleText);
            body.setText(bodyText);
        }
    }

}
