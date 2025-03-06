package model;

import java.util.List;

public class Player {
    private final String name;
    private final ParticipantHand participantHand;

    public Player(String name) {
        this.name = name;
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

    public void dealInitialCards(Deck deck) {
        for (int i = 0; i < 2; i++) {
            receiveCard(deck.pick());
        }
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

    public String getName() {
        return name;
    }
}
