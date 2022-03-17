package blackjack.domain.human.humans;

import blackjack.domain.card.cards.CardDeck;
import blackjack.domain.human.Player;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public final class Players {
    private static final String NULL_NAMES_DUPLICATED_ERROR_MESSAGE = "이름은 중복될 수 없습니다.";
    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
        validateNames(getPlayerNames());
    }

    public static Players from(final List<Player> players) {
        return new Players(players);
    }

    public static Players fromNames(final List<String> names) {
        return new Players(toPlayerList(names));
    }

    private static void validateNames(final List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException(NULL_NAMES_DUPLICATED_ERROR_MESSAGE);
        }
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    private static List<Player> toPlayerList(final List<String> names) {
        return names.stream()
                .map(Player::from)
                .collect(Collectors.toList());
    }

    public int size() {
        return players.size();
    }

    public void giveCard(CardDeck cardDeck) {
        for (Player player : players) {
            player.addCard(cardDeck.pop());
        }
    }

    public List<Player> get() {
        return List.copyOf(players);
    }
}
