package blackjack.view;

import static blackjack.view.form.OutputFormatter.formatCardsWithName;
import static blackjack.view.form.OutputFormatter.formatDealerBettingProfit;
import static blackjack.view.form.OutputFormatter.formatDealerDrawingCards;
import static blackjack.view.form.OutputFormatter.formatDealerFinalCards;
import static blackjack.view.form.OutputFormatter.formatDealingResultIntro;
import static blackjack.view.form.OutputFormatter.formatErrorMessage;
import static blackjack.view.form.OutputFormatter.formatPlayerBettingProfits;
import static blackjack.view.form.OutputFormatter.formatPlayersFinalCards;

import blackjack.dto.DealerFinalCardsOutcome;
import blackjack.dto.PlayerBettingProfitOutcome;
import blackjack.dto.PlayerCardsOutcome;
import blackjack.dto.PlayerFinalCardsOutcome;
import blackjack.model.card.Card;
import blackjack.model.player.PlayerName;
import java.util.List;

public class OutputView {
    private static final String BETTING_PROFIT_INTRO = "\n## 최종 수익";
    private static final PlayerName DEALER_NAME = new PlayerName("딜러");

    public void printDealingCards(final List<PlayerName> playerNames,
                                  final List<PlayerCardsOutcome> playerCardsOutcomes,
                                  final Card dealerFirstCard) {
        System.out.println(formatDealingResultIntro(playerNames));
        System.out.println(formatCardsWithName(List.of(dealerFirstCard), DEALER_NAME));
        for (PlayerCardsOutcome playerCardsOutcome : playerCardsOutcomes) {
            System.out.println(formatCardsWithName(playerCardsOutcome.cards(), playerCardsOutcome.name()));
        }
    }

    public void printPlayerDrawingCards(final PlayerCardsOutcome playerCardsOutcome) {
        System.out.println(formatCardsWithName(playerCardsOutcome.cards(), playerCardsOutcome.name()));
    }

    public void printDealerDrawingCards(final int drawCount) {
        System.out.println(formatDealerDrawingCards(drawCount));
    }

    public void printFinalCards(final DealerFinalCardsOutcome dealerFinalCardsOutcome,
                                final List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes) {
        System.out.println(formatDealerFinalCards(dealerFinalCardsOutcome, DEALER_NAME));
        System.out.println(formatPlayersFinalCards(playerFinalCardsOutcomes));
    }

    public void printBettingProfit(final List<PlayerBettingProfitOutcome> playerBettingProfitOutcomes) {
        System.out.println(BETTING_PROFIT_INTRO);
        System.out.println(formatDealerBettingProfit(playerBettingProfitOutcomes, DEALER_NAME));
        System.out.println(formatPlayerBettingProfits(playerBettingProfitOutcomes));
    }

    public void printException(final String errorMessage) {
        System.out.println(formatErrorMessage(errorMessage));
    }
}
