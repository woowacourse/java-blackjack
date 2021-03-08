package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.result.Result;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<String> playerName) {
        validateDuplicate(playerName);
        final List<Player> players = playerName.stream()
                .map(name -> new Player(name))
                .collect(Collectors.toList());
        return new Players(players);
    }

    private static void validateDuplicate(final List<String> playerName) {
        final int distinctName = (int) playerName.stream().distinct().count();
        if (distinctName != playerName.size()) {
            throw new IllegalArgumentException("입력된 플레이어의 이름이 중복됩니다.");
        }
    }

    public void receiveInitialCard(final CardDeck cardDeck) {
        for (Player player : players) {
            player.receiveInitialCard(cardDeck);
        }
    }

    public Map<Result, Integer> checkDealerResult(final Dealer dealer) {
        final Map<Result, Integer> dealerResult = new HashMap<>();
        for (Player player : players) {
            Result result = dealer.checkResult(player);
            dealerResult.put(result, dealerResult.getOrDefault(result, 0) + 1);
        }
        return Collections.unmodifiableMap(dealerResult);
    }

    public Map<Player, Result> checkAllPlayerResult(final Dealer dealer) {
        final Map<Player, Result> playerResult = new LinkedHashMap<>();
        for (Player player : players) {
            playerResult.put(player, player.checkResult(dealer));
        }
        return Collections.unmodifiableMap(playerResult);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
