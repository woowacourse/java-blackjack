package blackjack.domain;

public class Hand {

    private final PlayingCards cards;

    public Hand() {
        cards = new PlayingCards();
    }

    public String getCardSnapshot() {
        return cards.getSnapshot();
    }

    public String getFirstSnapshot() {
        if (cards.isCardRemain()) {
            return cards.getFirstSnapshot();
        }
        throw new IllegalArgumentException("남은 카드가 없습니다.");
    }

    public String addCard(PlayingCards receivedCards) {
        cards.add(receivedCards);
        return getCardSnapshot();
    }

    public int getTotalScore() {
        return cards.getCards()
                .stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public int getResultScore(int bustedScore) {
        return calculateResultScore(bustedScore);
    }

    public int calculateResultScore(int threshold) {
        int totalScore = getTotalScore();
        boolean busted = isBusted(threshold, totalScore);
        if (busted) {
            return calculateWithAce(totalScore, countAce(), threshold);
        }
        return totalScore;
    }

    public int countAce() {
        return (int) cards.getCards()
                .stream()
                .filter(Card::isAce)
                .count();
    }
    
    public boolean isBusted(int threshold, int scoreSum) {
        return scoreSum > threshold;
    }

    private int calculateWithAce(int scoreSum, int remainAce, int threshold) {
        if (remainAce == 0) {
            return scoreSum;
        }
        int calculatedScore = scoreSum - 10;
        if (calculatedScore <= threshold) {
            return calculatedScore;
        }
        return calculateWithAce(calculatedScore, remainAce - 1, threshold);
    }
}
