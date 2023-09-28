package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players createDefault() {
        List<Player> players = new ArrayList<>();
        players.add(Player.from(Name.createDealer()));
        return new Players(players);
    }

    public void joinPlayer(String input) {
        List<Name> splitNames = Name.createSplitNames(input);

        players.addAll(splitNames.stream()
                .map(Player::from)
                .collect(Collectors.toList()));
    }

    public String getPlayerNamesExceptDealer() {
        List<Name> names = players.stream()
                .map(Player::getName)
                .filter(Name::isNotDealer)
                .collect(Collectors.toList());

        return Name.chainingNames(names);
    }

    public void giveInitialCards(Deck deck, int count) {
        players.forEach(player -> {
            List<Card> initCards = deck.getCards(count);
            player.selectCardsFromDeck(initCards);
        });
    }
}
