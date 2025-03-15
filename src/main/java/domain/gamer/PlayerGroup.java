package domain.gamer;

import domain.GameResult;
import domain.card.Card;
import domain.card.CardGroup;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PlayerGroup {

    private final List<Player> players;

    public PlayerGroup(final List<Player> players) {
        this.players = players;
    }

    public static PlayerGroup of(final List<String> playerNames) {
        validateDuplicate(playerNames);
        final List<Player> players = playerNames.stream()
                .map(playerName -> new Player(playerName, new CardGroup()))
                .toList();
        return new PlayerGroup(players);
    }


    public Map<String, GameResult> calculatePlayersGameResult(final Dealer dealer) {
        Map<String, GameResult> resultMap = new HashMap<>();
        for (Player player : players) {
            resultMap.put(player.getName(), calculateResult(player, dealer));
        }
        return resultMap;
    }

    public List<Card> getCardsByName(final String playerName) {
        return findByPlayerByName(playerName).getCards();
    }

    public boolean isBustByPlayerName(final String playerName) {
        return findByPlayerByName(playerName).isBust();
    }

    public void giveCardByName(final String playerName, final Card card) {
        findByPlayerByName(playerName).giveCard(card);
    }

    public int getBattingOfReturnByName(final String playerName) {
        final Player player = findByPlayerByName(playerName);
        return player.getBattingAmount();
    }

    private Player findByPlayerByName(final String playerName) {
        return players.stream()
                .filter(player -> Objects.equals(playerName, player.getName()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 플에이어를 찾을 수 없습니다."));
    }

    private GameResult calculateResult(final Player player, final Dealer dealer) {
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        return player.calculateGameResult(dealer.calculateScore());
    }

    private static void validateDuplicate(final List<String> playerNames) {
        if (isDuplicate(playerNames)) {
            throw new IllegalArgumentException("[ERROR] 플레어는 중복될 수 없습니다.");
        }
    }

    private static boolean isDuplicate(final List<String> playerNames) {
        return playerNames.stream()
                .distinct()
                .count() != playerNames.size();
    }

    public List<Player> getPlayers() {
        return players.stream()
                .map(Player::newInstance)
                .toList();
    }
}
