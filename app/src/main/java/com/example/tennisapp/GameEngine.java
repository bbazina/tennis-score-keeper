package com.example.tennisapp;



import org.json.JSONException;
import org.json.JSONObject;


public class GameEngine {

    private String playerOne;
    private String playerTwo;

    private String score = "{\n" +
            "   \"Player One\" : {\n" +
            "    \"id\" : \"001\",\n" +
            "    \"first name\" : \"Bruno\",\n" +
            "    \"last name\" : \"Bazina\",\n" +
            "    \"match\" :\n" +
            "    {\n" +
            "      \"points\" : \"0\",\n" +
            "      \"advantage_one\" : \"AD\",\n" +
            "      \"games\" : \"0\",\n" +
            "      \"tieBreak points\" : \"0\",\n" +
            "      \"sets\" : \"0\"\n" +
            "    }\n" +
            "   },\n" +
            "   \"Player Two\" : {\n" +
            "    \"id\" : \"002\",\n" +
            "    \"first name\" : \"Toni\",\n" +
            "    \"last name\" : \"Mastelic\",\n" +
            "    \"match\" :\n" +
            "    {\n" +
            "      \"points\" : \"0\",\n" +
            "      \"advantage_two\" : \"AD\",\n" +
            "      \"games\" : \"0\",\n" +
            "      \"tieBreak points\" : \"0\",\n" +
            "      \"sets\" : \"0\"\n" +
            "    }\n" +
            "   },\n" +
            "   \"Deuce\" : false,\n" +
            "   \"TieBreak\" : false\n" +
            "   \n" +
            "}";
    private JSONObject json = new JSONObject(score);

    private int pointsPlayerOne = 0;
    private int pointsPlayerTwo = 0;

    private int gamesPlayerOne = 0;
    private int gamesPlayerTwo = 0;


    private int setsPlayerOne = 0;
    private int setsPlayerTwo = 0;

    private int tieBreakPointsOne = 0;
    private int tieBreakPointsTwo = 0;

    private int numberOfSets;


    public boolean tieBreak = false;
    private boolean isMatchOver = false;



    public GameEngine() throws JSONException {
    }

    public String getPlayerOne() throws JSONException {
        return json.getJSONObject("Player One").getString("first name");
    }

    public String getPlayerTwo() throws JSONException {
        return json.getJSONObject("Player Two").getString("first name");
    }

    public void setNumberOfSets(int numberOfSets) {
        this.numberOfSets = numberOfSets;
    }

    private int pointsPlayerOne(){
        return pointsPlayerOne++;
    }

    private int pointsPlayerTwo(){
        return pointsPlayerTwo++;
    }

    private int tieBreakPointsOne(){
        return tieBreakPointsOne++;
    }

    private int tieBreakPointsTwo(){
        return tieBreakPointsTwo++;
    }


    private void resetPoints() {
        pointsPlayerOne = 0;
        pointsPlayerTwo = 0;
    }

    private void resetGame() {
        gamesPlayerOne = 0;
        gamesPlayerTwo = 0;
    }

    private void resetTieBreakPoints() {
        tieBreakPointsOne = 0;
        tieBreakPointsTwo = 0;
    }



    private boolean isGameWinner() {
        if (pointsPlayerOne >= 4 && pointsPlayerOne >= pointsPlayerTwo + 2) {
            resetPoints();
            gamesPlayerOne++;
            return true;
        } else if (pointsPlayerTwo >= 4 && pointsPlayerTwo >= pointsPlayerOne + 2) {
            resetPoints();
            gamesPlayerTwo++;
            return true;
        }
        return false;
    }


    private boolean isDeuce() {
        if (pointsPlayerOne >= 3 && pointsPlayerOne == pointsPlayerTwo) {
            return true;
        }
        return false;
    }


    private boolean isAdvantagePlayerOne() {
        if (pointsPlayerOne >= 4 && pointsPlayerOne == pointsPlayerTwo + 1) {
            return true;
        }
        return false;
    }
    private boolean isAdvantagePlayerTwo(){
        if(pointsPlayerTwo >= 4 && pointsPlayerTwo == pointsPlayerOne +1){
            return true;
        }
        return false;
    }


    private boolean isSetWinner() {
        if (gamesPlayerOne >= 6 && gamesPlayerOne >= gamesPlayerTwo + 2) {
            resetGame();
            setsPlayerOne++;
            return true;
        } else if (gamesPlayerTwo >= 6 && gamesPlayerTwo >= gamesPlayerOne + 2) {
            resetGame();
            setsPlayerTwo++;
            return true;
        }
        return false;
    }


