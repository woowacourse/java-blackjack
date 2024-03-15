package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.gameresult.Batting;
import blackjack.domain.hands.GamerHands;
import blackjack.domain.hands.HandsScore;
import blackjack.domain.hands.Name;

import java.util.List;

public class Player{
    private final GamerHands playerHands;
    private final Batting batting;

    public Player(Name name, Batting batting) {
        this.playerHands = new GamerHands(name);
        this.batting = batting;
    }

    public boolean canAddCard() {
        return (!playerHands.isBust());
    }

    public boolean isFirstTurnBackJack() {
        return playerHands.isBlackJack() && playerHands.getHandsCards().size() == 2;
    }

    public void addCard(Card card) {
        playerHands.addCard(card);
    }

    public boolean isBust() {
        return playerHands.isBust();
    }

    public Double getBat() {
        return batting.getBat();
    }

    public List<Card> getHandsCards() {
        return playerHands.getHandsCards();
    }

    public String getName() {
        return playerHands.getName();
    }

    public HandsScore getHandsScore() {
        return playerHands.getHandsScore();
    }
}
