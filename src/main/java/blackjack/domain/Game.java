package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private final User dealer;
    private final List<User> players;
    private Deck deck;

    public Game(List<String> names) {
        dealer = new Dealer();
        players = createPlayer(names);
    }

    private List<User> createPlayer(List<String> names) {
        return names.stream()
            .map(Player::create)
            .collect(Collectors.toList());
    }
}
