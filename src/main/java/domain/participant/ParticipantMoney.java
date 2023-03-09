package domain.participant;

import domain.game.BettingMoney;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static domain.participant.Participant.DEALER_NAME;

public class ParticipantMoney {
    private final Map<Participant, BettingMoney> participantMoneys;

    private ParticipantMoney(final Map<Participant, BettingMoney> participantMoneys) {
        this.participantMoneys = participantMoneys;
    }

    public static ParticipantMoney create(final Dealer dealer, final Map<Player, BettingMoney> playerInfo) {
        final Map<Participant, BettingMoney> participantBettingInfo = makeParticipantMoneys(dealer, playerInfo);
        return new ParticipantMoney(participantBettingInfo);
    }

    private static Map<Participant, BettingMoney> makeParticipantMoneys(final Dealer dealer,
                                                                        final Map<Player, BettingMoney> players) {
        final Map<Participant, BettingMoney> participantMoneys = new LinkedHashMap<>();
        participantMoneys.put(dealer, BettingMoney.zero());
        participantMoneys.putAll(players);
        return participantMoneys;
    }

    public Map<Participant, BettingMoney> getPlayerMoney() {
        return participantMoneys.keySet().stream()
                .filter(participant -> !DEALER_NAME.isSame(participant.getName()))
                .collect(Collectors.toMap(Function.identity(), participantMoneys::get,
                        (newValue, oldValue) -> oldValue, LinkedHashMap::new));
    }

    public Map<Participant, BettingMoney> getParticipantMoney() {
        return Map.copyOf(participantMoneys);
    }
}
