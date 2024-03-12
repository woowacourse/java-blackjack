package blackjack.domain.participants;


import blackjack.domain.cards.Card;
import blackjack.domain.cards.Hand;

public class Player {
    private final Name name;
    private final PlayerStatus playerStatus;

    public Player(Name name) {
        this.name = name;
        this.playerStatus = new PlayerStatus();
    }

    public void receiveCard(Card card) {
        playerStatus.addCard(card);
    }

    public int calculateScore() {
        return playerStatus.calculateScore();
    }

    public boolean isNotOver(int boundaryScore) {
        return playerStatus.calculateScore() < boundaryScore;
    }

    public Name getName() {
        return name;
    }

    public Hand getHand() {
        return playerStatus.getHand();
    }
}
