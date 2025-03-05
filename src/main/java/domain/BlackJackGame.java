package domain;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {

    private final Deck deck;

    public BlackJackGame(Deck deck) {
        validate(deck);
        this.deck = deck;
    }

    private void validate(Deck deck) {
        validateNotNull(deck);
    }

    private void validateNotNull(Deck deck) {
        if (deck == null) {
            throw new IllegalStateException("심판은 덱을 가지고 있어야합니다.");
        }
    }

    public List<Player> createPlayers(List<String> names) {
        List<Player> players = new ArrayList<>();

        names.forEach(name -> {
            TrumpCard firstCard = deck.draw();
            TrumpCard secondCard = deck.draw();
            players.add(new Player(name, Hand.of(firstCard, secondCard)));
        });

        return players;
    }

    public Dealer createDealer() {
        TrumpCard firstCard = deck.draw();
        TrumpCard secondCard = deck.draw();
        
        return new Dealer(Hand.of(firstCard, secondCard));
    }
}
