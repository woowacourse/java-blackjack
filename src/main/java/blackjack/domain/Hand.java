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

    public int getDrawableScore(int threshold) {
        boolean busted = isBusted(threshold);
        int scoreSum = calculateScoreSum();
        if (busted) {
            int aceCount = countAce();
            return calculateWithAce(threshold, scoreSum, aceCount);
        }
        return scoreSum;
    }

    public int calculateScoreSum() {
        return cards.getCards()
                .stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public int getTotalScore(int bustedScore) {
        return calculateResultScore(bustedScore);
    }

    private int calculateResultScore(int threshold) {
        boolean busted = isBusted(threshold);
        if (busted) {
            return calculateBustedScore(threshold);
        }
        return calculateScoreSum();
    }

    public boolean isBusted(int threshold) {
        int scoreSum = calculateScoreSum();
        return threshold <= scoreSum;
    }

    private int calculateBustedScore(int threshold) {
        int aceCount = countAce();
        if (aceCount > 0) {
            int scoreSum = calculateScoreSum();
            return calculateWithAce(threshold, scoreSum, aceCount);
        }
        return 0;
    }

    public int countAce() {
        return (int) cards.getCards()
                .stream()
                .filter(Card::isAce)
                .count();
    }

    private int calculateWithAce(int threshold, int scoreSum, int aceCount) {
        if (aceCount == 0) {
            return scoreSum;
        }
        int calculatedScore = scoreSum - 10;
        if (calculatedScore <= threshold) {
            return calculatedScore;
        }
        return calculateWithAce(calculatedScore, aceCount - 1, threshold);
    }
}
