package domain.player;

import domain.card.Cards;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Users {
    private final List<User> users;

    public Users(Cards cards, List<String> playerNames) {
        this.users = playerNames.stream()
                .map(name -> new Player(name, cards.giveCard(), cards.giveCard()))
                .collect(Collectors.toList());
        users.add(new Dealer(cards.giveCard(), cards.giveCard()));
    }

    public Dealer getDealer() {
        return (Dealer) users.stream()
                .filter(player -> player instanceof Dealer)
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(users.stream()
                .filter(player -> player instanceof Player)
                .map(player -> (Player) player)
                .collect(Collectors.toList()));
    }

}
