package blackjack.view;

import blackjack.domain.DealerGameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.rule.BetResult;
import blackjack.domain.rule.BetResults;
import blackjack.domain.rule.Score;

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

    public void printPlayerGameResult(Player participant, boolean win) {
        System.out.println(messageResolver.resolvePlayerGameResult(participant, win));
    }

    public void printDealerGameResult(DealerGameResult dealerGameResult) {
        System.out.println(messageResolver.resolveDealerGameResult(dealerGameResult));
    }

    public void printDealerInitialHand(Dealer dealer) {
        System.out.println(messageResolver.resolveDealerHandMessage(dealer));
    }

    public void printBetResults(int dealerEarned, BetResults betResults) {
        System.out.println(messageResolver.resolveBetResultsMessage(dealerEarned, betResults));
    }

    public void printBetResult(BetResult betResult) {
        System.out.println(messageResolver.resolveBetResultMessage(betResult));
    }

}
