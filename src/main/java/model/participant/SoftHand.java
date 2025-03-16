package model.participant;

import java.util.List;
import model.deck.Card;
import model.deck.CardRank;

public final class SoftHand extends ParticipantHand{
    private static final int BURST_SCORE_LIMIT = 21;

    public SoftHand(List<Card> cards) {
        super(cards);
    }

    public SoftHand() {
        super();
    }

    @Override
    public int calculateFinalScore() {
        int defaultScore = calculateDefaultScore();
        int maxScore = convertOneAceToMaxValueFrom(defaultScore);
        if (maxScore <= BURST_SCORE_LIMIT) {
            return maxScore;
        }
        return defaultScore;
    }

    @Override
    public ParticipantHand cloneToSoftHand() {
        return this;
    }

    private int convertOneAceToMaxValueFrom(final int score) {
        return score - CardRank.ACE.getDefaultValue() + CardRank.ACE.getMaxValue();
    }

//    private boolean canOneAceConvertToMaxValue() {
//        int aceScoreAsMinValue = calculateAceScoreAsMinValue();
//        int maxScoreOfAce = convertOneAceToMaxValueFrom(aceScoreAsMinValue);
//        int scoreExceptAceUpperBound = BURST_SCORE_LIMIT - maxScoreOfAce;
//        return checkScoreExceptAceBelow(scoreExceptAceUpperBound);
//    }

//    private int calculateAceScoreAsMinValue() {
//        int aceCount = cards.stream()
//                .filter(card -> card.getCardRank() == CardRank.ACE)
//                .mapToInt(Card::getCardRankDefaultValue)
//                .sum();
//        return aceCount * CardRank.ACE.getDefaultValue();
//    }

//    private boolean checkScoreExceptAceBelow(final int upperBound) {
//        return cards.stream()
//                .filter(card -> card.getCardRank() != CardRank.ACE)
//                .mapToInt(Card::getCardRankDefaultValue)
//                .sum() <= upperBound;
//    }
}
