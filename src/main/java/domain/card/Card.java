package domain.card;

public record Card(CardName name, CardType cardType) {

    public boolean isCardNameAce() {
        return name.isAce();
    }
}
