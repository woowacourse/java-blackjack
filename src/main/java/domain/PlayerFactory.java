package domain;

import domain.card.Cards;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.player.User;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerFactory {
    private PlayerFactory() {
    }

    private static class Singleton {
        private static final PlayerFactory instance = new PlayerFactory();
    }

    public static PlayerFactory getInstance() {
        return Singleton.instance;
    }

    public Players createPlayers(Cards cards, List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(name -> new User(name, cards.pop(), cards.pop()))
                .collect(Collectors.toList());
        players.add(new Dealer(cards.pop(), cards.pop()));
        return new Players(players);
    }
}
