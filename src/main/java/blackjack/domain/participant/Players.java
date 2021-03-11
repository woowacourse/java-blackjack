package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.result.Result;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        validateDuplicate(players.stream()
                .map(Participant::getName)
                .collect(Collectors.toList()));
        this.players = players;
    }

    public static Players of(final List<String> playerName) {
        validateDuplicate(playerName);
        final List<Player> players = playerName.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Players(players);
    }

    public static Players of(final List<String> playerName, final List<Double> playerMoney) {
        validateDuplicate(playerName);
        validateMoney(playerMoney);
        final int playerCount = validateSize(playerName.size(), playerMoney.size());

        final List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player(playerName.get(i), playerMoney.get(i)));
        }
        return new Players(players);
    }

    private static void validateDuplicate(final List<String> playerName) {
        final int distinctName = (int) playerName.stream()
                .distinct()
                .count();

        if (distinctName != playerName.size()) {
            throw new IllegalArgumentException("입력된 플레이어의 이름이 중복됩니다.");
        }
    }

    private static void validateMoney(final List<Double> playerMoney) {
        final int belowZero = (int) playerMoney.stream()
                .filter(money -> money < 0)
                .count();

        if (belowZero != 0) {
            throw new IllegalArgumentException("베팅 금액은 0 이상이여야 합니다.");
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

    public Map<Player, Result> generateEveryPlayerResult(final Dealer dealer) {
        final Map<Player, Result> playerResult = new LinkedHashMap<>();
        for (Player player : players) {
            playerResult.put(player, player.generateResult(dealer));
        }
        return Collections.unmodifiableMap(playerResult);
    }

    public Map<Player, Double> generateEveryPlayerProfit(final Dealer dealer) {
        final Map<Player, Double> playerProfit = new LinkedHashMap<>();
        for (Player player : players) {
            playerProfit.put(player, player.generateProfit(dealer));
        }
        return Collections.unmodifiableMap(playerProfit);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
