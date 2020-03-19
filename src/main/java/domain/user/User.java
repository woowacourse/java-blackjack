package domain.user;

import domain.card.Cards;
import domain.card.Deck;

public abstract class User {

    protected Cards cards = new Cards();

    /*
     * 딜러와 플레이어의 각각 카드 받는 조건에 따라 다르게 구현
     */
    public abstract boolean isReceiveAble();

    public void receiveFirstCards(Deck deck) {
        cards.put(deck.dealFirstCards());
    }

    public boolean isLargerThan(int score) {
        return this.cards.isLargerThan(score);
    }

    public boolean isSmallerThan(int blackjackScore) {
        return this.cards.isSmallerThan(blackjackScore);
    }

    public void receiveCard(Deck deck) {
        cards.put(deck.deal());
    }

    public Cards getCards() {
        return this.cards;
    }

    public int getTotalScore() {
        return cards.sumScores();
    }
}
