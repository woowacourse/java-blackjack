package blackjack.view;

import blackjack.domain.DealerGameResult;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Players;
import blackjack.domain.rule.Score;

public class OutputView {

    private final MessageResolver messageResolver;

    public OutputView(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printHandOutEvent(Players players, int handedCount) {
        System.out.println(messageResolver.resolveHandOutEventMessage(players, handedCount));
    }

    public void printPlayerHand(Participant participant) {
        System.out.println(messageResolver.resolvePlayerHandMessage(participant));
    }

    public void printDealerPopCount(int dealerPopThreshold, int count) {
        System.out.println(messageResolver.resolveDealerPopCountMessage(dealerPopThreshold, count));
    }

    public void printPlayerScore(Participant participant, Score score) {
        System.out.println(messageResolver.resolvePlayerScoreMessage(participant, score));
    }

    public void printPlayerGameResult(Participant participant, boolean win) {
        System.out.println(messageResolver.resolvePlayerGameResult(participant, win));
    }

    public void printDealerGameResult(DealerGameResult dealerGameResult) {
        System.out.println(messageResolver.resolveDealerGameResult(dealerGameResult));
    }

    public void printDealerInitialHand(Participant dealer) {
        System.out.println(messageResolver.resolveDealerHandMessage(dealer));
    }
}
