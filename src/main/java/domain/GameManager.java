package domain;

import java.util.ArrayList;
import java.util.List;
import view.InputView;

public class GameManager {
    private static final int MAX_PLAYER = 8;
    private static final int BURST_THRESHOLD = 21;

    private final Dealer dealer;
    private final List<Player> players;
    private final Deck deck;

    public GameManager(List<Player> players) {
        this.dealer = initDealer();
        this.players = players;
        this.deck = new Deck();
    }

    private Dealer initDealer() {
        Name name = new Name("딜러");
        Dealer dealer = new Dealer(name);
        return dealer;
    }

    public static boolean isOverBurstThreshold(int score) {
        if (score > BURST_THRESHOLD) {
            return true;
        }
        return false;
    }

    public static void validatePlayersNumber(List<String> playerNames) {
        validateMinimumPlayers(playerNames);
        validateMaximumPlayers(playerNames);
    }

    private static void validateMaximumPlayers(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("플레이어의 수는 8명을 초과할 수 없습니다.");
        }
    }

    private static void validateMinimumPlayers(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("플레이어의 수는 1명 이상이어야 합니다.");
        }
    }
}
