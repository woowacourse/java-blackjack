package blackjack.domain.result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;

public class SimpleResult extends Result {
    private static final String SPACE = " ";

    private final Map<Status, Integer> dealerResult = new HashMap<>();
    private final Map<Participant, Status> playersResult = new HashMap<>();

    public SimpleResult(final Participants participants) {
        super(participants);
        for (Participant player : players) {
            decideWinner(player);
        }
    }

    @Override
    protected void execute(final Participant player, final Status playerStatus) {
        playersResult.put(player, playerStatus);
        Status dealerStatus = playerStatus.getOpposite();
        dealerResult.put(dealerStatus,
            dealerResult.getOrDefault(dealerStatus, 0) + 1
        );
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
