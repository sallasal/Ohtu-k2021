//Proudly triple-programmed with Mary Ehrstedt, Jenny Perttola and Salla Salokanto

package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String getScore() {
        String score = "";
        int tempScore = 0;
        if (player1Score == player2Score) {
            score = evenGame(player1Score);
        } else if (player1Score >= 4 || player2Score >= 4) {
            score = winOrAdvantage(player1Score - player2Score);
        } else {
            score = tennisPointsDescription(player1Score);
            score += "-";
            score += tennisPointsDescription(player2Score);
        }
        return score;
    }

    private String evenGame(int score) {
        switch (score) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";
        }
    }

    private String winOrAdvantage(int scoreDifference) {
        if (scoreDifference == 1) {
            return "Advantage player1";
        } else if (scoreDifference == -1) {
            return "Advantage player2";
        } else if (scoreDifference >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }
    
        private String tennisPointsDescription(int points) {
        switch (points) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }
        return "";
    }
}
