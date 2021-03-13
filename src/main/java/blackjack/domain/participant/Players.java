package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.result.Result;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private static final int MAX_PLAYER_LIMIT = 8;

    private final List<Player> players;

    public Players(final List<Player> players) {
        validateDuplicate(players);
        validateMaxPlayerNumber(players);
        this.players = new ArrayList<>(players);
    }

    public static Players of(final List<String> playerName) {
        validateDuplicate(playerName);
        validateMaxPlayerNumber(playerName);
        final List<Player> players = playerName.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Players(players);
    }

    public static Players of(final List<String> playerName, final List<Integer> playerMoney) {
        validateDuplicate(playerName);
        validateMaxPlayerNumber(playerName);
        final int playerCount = validateSize(playerName.size(), playerMoney.size());

        final List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player(playerName.get(i), playerMoney.get(i)));
        }
        return new Players(players);
    }

    private static void validateDuplicate(final List<?> players) {
        final int distinctPlayers = (int) players.stream()
                .distinct()
                .count();

        if (distinctPlayers != players.size()) {
            throw new IllegalArgumentException("입력된 플레이어의 이름이 중복됩니다.");
        }
    }

    private static void validateMaxPlayerNumber(final List<?> players) {
        if (players.size() > MAX_PLAYER_LIMIT) {
            throw new IllegalArgumentException("플레이어는 최대 8명까지 허용합니다.");
        }
    }

    private static int validateSize(final int nameCount, final int moneyCount) {
        if (nameCount != moneyCount) {
            throw new IllegalArgumentException("입력 받은 플레이어의 수와 베팅 금액의 수가 같지 않습니다");
        }
        return nameCount;
    }

    public void receiveInitialCard(final CardDeck cardDeck) {
        for (Player player : players) {
            player.receiveInitialCard(cardDeck);
        }
    }

    public Map<Player, Integer> generateEveryPlayerProfit(final Dealer dealer) {
        final Map<Player, Integer> playerProfit = new LinkedHashMap<>();
        for (Player player : players) {
            playerProfit.put(player, player.generateProfit(dealer));
        }
        return Collections.unmodifiableMap(playerProfit);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
