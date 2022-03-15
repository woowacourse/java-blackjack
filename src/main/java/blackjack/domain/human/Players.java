package blackjack.domain.human;

import blackjack.domain.card.CardDeck;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final String NULL_NAMES_DUPLICATED_ERROR_MESSAGE = "이름은 중복될 수 없습니다.";
    private static final String NAME_SEPARATE_REGEX = ",";
    private static final String PLAYER_NAME_DELIMITER = ", ";
    private final List<Player> players;
    
    private Players(final List<Player> players) {
        this.players = players;
    }
    
    public static Players of(final List<Player> players) {
        return new Players(players);
    }
    
    public static Players ofInputText(final String playersText) {
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
                .map(Player::of)
                .collect(Collectors.toList());
    }
    
    public int size() {
        return players.size();
    }
    
    public void giveCard(CardDeck cardDeck) {
        for (Player player : players) {
            player.addCard(cardDeck.draw());
        }
    }
    
    public List<Player> get() {
        return List.copyOf(players);
    }
    
    public String getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(PLAYER_NAME_DELIMITER));
    }
}
