package dto;

import domain.playingcard.PlayingCard;
import domain.playingcard.PlayingCardShape;
import domain.playingcard.PlayingCardValue;

public record PlayingCardDto(PlayingCardShape playingCardShape, PlayingCardValue playingCardValue) {

    public static PlayingCardDto of(PlayingCard playingCard) {
        return new PlayingCardDto(playingCard.getPlayingCardShape(), playingCard.getPlayingCardValue());
    }
}
