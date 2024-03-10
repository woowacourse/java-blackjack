package blackjack.domain.card;

public record Card(CardValue cardValue, CardSymbol cardSymbol) {

    public boolean isAce() {
        return cardValue.isAce();
    }

    public int getCardScore() {
        return cardValue.getScore();
    }
}
