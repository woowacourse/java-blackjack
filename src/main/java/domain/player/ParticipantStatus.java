package domain.player;

import domain.card.Card;
import domain.vo.Cost;

import java.util.List;

public class ParticipantStatus {
    private static final int MAX_SCORE = 21;
    private static final int BLACKJACK_CARD_QUANTITY = 2;

    private final HandCards handCards = new HandCards();
    private Cost cost;

    public void addCard(Card card) {
        handCards.addCard(card);
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost.getCost();
    }

    public void addCost(int money) {
        cost.addCost(money);
    }

    public int calculateProfit(WinStatus winStatus) {
        return cost.calculateProfit(winStatus);
    }

    public boolean isBust() {
        return handCards.getCardScoreSum() > MAX_SCORE;
    }

    public boolean isBlackJack() {
        return (handCards.getQuantity() == BLACKJACK_CARD_QUANTITY) && (handCards.getCardScoreSum() == MAX_SCORE);
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

