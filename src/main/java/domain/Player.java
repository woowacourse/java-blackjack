package domain;

import java.util.List;

public class Player {
    private final String name;
    private final Hand hand;

    public Player(final String name) {
        validateName(name);
        this.name = name;
        hand = new Hand();
    }

    private void validateName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    public void drawCard(final Card card) {
        hand.saveCard(card);
    }

    public void drawCards(final List<Card> cards) {
        hand.saveCards(cards);
    }

    public int calculateScoreWhileDraw() {
        return hand.calculateScoreWhileDraw();
    }

    public int calculateResultScore(final int blackjackScore) {
        return hand.calculateScore(blackjackScore);
    }

    public boolean hasMoreScore(final Dealer dealer) {
        return calculateResultScore(21) > dealer.calculateResultScore(21);
    }

    public boolean hasSameScore(final Dealer dealer) {
        return calculateResultScore(21) == dealer.calculateResultScore(21);
    }

    public boolean hasMoreCard(final Dealer dealer) {
        return getTotalSize() > dealer.getTotalSize();
    }

    public int getTotalSize() {
        return hand.size();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
