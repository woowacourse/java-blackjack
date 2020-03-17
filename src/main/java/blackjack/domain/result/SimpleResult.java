package blackjack.domain.result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import blackjack.controller.BlackJack;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Status;

public class SimpleResult {
    private static final String SPACE = " ";
    private static final String LINE_BREAK = System.lineSeparator();

    private final ScoreBoard scoreBoard;
    private final Participant dealer;
    private final List<Participant> players;
    private final Map<Status, Integer> dealerResult = new HashMap<>();
    private final Map<Participant, Status> playersResult = new HashMap<>();

    public SimpleResult(final Participants participants) {
        scoreBoard = new ScoreBoard(participants);
        dealer = participants.getDealer();
        players = participants.getPlayers();
        for (Participant player : players) {
            decideWinner(player);
        }
    }

    private void decideWinner(final Participant player) {
        if (isBusted(player)) {
            execute(player, Status.LOSE);
            return;
        }
        if (isBusted(dealer)) {
            execute(player, Status.WIN);
            return;
        }
        compareToDealer(player);
    }

    private void compareToDealer(final Participant player) {
        int dealerScore = scoreBoard.get(dealer);
        int playerScore = scoreBoard.get(player);
        if (dealerScore > playerScore) {
            execute(player, Status.LOSE);
        }
        if (dealerScore == playerScore) {
            execute(player, Status.DRAW);
        }
        if (dealerScore < playerScore) {
            execute(player, Status.WIN);
        }
    }

    private void execute(final Participant player, final Status playerStatus) {
        playersResult.put(player, playerStatus);
        Status dealerStatus = playerStatus.getOpposite();
        dealerResult.put(dealerStatus,
            dealerResult.getOrDefault(dealerStatus, 0) + 1
        );
    }

    private boolean isBusted(Participant participant) {
        return scoreBoard.get(participant) > BlackJack.BLACK_JACK_SCORE;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dealer.getName()).append(": ");
        stringBuilder.append(Arrays.stream(Status.values())
            .filter(status -> Objects.nonNull(dealerResult.get(status)))
            .map(status -> dealerResult.get(status) + status.toString())
            .collect(Collectors.joining(SPACE)));
        stringBuilder.append(LINE_BREAK);
        stringBuilder.append(players.stream()
            .map(player -> player.getName() + ": " + playersResult.get(player))
            .collect(Collectors.joining(LINE_BREAK)));
        return stringBuilder.toString();
    }
}
