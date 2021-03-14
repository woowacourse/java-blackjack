package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.result.Result;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private static final int MAX_PLAYER_LIMIT = 8;

    private final List<Player> players;

    public Players(final List<Player> players) {
        validateMaxPlayerNumber(players);
        validateDuplicate(players);
        this.players = new ArrayList<>(players);
    }

    public static Players of(final List<String> playerName) {
        final List<Player> players = playerName.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Players(players);
    }

    public static Players of(final List<String> playerName, final List<Integer> playerMoney) {
        validateSize(playerName.size(), playerMoney.size());
        final List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerName.size(); i++) {
            players.add(new Player(playerName.get(i), playerMoney.get(i)));
        }
        return new Players(players);
    }

    private void validateDuplicate(final List<Player> players) {
        final boolean duplicate = (int) players.stream()
                .distinct()
                .count() != players.size();

        if (duplicate) {
            throw new IllegalArgumentException("입력된 플레이어의 이름이 중복됩니다.");
        }
    }

    private void validateMaxPlayerNumber(final List<Player> players) {
        if (players.size() > MAX_PLAYER_LIMIT) {
            throw new IllegalArgumentException("플레이어는 최대 8명까지 허용합니다.");
        }
    }

    private static void validateSize(final int nameCount, final int moneyCount) {
        if (nameCount != moneyCount) {
            throw new IllegalArgumentException("입력 받은 플레이어의 수와 베팅 금액의 수가 같지 않습니다");
        }
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
