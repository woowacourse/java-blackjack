package blackjack.domain.result;

import static blackjack.domain.result.Result.DRAW;
import static blackjack.domain.result.Result.LOSE;
import static blackjack.domain.result.Result.WIN;

import blackjack.domain.card.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class GameResult {

    private final Participants participants;

    public GameResult(final Participants participants) {
        this.participants = participants;
    }

    public Map<Player, Result> makePlayersResult() {
        Map<Player, Result> playersResult = new HashMap<>();
        Dealer dealer = participants.getDealer();

        for (Player player : participants.getPlayers()) {
            makePlayersResult(player, dealer, playersResult);
        }

        return playersResult;
    }

    private void makePlayersResult(final Player player, final Dealer dealer,
                                   final Map<Player, Result> playersResult) {
        if (isParticipantBust(player, dealer, playersResult)) {
            return;
        }
        Score dealerScore = dealer.calculateTotalScore();
        compareIfNotBust(player, dealerScore, playersResult);
    }

    private boolean isParticipantBust(final Player player, final Dealer dealer,
                                      final Map<Player, Result> playersResult) {
        if (player.isBust()) {
            playersResult.put(player, LOSE);
            return true;
        }
        if (dealer.isBust()) {
            playersResult.put(player, WIN);
            return true;
        }
        return false;
    }

    private void compareIfNotBust(final Player player, final Score dealerScore,
                                  final Map<Player, Result> playersResult) {
        if (dealerScore.isLess(player.calculateTotalScore())) {
            playersResult.put(player, WIN);
            return;
        }
        if (dealerScore.isEquals(player.calculateTotalScore())) {
            playersResult.put(player, DRAW);
            return;
        }
        playersResult.put(player, LOSE);
    }

    public Map<Player, Result> reverseResult(final Map<Player, Result> playersResult) {
        Map<Player, Result> reversePlayersResult = new HashMap<>();

        for (Player player : playersResult.keySet()) {
            Result reverseResult = reverse(playersResult.get(player));
            reversePlayersResult.put(player, reverseResult);
        }

        return reversePlayersResult;
    }

    private Result reverse(final Result result) {
        if (result == WIN) {
            return LOSE;
        }
        if (result == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public List<Integer> getDealerResultCount(final Map<Player, Result> reversePlayersResult) {
        Map<Result, Integer> dealerResult = new LinkedHashMap<>() {{
            put(WIN, 0);
            put(DRAW, 0);
            put(LOSE, 0);
        }};

        for (Result value : reversePlayersResult.values()) {
            dealerResult.put(value, dealerResult.get(value) + 1);
        }
        return new ArrayList<>(dealerResult.values());
    }
}
