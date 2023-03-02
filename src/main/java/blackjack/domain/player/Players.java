package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.exception.DuplicatedPlayerNameException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> names) {
        validateDuplicatedNames(names);
        List<Player> players = new ArrayList<>();
        players.add(new Dealer());
        names.stream()
                .map(Challenger::new)
                .forEach(players::add);
        return new Players(players);
    }

    private static void validateDuplicatedNames(List<String> names) {
        long distinctNameCount = names.stream()
                .distinct()
                .count();

        if (names.size() != distinctNameCount) {
            throw new DuplicatedPlayerNameException();
        }
    }

    public void pickStartCards(CardDeck cardDeck) {
        for (Player player : players) {
            Card card1 = cardDeck.pick();
            Card card2 = cardDeck.pick();
            player.pickStartCards(card1, card2);
        }
    }

    public List<Player> getChallengers() {
        return players.stream()
                .filter(player -> player instanceof Challenger)
                .collect(Collectors.toUnmodifiableList());
    }

    public Player getDealer() {
        return players.stream()
                .filter(player -> player instanceof Dealer)
                .findFirst()
                .orElse(null);
    }
}