    private boolean isTieBreak() {
        if (gamesPlayerOne == 6 && gamesPlayerOne == gamesPlayerTwo) {
            tieBreak = true;
            return true;
        }
        return false;
    }


    private boolean isTieBreakWinner() throws JSONException {
        if (tieBreakPointsOne >= 7 && tieBreakPointsOne >= tieBreakPointsTwo + 2) {
            resetTieBreakPoints();
            resetGame();
            setsPlayerOne++;
            tieBreak = false;
            json.put("TieBreak", false);

        } else if (tieBreakPointsTwo >= 7 && tieBreakPointsTwo >= tieBreakPointsOne + 2) {
            resetTieBreakPoints();
            resetGame();
            setsPlayerTwo++;
            tieBreak = false;
            json.put("TieBreak", false);
        }
        return false;
    }


    private boolean isMatchWinner() {
        if (setsPlayerOne == numberOfSets && setsPlayerOne >= setsPlayerTwo + 1) {
            resetPoints();
            resetGame();
            return true;
        } else if (setsPlayerTwo == numberOfSets && setsPlayerTwo >= setsPlayerOne + 1) {
            resetPoints();
            resetGame();

            return true;
        }
        return false;
    }

    private void setEngine() throws JSONException {
        String status = "";

        if (isGameWinner()) {
            status = "Gem Winner";
        }
        if (isTieBreak()) {
            status = "Tie break";
            tieBreak = true;
        }
        if (isSetWinner()) {
            status = "Set winner";

        }
        if (isMatchWinner()) {
            status = "Match winner";
        }

        jsonParser(status);

        System.out.println(status + playerOne + "|" + translatedScore(pointsPlayerOne) + ", " + gamesPlayerOne + ", " + setsPlayerOne + " || " + setsPlayerTwo + ", " + gamesPlayerTwo + ", " + translatedScore(pointsPlayerTwo) + "| " + playerTwo);
    }



    private String translatedScore(int score){
        String ret = "";
        if(isDeuce()){
            ret = "40";
        }
        else if(isAdvantagePlayerOne()){

            if(score == pointsPlayerOne){
                ret ="AD";
            }
        }
        else if(isAdvantagePlayerTwo()){

            if(score == pointsPlayerTwo){
                ret = "AD";
            }
        }else{
            switch (score){
                case 0:
                    ret = "0";
                    break;
                case 1:
                    ret = "15";
                    break;
                case 2:
                    ret = "30";
                    break;
                case 3:
                    ret = "40";
                    break;
            }
        }

        return ret;
    }


    private String jsonParser(String status) throws JSONException {
        json = new JSONObject(score);
        json.getJSONObject("Player One").getJSONObject("match").put("points", translatedScore(pointsPlayerOne));
        json.getJSONObject("Player Two").getJSONObject("match").put("points", translatedScore(pointsPlayerTwo));
        json.getJSONObject("Player One").getJSONObject("match").put("games", gamesPlayerOne);
        json.getJSONObject("Player One").getJSONObject("match").put("sets", setsPlayerOne);
        json.getJSONObject("Player Two").getJSONObject("match").put("games", gamesPlayerTwo);
        json.getJSONObject("Player Two").getJSONObject("match").put("sets", setsPlayerTwo);


        json.put("Deuce", isDeuce());


        return score = json.toString();
    }



    private String tieBreakEngine() throws JSONException {
        json = new JSONObject(score);

        json.put("TieBreak", isTieBreak());
        json.getJSONObject("Player One").getJSONObject("match").put("tieBreak Points", tieBreakPointsOne);
        json.getJSONObject("Player Two").getJSONObject("match").put("tieBreak Points", tieBreakPointsTwo);
        isTieBreakWinner();



        System.out.print(playerOne + "-" + tieBreakPointsOne + " " + setsPlayerOne + " | ");
        System.out.println(playerTwo + "-" + tieBreakPointsTwo + " " + setsPlayerTwo + " |");
        return score = json.toString();
    }

    public String firstPlayer() throws JSONException {
        if(!isMatchOver){
            if(!tieBreak){
                pointsPlayerOne();
                setEngine();
            }else {
                tieBreakPointsOne();
                tieBreakEngine();
            }
        }
        return score;
    }
    public String secondPlayer() throws JSONException {
        if(!isMatchOver){
            if(!tieBreak){
                pointsPlayerTwo();
                setEngine();
            }else{
                tieBreakPointsTwo();
                tieBreakEngine();
            }
        }
        return score;
    }








}








