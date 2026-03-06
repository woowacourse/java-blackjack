package blackjack.model;

public class Player extends Participant{

    private final String name;

    private Player(String name, Hands hands) {
        super(hands);
        this.name = name;
    }

    public static Player of(String name) {
        return new Player(name, Hands.empty());
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void pickInitCards(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
        hands.addCard(cardDeck.pick());
    }

}
