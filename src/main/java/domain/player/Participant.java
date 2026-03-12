package domain.player;

import domain.card.Card;
import domain.vo.Name;

import java.util.List;

public class Participant {
    private static final int MAX_SCORE = 21;
    private static final int BLACKJACK_CARD_QUANTITY = 2;

    private final Name name;
    private final HandCards handCards = new HandCards();

    public Participant(Name name) {
        this.name = name;
    }

    public void addCard(Card card) {
        handCards.addCard(card);
    }

    public boolean isBust() {
        return handCards.getCardScoreSum() > MAX_SCORE;
    }

    public boolean isBlackJack() {
        return (handCards.quantity() == BLACKJACK_CARD_QUANTITY) && (handCards.getCardScoreSum() == MAX_SCORE);
    }

    public String getName() {
        return name.getName();
    }

    public List<String> getCards() {
        return handCards.cardsToString();
    }

    public int getCardSum() {
        return handCards.getCardScoreSum();
    }

    public WinStatus matchResult(int score) {
        int myScore = handCards.getCardScoreSum();
        if (myScore > score) {
            return WinStatus.WIN;
        }

        if (myScore < score) {
            return WinStatus.LOSE;
        }

        return WinStatus.DRAW;
    }
}
