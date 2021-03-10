package blackjack.controller;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = new BlackJackGame(InputView.askPlayerNames());

        askGamblerBet(blackJackGame);
        printInitialCards(blackJackGame);

        drawAdditionalCards(blackJackGame);
        calculateResults(blackJackGame);
    }

    private void askGamblerBet(BlackJackGame blackJackGame) {
        for (Gambler gambler : blackJackGame.getGamblers()) {
            blackJackGame.bet(gambler, InputView.askBettingMoney(gambler));
        }

        blackJackGame.checkBlackJack();
    }

    private void printInitialCards(BlackJackGame blackJackGame) {
        OutputView.printInitialCards(blackJackGame.getDealer(), blackJackGame.getGamblers());
    }

    private void drawAdditionalCards(final BlackJackGame blackJackGame) {
        for (Player gambler : blackJackGame.getGamblers()) {
            askGamblerDraw(blackJackGame, gambler);
        }
        giveDealerCards(blackJackGame);
    }

    private void askGamblerDraw(final BlackJackGame blackJackGame, final Player gambler) {
        while (InputView.askDrawOrNot(gambler).isYes()) {
            blackJackGame.giveCard(gambler);
            OutputView.printPlayerCardsInformation(gambler);
        }
    }

    private void giveDealerCards(final BlackJackGame blackJackGame) {
        Dealer dealer = blackJackGame.getDealer();
        while (dealer.ableToDraw()) {
            blackJackGame.giveCard(dealer);
            OutputView.informDealerReceived();
        }
    }

    private void calculateResults(BlackJackGame blackJackGame) {
        blackJackGame.calculateMoney();
        Dealer dealer = blackJackGame.getDealer();
        Gamblers gamblers = blackJackGame.getGamblers();

        OutputView.printPlayerCardsInformation(dealer);
        for(Gambler gambler : gamblers){
            OutputView.printPlayerCardsInformation(gambler);
        }

        OutputView.printResult(blackJackGame.calculateResult());
        OutputView.printFinalRevenue(dealer, gamblers);
    }
}
