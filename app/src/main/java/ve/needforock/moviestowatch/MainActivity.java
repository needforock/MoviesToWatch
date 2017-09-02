package ve.needforock.moviestowatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import ve.needforock.moviestowatch.models.Movie;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    public static final String MOVIE_ID = "ve.needforock.moviestowatch.KEY.MOVIE_ID";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final EditText movieEt = (EditText) findViewById(R.id.movieEt);
        Button saveBtn = (Button) findViewById(R.id.saveBtn);
        Button lastBtn = (Button) findViewById(R.id.lastMovieBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(movieEt.getText().toString().trim().length() != 0){

                   Movie movie = new Movie(movieEt.getText().toString(), false);
                   movie.save();
                   movies=getMovies();



                  /* for (Movie moviex : movies){
                       Log.d("movies", moviex.getName());
                   }*/

                   movieEt.setText("");
                   Toast.makeText(MainActivity.this, "Se guardo su pelicula", Toast.LENGTH_SHORT).show();

               }else{
                   Toast.makeText(MainActivity.this, "Introduce Nombre de Pelicula", Toast.LENGTH_SHORT).show();
               }

            }
        });

        lastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                Bundle lastMovieID = new Bundle();
                int listSize = movies.size();
                if(listSize>0){
                    Movie lastMovie = movies.get(listSize-1);
                    long id =  lastMovie.getId();
                    /*Log.d ("lastmovie", lastmovie.getName());
                    Log.d ("lastmovie", String.valueOf(lastmovie.getId()));*/

                    lastMovieID.putLong(MOVIE_ID, id);
                    intent.putExtras(lastMovieID);
                    startActivity(intent);                    
                }else{
                    Toast.makeText(MainActivity.this, "No Hay Peliculas sin ver", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private List getMovies() {
        List<Movie> movieList = Movie.find(Movie.class, "watched = 0");
        return movieList;
    }


    @Override
    protected void onResume() {
        super.onResume();
        movies = getMovies();
    }


}
