package domain.card;

public record Card(
        CardDenomination denomination,
        CardEmblem emblem
) {

    public static Card of(CardDenomination denomination, CardEmblem emblem) {
        return new Card(denomination, emblem);
    }

    public String toDisplay() {
        return denomination.getDisplayName() + emblem.displayName();
    }

    public int getScore() {
        return denomination.getScore();
    }

    public boolean isAce() {
        return denomination.isAce();
    }

}
