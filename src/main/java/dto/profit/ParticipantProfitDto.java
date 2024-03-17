package dto.profit;

import domain.Profit;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Map.Entry;

public record ParticipantProfitDto(String name, long profit) {

    public static ParticipantProfitDto of(final Entry<Player, Profit> playerProfit) {
        return new ParticipantProfitDto(playerProfit.getKey().getName(), playerProfit.getValue().getProfit());
    }

    public static ParticipantProfitDto of(final Players players, final Dealer dealer) {
        return new ParticipantProfitDto(dealer.getName(), dealer.calculateProfitBy(players).getProfit());
    }
}
