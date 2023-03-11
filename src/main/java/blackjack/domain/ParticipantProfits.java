package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ParticipantProfits {
    private final List<ParticipantProfit> participantProfits;

    public ParticipantProfits(List<ParticipantProfit> participantProfits) {
        this.participantProfits = participantProfits;
    }

    public static ParticipantProfits of(List<Player> players, Dealer dealer) {
        List<ParticipantProfit> participantsProfits = getParticipantProfits(players, dealer);
        int dealerProfit = getDealerProfit(participantsProfits);
        participantsProfits.add(0, new ParticipantProfit(dealer, dealerProfit));
        return new ParticipantProfits(participantsProfits);
    }

    private static List<ParticipantProfit> getParticipantProfits(List<Player> players, Dealer dealer) {
        return players.stream()
                .map(player -> new ParticipantProfit(player, player.matchGameWithBet(dealer).getBet()))
                .collect(Collectors.toList());
    }

    private static int getDealerProfit(List<ParticipantProfit> playerProfits) {
        return playerProfits.stream()
                .mapToInt(ParticipantProfit::getProfit)
                .sum() * -1;
    }

    public List<ParticipantProfit> getParticipantProfits() {
        return Collections.unmodifiableList(participantProfits);
    }
}
