package com.example.tennisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Game extends AppCompatActivity {
    private Button playerOneScoreButton;
    private Button playerTwoScoreButton;

    private TextView playerOneNameText;
    private TextView playerTwoNameText;

    private TextView pointsPlayerOneText;
    private TextView pointsPlayerTwoText;

    private TextView gamesPlayerOneText;
    private TextView gamesPlayerTwoText;

    private TextView setsPlayerOneText;
    private TextView setsPlayerTwoText;





    final GameEngine gameEngine = new GameEngine();

    final JsonData mJsonData = new JsonData();


    public Game() throws JSONException {
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerOneScoreButton = findViewById(R.id.buttonPlayerOne);
        playerTwoScoreButton = findViewById(R.id.buttonPlayerTwo);
        playerOneNameText = findViewById(R.id.playerOneName);
        playerTwoNameText = findViewById(R.id.playerTwoName);
        pointsPlayerOneText = findViewById(R.id.scorePlayerOne);
        pointsPlayerTwoText = findViewById(R.id.scorePlayerTwo);

        gamesPlayerOneText = findViewById(R.id.gamesPlayerOne);
        gamesPlayerTwoText = findViewById(R.id.gamesPlayerTwo);
        setsPlayerOneText = findViewById(R.id.setsPlayerOne);
        setsPlayerTwoText = findViewById(R.id.setsPlayerTwo);


        gameEngine.setNumberOfSets(2);

        /*try {
            printName();
        } catch (JSONException e) {
            e.printStackTrace();
        } */

        GetDataService service = RetrofitClient.getRetrofit().create(GetDataService.class);
        Call<List<JsonData>> call = service.getJsonData();
        call.enqueue(new Callback<List<JsonData>>() {
            @Override
            public void onResponse(Call<List<JsonData>> call, Response<List<JsonData>> response) {
                playerOneNameText.setText(mJsonData.getFirstName());

            }

            @Override
            public void onFailure(Call<List<JsonData>> call, Throwable t) {
                System.out.println("Error!!!!!!!!!!");
            }
        });


      /*  playerOneScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // ovdje pozvati retrofit
                    String score = gameEngine.firstPlayer();
                    printScoreOne(score);
                    printScoreTwo(score);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        playerTwoScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //ovdje pozvati retrofit
                    String score = gameEngine.secondPlayer();
                    printScoreOne(score);
                    printScoreTwo(score);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }); */

    }

    /*public void printName() throws JSONException {
        playerOneNameText.setText(gameEngine.getPlayerOne());
        playerTwoNameText.setText(gameEngine.getPlayerTwo());
    } */

    public void printScoreOne(String score) throws JSONException {

        JSONObject json = new JSONObject(score);
        try {
            String points = json.getJSONObject("Player One").getJSONObject("match").getString("points");
            String games = json.getJSONObject("Player One").getJSONObject("match").getString("games");
            String sets = json.getJSONObject("Player One").getJSONObject("match").getString("sets");
            boolean deuce = json.getBoolean("Deuce");
            boolean tieBreak = json.getBoolean("TieBreak");
            if(deuce){
                Toast.makeText(getApplicationContext(), "DEUCE", Toast.LENGTH_SHORT).show();
            }
            if(tieBreak){
                String tie = json.getJSONObject("Player One").getJSONObject("match").getString("tieBreak Points");
                pointsPlayerOneText.setText(tie);
            }else{

                pointsPlayerOneText.setText(points);

            }

            gamesPlayerOneText.setText(String.valueOf(games));
            setsPlayerOneText.setText(String.valueOf(sets));
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void printScoreTwo(String score) throws JSONException {

        JSONObject json = new JSONObject(score);
        try {
            String points = json.getJSONObject("Player Two").getJSONObject("match").getString("points");
            String games = json.getJSONObject("Player Two").getJSONObject("match").getString("games");
            String  sets = json.getJSONObject("Player Two").getJSONObject("match").getString("sets");
            boolean tieBreak = json.getBoolean("TieBreak");
            if(tieBreak){
                String tie = json.getJSONObject("Player Two").getJSONObject("match").getString("tieBreak Points");
                pointsPlayerTwoText.setText(tie);
            }else{
                pointsPlayerTwoText.setText(points);
            }

            gamesPlayerTwoText.setText(String.valueOf(games));
            setsPlayerTwoText.setText(String.valueOf(sets));

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }




}


