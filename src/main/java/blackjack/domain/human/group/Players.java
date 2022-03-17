package blackjack.domain.human.group;

import blackjack.domain.card.group.CardDeck;
import blackjack.domain.human.Player;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public final class Players {
    private static final String NULL_NAMES_DUPLICATED_ERROR_MESSAGE = "이름은 중복될 수 없습니다.";
    private static final String NAME_SEPARATE_REGEX = ",";
    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<Player> players) {
        return new Players(players);
    }

    public static Players fromText(final String playersText) {
        validateTextInput(playersText);
        return new Players(toPlayerList(playersText));
    }

    private static void validateTextInput(String rawInput) {
        String[] input = rawInput.split(rawInput);
        if (new HashSet<>(Arrays.asList(input)).size() != input.length) {
            throw new IllegalArgumentException(NULL_NAMES_DUPLICATED_ERROR_MESSAGE);
        }
    }

    private static List<Player> toPlayerList(final String input) {
        return Arrays.stream(input.split(NAME_SEPARATE_REGEX))
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

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
