package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final ParticipantHand participantHand;

    public Player() {
        this.participantHand = new ParticipantHand();
    }

    public void receiveCard(Card card) {
        participantHand.add(card);
    }

    public List<Card> getHandCards() {
        return participantHand.getCards();
    }

    public ParticipantHand getParticipantHand() {
        return participantHand;
    }

    public GameResult decideWinning(Dealer dealer) {
        int dealerScore = dealer.getParticipantHand().calculateFinalScore();
        int playerScore = participantHand.calculateFinalScore();

        if (dealerScore > playerScore){
            return GameResult.LOSE;
        }
        if (dealerScore < playerScore){
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }
}
