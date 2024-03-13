package domain;

import java.util.List;

public class Player {
    private static final int BLACKJACK_SCORE = 21;

    private final Hand hand;

    public Player() {
        hand = new Hand();
    }

    public Player(final Hand hand) {
        this.hand = hand;
    }

    public void drawTwoCard(final int first, final int second) {
        hand.saveCards(List.of(Deck.pick(first), Deck.pick(second)));
    }

    public void drawOneCard(final int cardAt) {
        hand.saveCard(Deck.pick(cardAt));
    }

    public int calculateRoundScore() {
        return hand.calculateScoreWhileRound();
    }

    public int calculateResultScore() {
        return hand.calculateScoreAfterRound(BLACKJACK_SCORE);
    }

    public boolean hasMoreScoreThan(final Dealer dealer) {
        return calculateResultScore() > dealer.calculateResultScore();
    }

    public boolean hasSameScoreAs(final Dealer dealer) {
        return calculateResultScore() == dealer.calculateResultScore();
    }

    public boolean hasLessCardThan(final Dealer dealer) {
        return getHandSize() <= dealer.getHandSize();
    }

    public boolean isNotAbleToDrawCard() {
        return hand.calculateScoreWhileRound() >= BLACKJACK_SCORE;
    }

    public boolean isBusted() {
        return calculateResultScore() > BLACKJACK_SCORE;
    }

    public int getHandSize() {
        return hand.size();
    }

    public Hand getHand() {
        return hand;
    }
}
