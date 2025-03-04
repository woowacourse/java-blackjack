package model;

public class Judge {

    public boolean checkPlayerWin(Dealer dealer, Player player) {
        int dealerScore = dealer.getParticipantHand().calculateScoreSum();
        int playerScore = player.getParticipantHand().calculateScoreSum();

        return playerScore > dealerScore;

    }
}
