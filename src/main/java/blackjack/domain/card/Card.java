package blackjack.domain.card;

public record Card(CardValue cardValue, CardSymbol cardSymbol) {

    public boolean isAce() {
        return this.cardValue.isAce();
    }

    public int getCardScore() {
        return this.cardValue.getScore();
    }
}
