package blackjack.domain;

public class GameResponse {

    private final String name;
    private final Deck deck;

    public GameResponse(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
    }

    public String getName() {
        return name;
    }

    public Deck getDeck() {
        return deck;
    }
}
