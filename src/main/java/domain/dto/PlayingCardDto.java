package domain.dto;

import domain.PlayingCard;
import domain.PlayingCardShape;
import domain.PlayingCardValue;

public record PlayingCardDto(PlayingCardShape playingCardShape, PlayingCardValue playingCardValue) {
    public static PlayingCardDto of(PlayingCard playingCard) {
        return new PlayingCardDto(playingCard.playingCardShape(), playingCard.playingCardValue());
    }
}
