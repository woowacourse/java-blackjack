package domain;

import java.util.HashMap;
import java.util.Map;

public final class Dealer {
    private final CardList cardList;
    private final Score score;
    private final Map<Outcome, Integer> result;
    private int resultScore;

    public Dealer() {
        this.cardList = new CardList();
        this.score = new Score();
        this.resultScore = 0;
        this.result = new HashMap<>();
        
        for (Outcome outcome : Outcome.values()) {
            this.result.put(outcome, 0);
        }
    }

    public void drawCard() {
        Card card = cardList.draw();
        while (isDuplicated(card)) {
            card = cardList.draw();
        }
        saveDrawnCard(card);
    }

    private boolean isDuplicated(Card card) {
        return DuplicationSet.duplicationCards.contains(card.getCard());
    }

    private void saveDrawnCard(Card card) {
        DuplicationSet.duplicationCards.add(card.getCard());
        score.addScore(CardSuitMap.getScore(card.getCard()));
        cardList.add(card);
    }

    public int getCount(Outcome playerOutcome) {
        return result.get(playerOutcome);
    }

    public void addResult(Outcome playerOutcome) {
        result.put(playerOutcome, result.getOrDefault(playerOutcome, 0) + 1);
    }

    public int getResultScore() {
        return resultScore;
    }

    public void setResultScore(int number) {
        resultScore = number;
    }

    public CardList getCardList() {
        return cardList;
    }

    public Score getScore() {
        return score;
    }

    public int getResult() {
        return score.getScore();
    }

    public boolean checkBust() {
        return score.isBust();
    }
}
