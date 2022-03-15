package blackjack.domain.result;

import blackjack.domain.card.PlayingCards;

public class GameResponse {

    private final String name;
    private final PlayingCards playingCards;

    public GameResponse(String name, PlayingCards playingCards) {
        this.name = name;
        this.playingCards = playingCards;
    }

    public boolean isDealer(String name) {
        return this.name.equals(name);
    }

    public String getName() {
        return name;
    }

    public PlayingCards getDeck() {
        return playingCards;
    }
}
