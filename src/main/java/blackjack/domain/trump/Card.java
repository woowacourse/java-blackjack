package blackjack.domain.trump;

import blackjack.dto.CardDto;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public int toScore() {
        return denomination.toScore();
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public CardDto toDto() {
        return CardDto.of(denomination, suit);
    }
}
