package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.BlackjackResult;
import blackjack.dto.DealerResult;
import blackjack.dto.PlayerResult;

import java.util.*;

public class Players {

    private static final String NAME_DUPLICATED_EXCEPTION = "플레이어의 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<String> names, final List<String> battings, final Dealer dealer) {
        validate(names);

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), dealer, battings.get(i)));
        }

        return new Players(players);
    }

    private static void validate(final List<String> names) {
        if (isDuplicated(names)) {
            throw new IllegalArgumentException(NAME_DUPLICATED_EXCEPTION);
        }
    }

    private static boolean isDuplicated(final List<String> names) {
        return names.size() != Set.copyOf(names).size();
    }

    public BlackjackResult createResult(final Dealer dealer) {
        DealerResult dealerResult = DealerResult.of(0, 0, 0);
        PlayerResult playerResult = new PlayerResult();

        for (Player player : players) {
            DealerResult resultWithPlayer = Judge.judge(dealerResult, player, dealer, playerResult);
            dealerResult.updateByDealerResult(resultWithPlayer);
        }

        return new BlackjackResult(dealerResult, playerResult);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Map<Player, Integer> calculateProfits(final PlayerResult playerResult) {
        Map<Player, Integer> profitResult = new HashMap<>();

        for (Player player : players) {
            GameResult gameResult = playerResult.findByName(player.getName());
            profitResult.put(player, player.calculateProfit(gameResult));
        }

        return profitResult;
    }
}
