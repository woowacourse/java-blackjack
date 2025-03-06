package model;

import java.util.ArrayList;
import java.util.List;

public class ParticipantHand {
    private final List<Card> cards;

    public ParticipantHand(){
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean checkBurst() {
        return calculateScoreWithAceAsOne() > 21;
    }

    public boolean checkScoreBelow(int upperBound) {
        return calculateScoreWithAceAsOne() <= upperBound;
    }

    public int calculateFinalScore() {
        if (isAceElevenPossible()){
            //TODO : 코드의 의미가 읽히지 않음. 상수화 말고도 개선 방법이 있을까?
            return calculateScoreWithAceAsOne() + 10;
        }
        return calculateScoreWithAceAsOne();
    }

    private int calculateScoreWithAceAsOne() {
        return cards.stream()
                .mapToInt(Card::getCardRankValue)
                .sum();
    }

    private boolean checkScoreExceptAceBelow(int upperBound) {
        return cards.stream()
                .filter(card -> card.getCardRank() != CardRank.ACE)
                .mapToInt(Card::getCardRankValue)
                .sum() <= upperBound;
    }

    private int calculateAceCount() {
        return (int) cards.stream()
                .filter(card -> card.getCardRank() == CardRank.ACE)
                .count();
    }

    private boolean isAceElevenPossible() {
        int countOfAce = calculateAceCount();
        if (countOfAce == 0){
            return false;
        }
        int maxScoreOfAce = countOfAce + 10;
        int scoreExceptAceUpperBound = 21 - maxScoreOfAce;
        //TODO : 개선생각해보기
        return checkScoreExceptAceBelow(scoreExceptAceUpperBound);
    }

    public List<Card> getCards() {
        return cards;
    }
}
