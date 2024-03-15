package domain.participant;

import static java.util.stream.Collectors.toList;

import controller.dto.request.PlayerBettingMoney;
import controller.dto.response.PlayerOutcome;
import domain.game.deck.PlayerOutcomeFunction;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Participants {
    private final List<Participant> participants;

    public Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(final List<PlayerBettingMoney> requests) {
        return Stream.concat(
                generatePlayers(requests),
                Stream.of(new Dealer())
        ).collect(Collectors.collectingAndThen(toList(), Participants::new));
    }

    private static Stream<Player> generatePlayers(final List<PlayerBettingMoney> requests) {
        return requests.stream()
                .map(request -> new Player(request.name(), request.bettingAmount()));
    }

    public List<PlayerOutcome> getPlayersOutcomeIf(final PlayerOutcomeFunction function) {
        return getPlayers().stream()
                .map(player -> new PlayerOutcome(
                        player, // TODO: 어짜피 밖에서 NAME 조회해야해서 성능 상 그냥 Player를 바로 보냄.ㄱㅊ?
                        function.apply(player)
                ))
                .toList();
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public Dealer getDealer() {
        return (Dealer) participants.stream()
                .filter(Dealer.class::isInstance)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public List<Player> getPlayers() {
        return participants.stream()
                .filter(Player.class::isInstance)
                .map(Player.class::cast)
                .toList();
    }
}
