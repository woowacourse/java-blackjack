package domain.game;

import domain.participant.Participant;
import domain.participant.Participants;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {

    private final Map<Participant, Result> gameResults;

    private GameResult(Map<Participant, Result> gameResults) {
        this.gameResults = gameResults;
    }

    public static GameResult create(final Participants participants) {
        final Participant dealer = participants.getDealer();
        final List<Participant> players = participants.getPlayer();
        final Map<Participant, Result> gameResults = makeGameResults(dealer, players);
        return new GameResult(gameResults);
    }

    public Map<String, Result> getPlayerGameResults() {
        return gameResults.keySet().stream()
                .collect(Collectors.toMap(Participant::getName, gameResults::get,
                        (newValue, oldValue) -> oldValue, LinkedHashMap::new));
    }

    private static Map<Participant, Result> makeGameResults(final Participant dealer, final List<Participant> players) {
        Map<Participant, Result> gameResults = new LinkedHashMap<>();
        for (Participant player : players) {
            Result playerResult = calculateResult(dealer, player);
            gameResults.put(player, playerResult);
        }
        return gameResults;
    }

    private static Result calculateResult(Participant dealer, Participant player) {
        if (isDealerWin(dealer, player)) {
            return Result.LOSE;
        }
        if (isPlayerWin(dealer, player)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    private static boolean isDealerWin(final Participant dealer, final Participant player) {
        return player.isBust()
                || dealer.isBlackJack()
                || dealer.isBust() && player.isBust()
                || !dealer.isBust() && dealer.calculateScore() > player.calculateScore();
    }

    private static boolean isPlayerWin(final Participant dealer, final Participant player) {
        return dealer.isBust()
                || player.isBlackJack()
                || dealer.calculateScore() < player.calculateScore();
    }
}
