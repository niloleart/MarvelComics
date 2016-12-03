package oleart.nil.marvelcomics;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by niloleart on 3/12/16.
 */

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.MyViewHolder> {
    Context mContext;
    public static final String HEROES_ADAPTER_TAG = HeroesAdapter.class.getSimpleName();
    private List<Hero> heroList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description;
        public ImageView avatar;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.hero_name);
            description = (TextView) view.findViewById(R.id.hero_description);
            avatar = (ImageView) view.findViewById(R.id.hero_avatar);
        }
    }


    public HeroesAdapter(List<Hero> heroList, Context mContext) {
        this.heroList = heroList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(HEROES_ADAPTER_TAG, "onCreate");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hero_list_row, parent, false);
        MyViewHolder viewRowHolder = new MyViewHolder(itemView);

        return viewRowHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.e(HEROES_ADAPTER_TAG, "onBindViewHolder");
        holder.name.setText(this.heroList.get(position).getName());
        holder.description.setText(this.heroList.get(position).getDescription());
        Picasso.with(mContext)
                .load(this.heroList.get(position).getImage())
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return this.heroList.size();
    }

}
