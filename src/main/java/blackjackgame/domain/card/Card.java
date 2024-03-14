package blackjackgame.domain.card;

public record Card(CardName name, CardType cardType) {
    private static final int ACE = 1;

    public boolean isCardNameAce() {
        return name.isCardNumberSame(ACE);
    }
}
