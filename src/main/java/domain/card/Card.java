package domain.card;

public record Card(
        CardDenomination denomination,
        CardEmblem emblem
) {

    public static Card of(CardDenomination denomination, CardEmblem emblem) {
        return new Card(denomination, emblem);
    }

    public String openCard() {
        return denomination.getName() + emblem.getName();
    }

    public int getScore() {
        return denomination.getScore();
    }

    public boolean isAce() {
        return denomination.isAce();
    }

}
