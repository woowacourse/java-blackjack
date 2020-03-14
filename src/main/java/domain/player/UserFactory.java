package domain.player;

import domain.card.Cards;

import java.util.List;
import java.util.stream.Collectors;

public class UserFactory {
    private UserFactory() {
    }

    public static Users createPlayers(Cards cards, List<String> playerNames) {
        List<User> men = playerNames.stream()
                .map(name -> new Player(name, cards.pop(), cards.pop()))
                .collect(Collectors.toList());

        men.add(new Dealer(cards.pop(), cards.pop()));
        return new Users(men);
    }
}
