package oleart.nil.marvelcomics;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by niloleart on 4/12/16.
 */
public class DetailActivity extends AppCompatActivity {
    public static final String DETAIL_ACTIVITY_TAG = DetailActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String name;
        String avatar;
        String description;
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
            name = extras.getString("name");
            description = extras.getString("description");
            avatar = extras.getString("avatar");


        TextView detailName = (TextView) findViewById(R.id.detail_hero_name);
        detailName.setText(name);
        TextView detailDescription = (TextView) findViewById(R.id.detail_hero_description);
        detailDescription.setText(description);
        ImageView detailAvatar = (ImageView) findViewById(R.id.detail_avatar);
        Picasso.with(this).load(avatar).into(detailAvatar);




    }
}
