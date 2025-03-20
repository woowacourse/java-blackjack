package blackjack.domain.card;

public record Card(
        CardType type,
        CardNumber number
) {

    public boolean isAce() {
        return number == CardNumber.ACE;
    }
}
