package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {
    private static final int MAX_PLAYER_COUNT = 5;
    private final Map<Player, GameResult> players = new HashMap<>();
    private final Deck deck;

    public Game(List<String> playerNames) {
        deck = new Deck();
        validatePlayerCount(playerNames);
        validateDuplicateName(playerNames);
        playerNames.forEach(this::registerPlayer);
    }

    private void registerPlayer(String playerName) {
        Card firstCard = deck.random(new RandomNumberGenerator());
        Card secondCard = deck.random(new RandomNumberGenerator());
        CardHand cardHand = new CardHand(Set.of(firstCard, secondCard));
        Player player = new Player(playerName, cardHand);
        players.put(player, GameResult.NONE);
    }

    private void validatePlayerCount(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 참여자는 최대 5명입니다.");
        }
    }

    private void validateDuplicateName(List<String> playerNames) {
        if (playerNames.size() != Set.of(playerNames).size()) {
            throw new IllegalArgumentException("[ERROR] 이름은 중복될 수 없습니다.");
        }
    }
}
