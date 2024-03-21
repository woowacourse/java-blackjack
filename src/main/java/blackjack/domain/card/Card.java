package blackjack.domain.card;

public record Card(CardSuit suit, CardScore score) {

    public int getScore() {
        return score.get();
    }

    public boolean isAce() {
        return score == CardScore.ACE;
    }
}
