package ve.needforock.moviestowatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

import ve.needforock.moviestowatch.models.Movie;



public class MovieActivity extends AppCompatActivity {

    CheckBox movieCb;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieCb = (CheckBox) findViewById(R.id.movieCb);
        long id = getIntent().getLongExtra(MainActivity.MOVIE_ID, 0 );
        movie = Movie.findById(Movie.class, id);
        getSupportActionBar().setTitle(movie.getName());

    }

    @Override
    protected void onPause() {
        super.onPause();

        movie.setWatched(movieCb.isChecked());
        movie.save();
    }
}
