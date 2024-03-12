package blackjack.model.card;

import blackjack.model.cardgenerator.CardGenerator;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Cards {
    private static final Score MAX_CARDS_TOTAL = Score.from(21);
    private static final Score ACE_ADJUSTMENT = Score.from(10);

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
        Score totalScore = sumAllScores();
        if (hasAce() && canBeAdjusted(totalScore)) {
            return adjustTotalForAce(totalScore);
        }
        return totalScore;
    }

    private Score sumAllScores() {
        return cards.stream()
                .map(Card::getDenomination)
                .map(Denomination::getScore)
                .reduce(Score.from(0), Score::plus);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private boolean canBeAdjusted(final Score totalScore) {
        return totalScore.plus(ACE_ADJUSTMENT)
                .equalToOrLessThan(MAX_CARDS_TOTAL);
    }

    private Score adjustTotalForAce(final Score totalScore) {
        return totalScore.plus(ACE_ADJUSTMENT);
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
