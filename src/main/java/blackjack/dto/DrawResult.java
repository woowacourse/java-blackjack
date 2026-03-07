package blackjack.dto;

import blackjack.domain.PlayingCards;

public record DrawResult(
    PlayingCards drewCard,
    PlayingCards drewDeck
) {

    public static DrawResult of(PlayingCards drewCards, PlayingCards playingCards) {
        return new DrawResult(drewCards, playingCards);
    }
}
