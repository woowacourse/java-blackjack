package domain.participant;

import domain.game.BettingMoney;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static domain.participant.Participant.DEALER_NAME;

public class ParticipantMoney {
    private final Map<Participant, BettingMoney> participantMoneys;

    private ParticipantMoney(final Map<Participant, BettingMoney> participantMoneys) {
        this.participantMoneys = participantMoneys;
    }

    public static ParticipantMoney create(final Dealer dealer, final Map<Participant, BettingMoney> playerInfo) {
        final Map<Participant, BettingMoney> participantBettingInfo = makeParticipantMoneys(dealer, playerInfo);
        return new ParticipantMoney(participantBettingInfo);
    }

    private static Map<Participant, BettingMoney> makeParticipantMoneys(final Dealer dealer,
                                                                        final Map<Participant, BettingMoney> players) {
        final Map<Participant, BettingMoney> participantMoneys = new LinkedHashMap<>();
        participantMoneys.put(dealer, BettingMoney.zero());
        participantMoneys.putAll(players);
        return participantMoneys;
    }

    public Map<Participant, BettingMoney> getPlayerMoney() {
        return participantMoneys.keySet()
                .stream()
                .filter(participant -> !DEALER_NAME.isSame(participant.getName()))
                .collect(Collectors.toMap(Function.identity(), participantMoneys::get,
                        (newValue, oldValue) -> oldValue, LinkedHashMap::new));
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof ParticipantMoney)) {
            return false;
        }
        ParticipantMoney money = (ParticipantMoney) target;
        return Objects.equals(participantMoneys, money.participantMoneys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participantMoneys);
    }

    public Map<Participant, BettingMoney> getParticipantMoney() {
        return new LinkedHashMap<>(participantMoneys);
    }
}
