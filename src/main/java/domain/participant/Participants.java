package domain.participant;

import static java.util.stream.Collectors.toList;

import controller.dto.request.PlayerBettingMoney;
import controller.dto.response.PlayerOutcome;
import domain.game.deck.PlayerOutcomeFunction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Participants {
    private static final Dealer CACHED_DEALER = new Dealer();
    private static final List<Player> CACHED_PLAYERS = new ArrayList<>();

    private final List<Participant> participants;

    public Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(final List<PlayerBettingMoney> requests) {
        return Stream.concat(
                generatePlayers(requests),
                Stream.of(CACHED_DEALER) // TODO: add 순서가 프로그램의 흐름에 영향을 끼친다.
        ).collect(Collectors.collectingAndThen(toList(), Participants::new));
    }

    private static Stream<Player> generatePlayers(final List<PlayerBettingMoney> requests) {
        return requests.stream()
                .map(request -> {
                    Player player = new Player(request.name(), request.bettingAmount());
                    CACHED_PLAYERS.add(player);
                    return player;
                });
    }

    public List<PlayerOutcome> getPlayersOutcomeIf(final PlayerOutcomeFunction function) {
        return getPlayers().stream()
                .map(player -> new PlayerOutcome(
                        player, // TODO: 어짜피 밖에서 NAME 조회해야해서 성능 상 그냥 Player를 바로 보냄.ㄱㅊ? // 이것도 캐싱하면 해결될 텐데 ..
                        function.apply(player)
                ))
                .toList();
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    // TODO: 캐싱 이렇게 하는 거 맞나?
    public Dealer getDealer() {
        return participants.stream()
                .filter(Dealer.class::isInstance)
                .map(Dealer.class::cast)
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }

    public List<Player> getPlayers() {
        return participants.stream()
                .filter(Player.class::isInstance)
                .map(Player.class::cast)
                .toList();
    }
}
