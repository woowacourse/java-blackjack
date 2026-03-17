package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.exception.ExceptionMessage;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Participants {

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(Dealer dealer, List<Player> players) {
        validateUnique(dealer, players);
        this.dealer = dealer;
        this.players = List.copyOf(players);
    }

    private void validateUnique(Dealer dealer, List<Player> players) {
        List<Participant> participants = getParticipants(dealer, players);
        if (isDuplicated(participants)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_PARTICIPANT_NAME.getMessage());
        }
    }

    private List<Participant> getParticipants(Dealer dealer, List<Player> players) {
        return Stream.concat(Stream.of(dealer), players.stream())
                .toList();
    }

    private boolean isDuplicated(List<Participant> values) {
        return values.stream()
                .distinct()
                .count() != values.size();
    }

    public void forEachParticipant(Consumer<Participant> consumer) {
        consumer.accept(dealer);
        players.forEach(consumer);
    }

    public void forEachPlayer(Consumer<Player> consumer) {
        players.forEach(consumer);
    }

    public void withDealer(Consumer<Dealer> consumer) {
        consumer.accept(dealer);
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Participant::getName)
                .toList();
    }
}
