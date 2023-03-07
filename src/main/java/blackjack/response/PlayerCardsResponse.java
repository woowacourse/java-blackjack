package blackjack.response;

import java.util.List;

public class PlayerCardsResponse {

    private final String name;
    private final List<CardResponse> cards;

    public PlayerCardsResponse(final String name, final List<CardResponse> cards) {
        this.name = name;
        this.cards = cards;
    }


    public String getName() {
        return name;
    }

    public List<CardResponse> getCards() {
        return cards;
    }
}
