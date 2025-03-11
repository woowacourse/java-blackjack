package model.participant;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardRank;

public class ParticipantHand {
    private static final int BUST_SCORE_LIMIT = 21;
    private static final int BLACKJACK_CONDITION_CARD_COUNT = 2;

    private final List<Card> cards;

    public ParticipantHand() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean checkBust() {
        return calculateScoreWithAceAsMinValue() > BUST_SCORE_LIMIT;
    }

    public boolean checkBlackjack(){
        return cards.size() == BLACKJACK_CONDITION_CARD_COUNT && calculateFinalScore() == BUST_SCORE_LIMIT;
    }

    public boolean checkScoreBelow(int upperBound) {
        return calculateScoreWithAceAsMinValue() <= upperBound;
    }

    public int calculateFinalScore() {
        if (canOneAceConvertToMaxValue()) {
            int scoreWithAce = calculateScoreWithAceAsMinValue();
            return convertOneAceToMaxValueFrom(scoreWithAce);
        }
        return calculateScoreWithAceAsMinValue();
    }

    public Card openFirstCard(){
        return cards.getFirst();
    }

    private int calculateScoreWithAceAsMinValue() {
        return cards.stream()
                .mapToInt(Card::getCardRankDefaultValue)
                .sum();
    }

    private boolean checkScoreExceptAceBelow(int upperBound) {
        return calculateExceptAceScore() <= upperBound;
    }

    private boolean canOneAceConvertToMaxValue() {
        int scoreOfAceAsMinValue = calculateTotalAceScore();
        if (scoreOfAceAsMinValue == 0) {
            return false;
        }
        int maxScoreOfAce = convertOneAceToMaxValueFrom(scoreOfAceAsMinValue);
        int scoreExceptAceUpperBound = BUST_SCORE_LIMIT - maxScoreOfAce;
        return checkScoreExceptAceBelow(scoreExceptAceUpperBound);
    }

    private int calculateTotalAceScore() {
        return cards.stream()
                .filter(Card::isAce)
                .mapToInt(Card::getCardRankDefaultValue)
                .sum();
    }

    private int calculateExceptAceScore() {
        return cards.stream()
                .filter(card -> !card.isAce())
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
