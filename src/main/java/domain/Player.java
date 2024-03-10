package domain;

import java.util.List;

public class Player {
    private static final int BLACKJACK_SCORE = 21;

    private final String name;
    private final Hand hand;

    public Player(final String name) {
        this.name = name;
        hand = new Hand();
    }

    public List<Card> pickTwoCards(final Deck deck) {
        hand.saveCards(List.of(deck.pick(), deck.pick()));
        return hand.getCards();
    }

    public List<Card> pickOneCard(final Deck deck) {
        hand.saveCard(deck.pick());
        return hand.getCards();
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

    public boolean isNotSameScoreAs(final Player other) {
        return calculateResultScore() != other.calculateResultScore();
    }

    public boolean hasMoreScoreThan(final int otherScore) {
        return calculateResultScore() > otherScore;
    }

    public boolean hasMoreScoreThan(final Player other) {
        return calculateResultScore() > other.calculateResultScore();
    }

    public int calculateResultScore() {
        return hand.calculateScore(BLACKJACK_SCORE);
    }

    public boolean hasLessOrSameCardThan(final Player other) {
        return getTotalSize() <= other.getTotalSize();
    }

    public boolean cannotDraw() {
        return hand.calculateScore(BLACKJACK_SCORE) > BLACKJACK_SCORE;
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
