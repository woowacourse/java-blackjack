package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Referee {
    private final Dealer dealer;
    private final List<HandResult> handResults;

    public Referee(Dealer dealer) {
        this.dealer = dealer;
        this.handResults = List.of(HandResult.values());
    }

    public BlackjackResult generateBlackjackResult(PlayerBets playerBets) {
        List<ParticipantProfit> playersProfit = new ArrayList<>();
        for (PlayerBet playerBet : playerBets.getValues()) {
            HandResult playerResult = getPlayerResult(playerBet.getPlayer());
            playersProfit.add(playerBet.calculateProfit(playerResult));
        }
        return generateBlackjackResult(playersProfit);
    }

    private HandResult getPlayerResult(Player player) {
        return handResults.stream()
                .filter(handResult -> handResult.match(player, dealer))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
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
