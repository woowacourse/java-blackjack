package model;

import java.util.ArrayList;
import java.util.List;

public class ParticipantHand {
    private static final int BURST_SCORE_LIMIT = 21;
    private final List<Card> cards;

    public ParticipantHand(){
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean checkBurst() {
        return calculateScoreWithAceAsMinValue() > BURST_SCORE_LIMIT;
    }

    public boolean checkScoreBelow(int upperBound) {
        return calculateScoreWithAceAsMinValue() <= upperBound;
    }

    public int calculateFinalScore() {
        if (canOneAceConvertToMaxValue()){
            int scoreWithAce = calculateScoreWithAceAsMinValue();
            return convertOneAceToMaxValueFrom(scoreWithAce);
        }
        return calculateScoreWithAceAsMinValue();
    }

    private int calculateScoreWithAceAsMinValue() {
        return cards.stream()
                .mapToInt(Card::getCardRankDefaultValue)
                .sum();
    }

    private boolean checkScoreExceptAceBelow(int upperBound) {
        return cards.stream()
                .filter(card -> card.getCardRank() != CardRank.ACE)
                .mapToInt(Card::getCardRankDefaultValue)
                .sum() <= upperBound;
    }

    private boolean canOneAceConvertToMaxValue() {
        int scoreOfAceAsMinValue = calculateAceCount();
        if (scoreOfAceAsMinValue == 0){
            return false;
        }
        int maxScoreOfAce = convertOneAceToMaxValueFrom(scoreOfAceAsMinValue);
        int scoreExceptAceUpperBound = BURST_SCORE_LIMIT - maxScoreOfAce;
        return checkScoreExceptAceBelow(scoreExceptAceUpperBound);
    }

    private int calculateAceCount() {
        return cards.stream()
                .filter(card -> card.getCardRank() == CardRank.ACE)
                .mapToInt(Card::getCardRankDefaultValue)
                .sum();
    }

    private int convertOneAceToMaxValueFrom(int score) {
        return score - CardRank.ACE.getDefaultValue() + CardRank.ACE.getMaxValue();
    }

    public List<Card> getCards() {
        return cards;
    }
}
