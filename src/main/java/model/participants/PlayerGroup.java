package model.participants;

import static view.InputView.getContinueOrNot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.casino.Deck;
import model.casino.HitOrHoldPolicy;

public class PlayerGroup {
    private static final String DELIMITER = ",";
    private static final String MAX_PLAYER_EXCEPTION = "플레이어는 5명까지만 입력해주세요.";
    private static final int MAX_PLAYER_SIZE = 5;
    private static final String NO = "n";

    private final List<Player> players = new ArrayList<>();

    public PlayerGroup(String playersInput) {
        enterPlayer(playersInput);
    }

    private void enterPlayer(String playersInput) {
        List<String> playerNames = Arrays.asList(playersInput.split(DELIMITER));
        validateMaxPlayer(playerNames);
        for (String playerName : playerNames) {
            players.add(new Player(playerName.trim()));
        }
    }

    public void playersTurn(Deck deck) {
        for (Player player : players) {
            player.bustCheckOfHitOrHold(deck, new HitOrHoldPolicy() {
                @Override
                public boolean hold() {
                    return getContinueOrNot(player).equalsIgnoreCase(NO);
                }
            });
        }
    }

    private void validateMaxPlayer(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_SIZE) {
            throw new IllegalArgumentException(MAX_PLAYER_EXCEPTION);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
