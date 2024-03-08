package domain;

import java.util.List;

public class Player {
    private static final int BLACKJACK_SCORE = 21;

    private final String name;
    private final Hand hand;

    public Player(final String name) {
        validateName(name);
        this.name = name;
        hand = new Hand();
    }

    private void validateName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름으로 빈 문자열이 입력되었습니다.");
        }
    }

    public void pickTwoCards(final Deck deck) {
        hand.saveCards(List.of(deck.pick(), deck.pick()));
    }

    public void pickOneCard(final Deck deck) {
        hand.saveCard(deck.pick());
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

    public boolean isAbleToDrawCard() {
        return hand.calculateScoreWhileRound() < BLACKJACK_SCORE;
    }

    public boolean isBusted() {
        return calculateResultScore() > BLACKJACK_SCORE;
    }

    public int getHandSize() {
        return hand.size();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
