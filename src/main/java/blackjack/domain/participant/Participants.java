package blackjack.domain.participant;

import blackjack.domain.card.HandGenerator;
import blackjack.domain.result.*;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private static final int DEALER_PROFIT_INDEX = 0;

    private final Dealer dealer;
    private final Players players;

    public Participants(List<Name> playersName, HandGenerator handGenerator) {
        this.players = new Players(playersName, handGenerator);
        this.dealer = new Dealer(handGenerator);
    }

    public BlackjackResult generateResult(Referee referee, PlayerBets playerBets) {
        List<ParticipantProfit> participantProfits = new ArrayList<>();
        for (PlayerBet playerBet : playerBets.getValues()) {
            HandResult playerResult = referee.getPlayerResult(playerBet.getPlayer(), dealer);
            participantProfits.add(playerBet.calculateProfit(playerResult));
        }
        ParticipantProfit dealerProfit = calculateDealerProfit(participantProfits);
        participantProfits.add(DEALER_PROFIT_INDEX, dealerProfit);
        return new BlackjackResult(participantProfits);
    }

    private ParticipantProfit calculateDealerProfit(List<ParticipantProfit> playerProfits) {
        double negatedPlayerProfits = playerProfits.stream()
                .mapToDouble(ParticipantProfit::getNegatedProfit)
                .sum();
        return new ParticipantProfit(dealer.getName(), negatedPlayerProfits);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
