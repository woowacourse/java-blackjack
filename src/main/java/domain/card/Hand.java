package domain.card;

import domain.card.vo.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BURST_SCORE = 21;
    public static final int ACE_ADVANTAGE_SCORE = 10;
    private static final int MIN_SIZE = 2;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        validateCardsSize(cards);
        this.cards = cards;
    }

    public static Hand createFromDeck(Deck deck) {
        List<Card> cards = new ArrayList<>();
        cards.add(deck.drawCard());
        cards.add(deck.drawCard());
        return new Hand(cards);
    }

    private void validateCardsSize(List<Card> cards) {
        if (cards.size() != MIN_SIZE) {
            throw new IllegalArgumentException("처음 게임 시작 시, 두장을 나눠줘야한다.");
        }
    }

    public Integer getScore() {
        int score = cards.stream()
                .map(Card::getScore)
                .reduce(Integer::sum)
                .orElse(0);

        if (hasAce(cards) && !aceAdvantageIsBurst(score)) {
            return score + ACE_ADVANTAGE_SCORE;
        }

        return score;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    private boolean hasAce(List<Card> hands) {
        return hands.stream()
                .anyMatch(Card::isAce);
    }

    private boolean aceAdvantageIsBurst(int score) {
        return score + ACE_ADVANTAGE_SCORE > BURST_SCORE;
    }

    public boolean isBurst() {
        return getScore() > BURST_SCORE;
    }
}
