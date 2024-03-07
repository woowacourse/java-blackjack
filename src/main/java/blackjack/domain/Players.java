package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.BlackjackResult;
import blackjack.dto.DealerResult;
import blackjack.dto.PlayerResult;
import java.util.List;
import java.util.Set;

public class Players {

    private static final String NAME_DUPLICATED_EXCEPTION = "플레이어의 이름은 중복될 수 없습니다.";
    private static final int BLACKJACK_BOUND = 21;

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<String> names, final Dealer dealer) {
        validate(names);

        List<Player> players = names.stream()
                .map(name -> new Player(name, dealer))
                .toList();

        return new Players(players);
    }

    private static void validate(final List<String> names) {
        if (names.size() != Set.copyOf(names).size()) {
            throw new IllegalArgumentException(NAME_DUPLICATED_EXCEPTION);
        }
    }

    public BlackjackResult createResult(final Dealer dealer) {
        int wins = 0;
        int loses = 0;
        int draws = 0;

        PlayerResult playerResult = new PlayerResult();

        for (Player player : players) {
            DealerResult result = judge(wins, loses, draws, player, dealer, playerResult);

            wins = result.getWins();
            loses = result.getLoses();
            draws = result.getDraws();
        }

        return new BlackjackResult(new DealerResult(wins, loses, draws), playerResult);
    }

    private DealerResult judge(final int wins, int loses, int draws,
                               final Player player, final Dealer dealer, PlayerResult playerResult) {
        long dealerScore = dealer.getScore();

        if (player.isBust() && dealer.isBust()) {
            playerResult.addResult(player, GameResult.DRAW);
            return new DealerResult(wins, loses, draws + 1);
        }

        if (player.isBust()) {
            playerResult.addResult(player, GameResult.LOSE);
            return new DealerResult(wins + 1, loses, draws);
        }

        if (dealerScore <= BLACKJACK_BOUND && (dealerScore != player.getScore()) && (dealerScore
                >= player.getScore())) {
            playerResult.addResult(player, GameResult.LOSE);
            return new DealerResult(wins + 1, loses, draws);
        }

        if (dealerScore <= BLACKJACK_BOUND && (dealerScore != player.getScore())) {
            playerResult.addResult(player, GameResult.WIN);
            return new DealerResult(wins, loses + 1, draws);
        }

        if (dealer.isBlackjack() && player.isBlackjack()) {
            playerResult.addResult(player, GameResult.DRAW);
            return new DealerResult(wins, loses, draws + 1);
        }

        if (dealer.getScore() != player.getScore() && dealer.isBlackjack()) {
            playerResult.addResult(player, GameResult.LOSE);
            return new DealerResult(wins + 1, loses, draws);
        }

        if (dealer.getScore() != player.getScore() && player.isBlackjack()) {
            playerResult.addResult(player, GameResult.WIN);
            return new DealerResult(wins, loses + 1, draws);
        }

        playerResult.addResult(player, GameResult.WIN);
        return new DealerResult(wins, loses + 1, draws);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
