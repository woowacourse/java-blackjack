package blackjack.dto;

import blackjack.domain.PlayingCards;

public record DrawResult(
    PlayingCards drewCard,
    PlayingCards drewDeck
) {

    public static DrawResult of(PlayingCards drewCard, PlayingCards drewDeck) {
        return new DrawResult(drewCard, drewDeck);
    }
}
