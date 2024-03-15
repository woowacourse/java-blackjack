package blackjack.view;

import blackjack.domain.Score;
import blackjack.domain.bet.Profit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.PlayerProfitResult;

public class OutputView {

    private final MessageResolver messageResolver;

    public OutputView(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printHandOutEvent(Players players, int handedCount) {
        System.out.println(messageResolver.resolveHandOutEventMessage(players, handedCount));
    }

    public void printPlayerHand(Player participant) {
        System.out.println(messageResolver.resolvePlayerHandMessage(participant));
    }

    public void printDealerPopCount(int dealerPopThreshold, int count) {
        System.out.println(messageResolver.resolveDealerPopCountMessage(dealerPopThreshold, count));
    }

    public void printParticipantScore(Participant participant, Score score) {
        System.out.println(messageResolver.resolveParticipantScoreMessage(participant, score));
    }

    public void printDealerInitialHand(Dealer dealer) {
        System.out.println(messageResolver.resolveDealerHandMessage(dealer));
    }

    public void printProfitResults(PlayerProfitResult playerProfitResult, Profit dealerProfit) {
        System.out.println(messageResolver.resolveProfitResultMessage(playerProfitResult, dealerProfit));
    }
}
