package model.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import model.deck.Card;
import model.deck.CardRank;

public final class ParticipantHand {
    private static final int BURST_SCORE_LIMIT = 21;

    private final List<Card> cards;

    public ParticipantHand() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        validateDuplication(card);
        cards.add(card);
    }

    private void validateDuplication(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("중복 카드가 선택되었습니다.");
        }
    }

    public boolean checkBlackJack() {
        return this.cards.size() == 2
                && calculateFinalScore() == 21;
    }

    public boolean checkBurst() {
        return calculateTotalScoreWithAceAsMinValue() > BURST_SCORE_LIMIT;
    }

    public boolean checkScoreBelow(final int upperBound) {
        return calculateTotalScoreWithAceAsMinValue() <= upperBound;
    }

    public int calculateFinalScore() {
        if (canOneAceConvertToMaxValue()) {
            int scoreWithAce = calculateTotalScoreWithAceAsMinValue();
            return convertOneAceToMaxValueFrom(scoreWithAce);
        }
        return calculateTotalScoreWithAceAsMinValue();
    }

    private int calculateTotalScoreWithAceAsMinValue() {
        return cards.stream()
                .mapToInt(Card::getCardRankDefaultValue)
                .sum();
    }

    private boolean checkScoreExceptAceBelow(final int upperBound) {
        return cards.stream()
                .filter(card -> card.getCardRank() != CardRank.ACE)
                .mapToInt(Card::getCardRankDefaultValue)
                .sum() <= upperBound;
    }

    private boolean canOneAceConvertToMaxValue() {
        int aceScoreAsMinValue = calculateAceScoreAsMinValue();
        if (aceScoreAsMinValue == 0) {
            return false;
        }
        int maxScoreOfAce = convertOneAceToMaxValueFrom(aceScoreAsMinValue);
        int scoreExceptAceUpperBound = BURST_SCORE_LIMIT - maxScoreOfAce;
        return checkScoreExceptAceBelow(scoreExceptAceUpperBound);
    }

    private int calculateAceScoreAsMinValue() {
        int aceCount = cards.stream()
                .filter(card -> card.getCardRank() == CardRank.ACE)
                .mapToInt(Card::getCardRankDefaultValue)
                .sum();
        return aceCount * CardRank.ACE.getDefaultValue();
    }

    private int convertOneAceToMaxValueFrom(final int score) {
        return score - CardRank.ACE.getDefaultValue() + CardRank.ACE.getMaxValue();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
