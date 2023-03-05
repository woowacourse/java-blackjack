package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public final class Result {

    private static final int BUST_NUMBER = 22;

    private final List<String> winners;
    private final List<String> losers;

    private Result(final List<String> winners, final List<String> losers) {
        this.winners = winners;
        this.losers = losers;
    }

    public static domain.Result of(final int dealerScore, final List<Participant> participants) {
        Map<GameResult, List<Participant>> result = participants.stream()
                .collect(groupingBy(participant -> isWinner(dealerScore, participant)));

        List<String> winners = classifyParticipants(result.get(GameResult.VICTORY));
        List<String> losers = classifyParticipants(result.get(GameResult.DEFEAT));

        return new domain.Result(winners, losers);
    }

    private static List<String> classifyParticipants(final List<Participant> result) {
        if (result == null) {
            return new ArrayList<>();
        }
        return result.stream()
                .map(Player::getName)
                .collect(toList());
    }

    private static GameResult isWinner(final int dealerScore, final Participant participant) {
        if (isParticipantBusted(participant) || isParticipantDefeated(dealerScore, participant)) {
            return GameResult.DEFEAT;
        }
        return GameResult.VICTORY;
    }

    private static boolean isParticipantDefeated(final int dealerScore, final Participant participant) {
        return dealerScore < BUST_NUMBER && dealerScore > participant.getScore();
    }

    private static boolean isParticipantBusted(final Participant participant) {
        return participant.getScore() >= BUST_NUMBER;
    }

    public int countWinners() {
        return winners.size();
    }

    public int countLosers() {
        return losers.size();
    }

    public List<String> getWinners() {
        return List.copyOf(winners);
    }

    public List<String> getLosers() {
        return List.copyOf(losers);
    }

    private enum GameResult {
        VICTORY,
        DEFEAT
    }
}
