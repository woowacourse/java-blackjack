package blackjack.controller;

import blackjack.domain.BlackJackManager;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.dto.ParticipantCardsDTO;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {
    private final BlackJackManager blackJackManager;

    public BlackJackController() {
        blackJackManager = new BlackJackManager(InputView.readNames());
    }

    public void showParticipantInitialCards() {
        Dealer dealer = blackJackManager.getDealer();
        List<Player> players = blackJackManager.getPlayers();
        OutputView.printParticipantsInitCards(ParticipantCardsDTO.ofStart(dealer, players));
    }

    public void checkParticipantsHit() {
        blackJackManager.hitByPlayers(InputView::checkPlayerAdditionalHit, OutputView::printParticipantsCards);
        blackJackManager.hitByDealer(OutputView::printDealerHit);
    }

    public void showParticipantStatus() {
        blackJackManager.showParticipantStatus(OutputView::printParticipantCardWithResult);
    }

    public void showGameResult() {
        blackJackManager.showGameResult(OutputView::printBlackJackResults);
    }
}
