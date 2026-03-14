package domain;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Profits {
    private final Map<Participant, Profit> participantProfits;

    private Profits(Map<Participant, Profit> participantProfits) {
        this.participantProfits = Collections.unmodifiableMap(participantProfits);
    }

    public static Profits from(Dealer dealer, Map<Player, Profit> playerProfits) {
        Map<Participant, Profit> totalProfits = new LinkedHashMap<>();
        Profit dealerProfit = calculateDealerProfit(playerProfits);
        totalProfits.put(dealer, dealerProfit);
        totalProfits.putAll(playerProfits);
        return new Profits(totalProfits);
    }

    private static Profit calculateDealerProfit(Map<Player, Profit> playerProfits) {
        BigDecimal totalPlayerProfit = playerProfits.values().stream()
                .map(Profit::value)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Profit(totalPlayerProfit).negate();
    }

    public Map<Participant, Profit> getParticipantProfits() {
        return participantProfits;
    }
}
