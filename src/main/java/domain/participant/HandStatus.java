package domain.participant;

import domain.PlayingCard;

import java.util.List;

public record HandStatus(List<PlayingCard> playingCards, int cardNumberSum) {
}
