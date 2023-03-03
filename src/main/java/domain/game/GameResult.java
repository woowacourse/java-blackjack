package domain.game;

import domain.participant.Participant;
import domain.participant.Participants;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {

    private final Map<Participant, Result> gameResults;

    public GameResult(final Participants participants) {
        this.gameResults = new LinkedHashMap<>();

        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayer();
        players.forEach(player -> calculateResult(dealer, player));
    }

    public static GameResult create(final Participants participants) {
        return new GameResult(participants);
    }

    private void calculateResult(Participant dealer, Participant player) {
        if (isDealerWin(dealer, player)) {
            gameResults.put(player, Result.LOSE);
            return;
        }
        if (isPlayerWin(dealer, player)) {
            gameResults.put(player, Result.WIN);
            return;
        }
        gameResults.put(player, Result.DRAW);
    }

    private boolean isDealerWin(final Participant dealer, final Participant player) {
        return player.isBust()
                || dealer.isBlackJack()
                || dealer.isBust() && player.isBust()
                || dealer.calculateScore() > player.calculateScore();
    }

    private boolean isPlayerWin(final Participant dealer, final Participant player) {
        return dealer.isBust()
                || player.isBlackJack()
                || dealer.calculateScore() < player.calculateScore();
    }

    public Map<String, Result> getPlayerGameResults() {
        return gameResults.keySet().stream()
                .collect(Collectors.toUnmodifiableMap(Participant::getName, gameResults::get));
    }
}
