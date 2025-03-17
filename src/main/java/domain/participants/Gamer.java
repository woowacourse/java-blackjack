package domain.participants;

import domain.card.Card;
import domain.card.CardRank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public abstract class Gamer {
    private static final int BLACKJACK_MAX_SCORE = 21;

    private final List<Card> cards;

    protected Gamer() {
        this.cards = new ArrayList<>();
    }

    protected Gamer(Gamer gamer){
        this.cards = new ArrayList<>(gamer.cards);
    }

    public abstract Gamer newInstance();

    public int calculateScore() {
        boolean containAce = cards.stream()
                .anyMatch(Card::isAce);

        int lowAceTotal = cards.stream()
                .mapToInt(card -> card.getCardRank().getValue())
                .sum();

        return calculateAceValue(containAce, lowAceTotal);
    }

    public void addCard(List<Card> addedCards) {
        validateDuplicate(addedCards);
        cards.addAll(addedCards);
    }

    public boolean isBust() {
        int score = calculateScore();
        return score > BLACKJACK_MAX_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    protected boolean isDrawable(int standard) {
        int score = calculateScore();
        return score <= standard;
    }

    private int calculateAceValue(boolean containAce, int lowAceTotal) {
        if (!containAce || lowAceTotal > BLACKJACK_MAX_SCORE) {
            return lowAceTotal;
        }
        int highAceTotal = lowAceTotal - CardRank.ACE.getValue() + CardRank.SPECIAL_ACE.getValue();
        if (highAceTotal > BLACKJACK_MAX_SCORE) {
            return lowAceTotal;
        }
        return highAceTotal;
    }

    private void validateDuplicate(List<Card> addedCards) {
        if (addedCards.size() != new HashSet<>(addedCards).size()) {
            throw new IllegalArgumentException("새로 뽑은 카드에 중복이 있습니다.");
        }
        if (addedCards.stream().anyMatch(this.cards::contains)) {
            throw new IllegalArgumentException("기존 카드와 중복 카드가 있습니다.");
        }
    }
}
