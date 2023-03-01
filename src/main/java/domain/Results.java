package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import static java.util.stream.Collectors.*;

public class Results {

    private final List<Name> winners;
    private final List<Name> losers;

    private Results(final List<Name> winners, final List<Name> losers) {
        this.winners = winners;
        this.losers = losers;
    }

    public static Results of(final int dealerScore, final List<Participant> participants) {
        if (dealerScore > 21) {
            final List<Name> winners = participants.stream().map(Player::getName).collect(toList());
            final List<Name> losers = new ArrayList<>();
            return new Results(winners, losers);
        }
        final List<Name> winners = getResult(dealerScore, participants, (participant, score) -> isWinner(dealerScore, participant));
        final List<Name> losers = getResult(dealerScore, participants, (participant, score) -> isLoser(dealerScore, participant));
        return new Results(winners, losers);
    }

    private static boolean isWinner(final int dealerScore, final Participant participant) {
        return dealerScore <= participant.getScore() && participant.getScore() <= 21;
    }

    private static boolean isLoser(final int dealerScore, final Participant participant) {
        return participant.getScore() < dealerScore || participant.getScore() > 21;
    }

    private static List<Name> getResult(final int dealerScore, final List<Participant> participants, BiPredicate<Participant, Integer> p) {
        return participants.stream()
                .filter(participant -> p.test(participant, dealerScore))
                .map(Player::getName)
                .collect(toUnmodifiableList());
    }

    public int countWinners() {
        return winners.size();
    }

    public int countLosers() {
        return losers.size();
    }

    public List<Name> getWinners() {
        return List.copyOf(winners);
    }

    public List<Name> getLosers() {
        return List.copyOf(losers);
    }
}
