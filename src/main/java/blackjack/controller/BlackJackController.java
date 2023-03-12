package blackjack.controller;

import blackjack.domain.BlackJackManager;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.dto.ParticipantCardsDTO;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;

public class BlackJackController {
    private final BlackJackManager blackJackManager;

    public BlackJackController() {
        blackJackManager = new BlackJackManager(getPlayerStatuses());
    }

    private Map<String, Integer> getPlayerStatuses() {
        List<String> playerNames = InputView.readNames();
        return InputView.readBettingMoney(playerNames);
    }

    public void playGame() {
        showParticipantInitialCards();
        checkParticipantsHit();
        showParticipantStatus();
        showGameResult();
        showBettingResults();
    }

    private void showParticipantInitialCards() {
        Dealer dealer = blackJackManager.getDealer();
        List<Player> players = blackJackManager.getPlayers();
        OutputView.printParticipantsInitCards(ParticipantCardsDTO.ofStart(dealer, players));
    }

    private void checkParticipantsHit() {
        blackJackManager.hitByPlayers(InputView::checkPlayerAdditionalHit, OutputView::printParticipantsCards);
        blackJackManager.hitByDealer(OutputView::printDealerHit);
    }

    private void showParticipantStatus() {
        blackJackManager.showParticipantStatus(OutputView::printParticipantCardWithResult);
    }

    private void showGameResult() {
        blackJackManager.showGameResult(OutputView::printBlackJackResults);
    }

    private void showBettingResults() {
        blackJackManager.showBettingResults(OutputView::printBettingResults);
    }
}
