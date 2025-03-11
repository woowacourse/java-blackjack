package model.card;

public record Card(CardNumber number, CardShape shape) {

    public boolean isSameNumber(final CardNumber number) {
        return this.number == number;
    }

    public int getNumberValue() {
        return number.getNumber();
    }
}
