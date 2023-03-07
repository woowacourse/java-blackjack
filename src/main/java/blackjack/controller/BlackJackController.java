package blackjack.controller;

import blackjack.domain.game.BlackJackManager;
import blackjack.domain.game.BlackJackResults;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

import static blackjack.util.ExceptionTemplate.repeatAndPrintCause;

public class BlackJackController {
    private final BlackJackManager blackJackManager;

    public BlackJackController() {
        this.blackJackManager = repeatAndPrintCause(() -> new BlackJackManager(InputView.readNames()));
    }

    public void inputHitCondition() {
        blackJackManager.hitByPlayer(InputView::checkPlayerAdditionalHit, OutputView::printParticipantsCards);
        blackJackManager.hitByDealer(OutputView::printDealerHit);
    }

    public void showParticipantsInitCards() {
        final Dealer dealer = blackJackManager.getDealer();
        final List<Player> players = blackJackManager.getPlayers();

        OutputView.printParticipantsInitCards(dealer, players);
    }

    public void showParticipantsStatus() {
        final Dealer dealer = blackJackManager.getDealer();
        final List<Player> players = blackJackManager.getPlayers();

        OutputView.printParticipantCardWithResult(dealer, dealer.getTotalPoint());
        players.forEach(player -> OutputView.printParticipantCardWithResult(player, player.getTotalPoint()));
    }

    public void showGameResult() {
        final BlackJackResults blackJackResults = blackJackManager.getBlackJackResults();

        OutputView.printBlackJackResults(blackJackResults);
    }
}
