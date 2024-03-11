package blackjack.model.card;

import blackjack.model.cardgenerator.CardGenerator;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Cards {
    private static final int MAX_CARDS_TOTAL = 21;
    private static final int ACE_ADJUSTMENT = 10;

    private final List<Card> cards;

    Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Cards(final CardGenerator cardGenerator) {
        this.cards = new ArrayList<>(deal(cardGenerator));
    }

    private List<Card> deal(final CardGenerator cardGenerator) {
        return List.of(cardGenerator.pick(), cardGenerator.pick());
    }

    public void addCard(final CardGenerator cardGenerator) {
        cards.add(cardGenerator.pick());
    }

    public Score calculateCardsTotalScore() {
        List<Score> scores = getScores();
        Score totalScore = Score.from(scores);
        if (hasAce() && canBeAdjusted(totalScore)) {
            return adjustTotalForAce(totalScore);
        }
        return totalScore;
    }

    private List<Score> getScores() {
        return cards.stream()
                .map(Card::getDenomination)
                .map(Denomination::getScore)
                .toList();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.getDenomination().isAce());
    }

    private boolean canBeAdjusted(final Score total) {
        return total.plus(ACE_ADJUSTMENT)
                .equalToOrLessThan(MAX_CARDS_TOTAL);
    }

    private Score adjustTotalForAce(final Score total) {
        return total.plus(ACE_ADJUSTMENT);
    }

    public boolean isBlackJack() {
        return calculateCardsTotalScore().equalTo(MAX_CARDS_TOTAL);
    }

    public boolean isBust() {
        return calculateCardsTotalScore().greaterThan(MAX_CARDS_TOTAL);
    }

    public int getSize() {
        return cards.size();
    }

    public Card get(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return unmodifiableList(cards);
    }
}
