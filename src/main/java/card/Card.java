package card;

public record Card(
        CardNumberType cardNumberType,
        CardShapeType cardShapeType
) {

    public int getNumber() {
        return cardNumberType.getCardNumber();
    }
}
