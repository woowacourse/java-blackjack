package blackjack.domain.result;

import static blackjack.domain.result.Result.DRAW;
import static blackjack.domain.result.Result.LOSE;
import static blackjack.domain.result.Result.WIN;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Participants participants;
    private final Map<Player, Result> playersResult;

    public GameResult(Participants participants) {
        this.participants = participants;
        playersResult = new HashMap<>();
    }

    public Map<Player, Result> decidePlayersResult() {
        for (Player player : participants.getPlayers()) {
            compareScore(player, participants.getDealer());
        }

        return playersResult;
    }

    private void compareScore(final Player player, final Dealer dealer) {
        if (isParticipantBust(player, dealer)) {
            return;
        }

        compareIfNotBust(player, dealer.calculateTotalScore());
    }

    private boolean isParticipantBust(final Player player, final Dealer dealer) {
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

    private void compareIfNotBust(final Player player, final int dealerScore) {
        if (dealerScore < player.calculateTotalScore()) {
            playersResult.put(player, WIN);
            return;
        }
        if (dealerScore == player.calculateTotalScore()) {
            playersResult.put(player, DRAW);
            return;
        }
        playersResult.put(player, LOSE);
    }

    public List<Integer> getDealerResult() {
        initPlayersResultIfEmpty();
        Map<Result, Integer> dealerResult = new LinkedHashMap<>() {{
            put(WIN, 0);
            put(DRAW, 0);
            put(LOSE, 0);
        }};
        for (Result playerResult : playersResult.values()) {
            decideDealerResultCount(playerResult, dealerResult);
        }
        return new ArrayList<>(dealerResult.values());
    }

    private void initPlayersResultIfEmpty() {
        if (playersResult.isEmpty()) {
            decidePlayersResult();
        }
    }

    private void decideDealerResultCount(final Result playerResult, final Map<Result, Integer> dealerResult) {
        if (playerResult == WIN) {
            dealerResult.put(LOSE, dealerResult.get(LOSE) + 1);
            return;
        }
        if (playerResult == DRAW) {
            dealerResult.put(DRAW, dealerResult.get(DRAW) + 1);
            return;
        }
        dealerResult.put(WIN, dealerResult.get(WIN) + 1);
    }
}
