package blackjack.domain.participants;


import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Player {
    private final String name;
    private final Cards cards;
    private int bettingMoney;

    public Player(String name, Cards cards, int bettingMoney) {
        this.name = name.trim();
        this.cards = cards;
        this.bettingMoney = bettingMoney;
    }

    public void take(Card card1, Card card2) {
        this.cards.take(card1, card2);
    }

    public void additionalTake(Card card) {
        this.cards.additionalTake(card);
    }

    public boolean canTake() {
        return cards.canTake();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public void setBettingMoney(int playerBettingMoney) {
        bettingMoney = playerBettingMoney;
    }

    public double makeProfit(double multiplyRatio) {
        return bettingMoney * multiplyRatio;
    }
}
