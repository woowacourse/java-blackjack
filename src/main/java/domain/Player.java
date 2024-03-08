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

    public int calculateResultScore() {
        return hand.calculateScore(BLACKJACK_SCORE);
    }

    public boolean hasMoreScore(final Dealer dealer) {
        return calculateResultScore() > dealer.calculateResultScore();
    }

    public boolean hasSameScore(final Dealer dealer) {
        return calculateResultScore() == dealer.calculateResultScore();
    }

    public boolean hasMoreCard(final Dealer dealer) {
        return getTotalSize() > dealer.getTotalSize();
    }

    public boolean isAbleToDrawCard() {
        return hand.calculateScore(BLACKJACK_SCORE) < BLACKJACK_SCORE;
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
