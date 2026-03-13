package domain.card;

public record Card(int card) {
    public String getCardName() {
        int shape = card / 13;
        int number = card % 13;
        return CardSuitMap.getSuit(shape) + CardSuitMap.getRank(number);
    }

    public int getScore() {
        int cardNumber = card % 13;
        if (1 <= cardNumber && cardNumber <= 9) {
            return cardNumber + 1;
        }
        if (cardNumber >= 10) {
            return 10;
        }
        return 11;
    }

    public boolean isAce() {
        return card % 13 == 0;
    }
}
