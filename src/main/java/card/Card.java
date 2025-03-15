package card;

public record Card(
        CardNumberType cardNumberType,
        CardType cardType
) {

    public int getNumber() {
        return cardNumberType.getCardNumber();
    }
}
