package model;

import java.util.ArrayList;
import java.util.List;

public class ParticipantHand {
    private final List<Card> cards;

    public ParticipantHand(){
        this.cards = new ArrayList<>();
    }
    public ParticipantHand(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScoreSum() {
        //TODO : ace에 대한 상황을 덜 고려함
        return cards.stream()
                .mapToInt(card -> card.getCardRankValue())
                .sum();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Card getFirstHand(){
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean checkBurst() {
        int score = calculateScoreSum();
        for (Card card : cards) {
            if (card.getCardRank() == CardRank.ACE) {
                score+=1;
            }
        }
        return score > 21;
    }

    public boolean isAceElevenPossible() {
        int countOfAce = calculateAceCount();
        if (countOfAce == 0){
            return false;
        }
        int maxScoreOfAce = countOfAce + 10;
        int scoreExceptAceUpperBound = 21 - maxScoreOfAce;
        //TODO : 개선생각해보기
        return checkScoreExceptAceBelow(scoreExceptAceUpperBound);
    }

    public int calculateFinalScore() {
        if (isAceElevenPossible()){
            //TODO : 코드의 의미가 읽히지 않음. 상수화 말고도 개선 방법이 있을까?
            return calculateScoreSum() + 10;
        }
        return calculateScoreSum();
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
}
