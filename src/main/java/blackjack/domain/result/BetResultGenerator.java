package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public class BetResultGenerator {
    private final Dealer dealer;
    private final Referee referee;

    public BetResultGenerator(Dealer dealer, Referee referee) {
        this.dealer = dealer;
        this.referee = referee;
    }

    public BlackjackResult generateBetResultOf(PlayerBets playerBets) {
        List<ParticipantProfit> playersProfit = new ArrayList<>();
        for (PlayerBet playerBet : playerBets.getValues()) {
            Player player = playerBet.getPlayer();
            HandResult playerResult = referee.getPlayerResult(player, dealer);
            playersProfit.add(playerBet.calculateProfit(playerResult));
        }
        return generateBlackjackResult(playersProfit);
    }

    private BlackjackResult generateBlackjackResult(List<ParticipantProfit> playerProfits) {
        double negatedPlayerProfits = playerProfits.stream()
                .mapToDouble(ParticipantProfit::getNegatedProfit)
                .sum();
        ParticipantProfit dealerProfit = new ParticipantProfit(dealer.getName(), negatedPlayerProfits);

        List<ParticipantProfit> participantsProfit = new ArrayList<>();
        participantsProfit.add(dealerProfit);
        participantsProfit.addAll(playerProfits);

        return new BlackjackResult(participantsProfit);
    }
}
