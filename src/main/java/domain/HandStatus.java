package domain;

import java.util.List;

public record HandStatus(List<PlayingCard> playingCards, int cardNumberSum) {
}
