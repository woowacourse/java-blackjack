package domain.participant;

import exception.BlackjackException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;
import domain.card.Card;

public class Players {

    private static final int PLAYER_MIN_COUNT = 2;
    private static final int PLAYER_MAX_COUNT = 8;
    public static final String PLAYER_DUPLICATED = "게임 참가자의 이름은 중복 되어선 안됩니다.";
    public static final String DIFFERENT_SIZE_PLAYER_BET_AMOUNT = "플레이어 목록과 베팅금액 목록의 크기가 다릅니다.";
    public static final String PLAYER_COUNT_OUT_OF_RANGE =
            String.format("게임 참가자의 수는 %d~%d명 사이여야 합니다.", PLAYER_MIN_COUNT, PLAYER_MAX_COUNT);
    public static final String NOT_FOUND_PLAYER = "플레이어를 찾을 수 없습니다.";

    private final List<Player> players;

    public Players(List<PlayerName> names, List<BetAmount> betAmounts) {
        validatePlayerAndBetAmountSize(names, betAmounts);
        validatePlayerNames(names);
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), betAmounts.get(i)));
        }
        this.players = players;
    }

    private void validatePlayerAndBetAmountSize(List<PlayerName> names, List<BetAmount> betAmounts) {
        if (names.size() != betAmounts.size()) {
            throw new BlackjackException(DIFFERENT_SIZE_PLAYER_BET_AMOUNT);
        }
    }

    private void validatePlayerNames(List<PlayerName> names) {
        validatePlayerDuplication(names);
        validatePlayerCount(names.size());
    }

    private void validatePlayerDuplication(List<PlayerName> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new BlackjackException(PLAYER_DUPLICATED);
        }
    }

    private void validatePlayerCount(int playerCount) {
        if (!(PLAYER_MIN_COUNT <= playerCount && playerCount <= PLAYER_MAX_COUNT)) {
            throw new BlackjackException(PLAYER_COUNT_OUT_OF_RANGE);
        }
    }

    public Player getPlayer(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new BlackjackException(NOT_FOUND_PLAYER));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void drawInitialCards(Supplier<Card> cardSupplier) {
        for (Player player : players) {
            player.drawInitialCards(cardSupplier);
        }
    }

    public void addCard(String name, Card card) {
        Player player = getPlayer(name);
        player.drawCard(card);
    }

    public boolean isPlayerBust(String name) {
        return getPlayer(name).isBust();
    }
}
