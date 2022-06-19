package com.example.hackillinois.adapters;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackillinois.R;
import com.example.hackillinois.models.Leaderboard;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    Context context;
    List<Leaderboard> leaderboardList;

    public LeaderboardAdapter(Context context, List<Leaderboard> leaderboardList){
        this.context = context;
        this.leaderboardList = leaderboardList;

    }


    @NonNull
    @Override
    public LeaderboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"Creating View holder");
        View view = LayoutInflater.from(context).inflate(R.layout.leaderboard_row, parent, false);

        return new LeaderboardAdapter.ViewHolder(view);
    }

     @Override
    public void onBindViewHolder(@NonNull LeaderboardAdapter.ViewHolder holder, int position) {
         Log.d(TAG,"Setting my View holder" + leaderboardList.get(position).getDiscord());
         Log.d(TAG,"Setting my View holder" + leaderboardList.get(position).getId());
         Log.d(TAG,"Setting my View holder" + leaderboardList.get(position).getPoints());

         holder.id.setText(String.valueOf(position + 1));
         holder.discord_id.setText(leaderboardList.get(position).getDiscord());
         holder.points.setText(String.valueOf(leaderboardList.get(position).getPoints()));

    }

    @Override
    public int getItemCount() {
        Log.d(TAG,"Size is " + leaderboardList.size());
        return leaderboardList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, discord_id, points;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG,"Init View");
            id = itemView.findViewById(R.id.row_id);
            discord_id = itemView.findViewById(R.id.discord_id);
            points = itemView.findViewById(R.id.points);

        }
    }
}
