package blackjack.controller;

import blackjack.domain.YesOrNo;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void run() {
        final BlackJackGame blackJackGame = new BlackJackGame(InputView.askPlayerNames());

        askGamblerBet(blackJackGame);
        printInitialCards(blackJackGame.getDealer(), blackJackGame.getGamblers());

        drawAdditionalCards(blackJackGame);

        blackJackGame.calculateMoney();
        calculateResults(blackJackGame.getDealer(), blackJackGame.getGamblers());
    }

    private void askGamblerBet(final BlackJackGame blackJackGame) {
        for (final Gambler gambler : blackJackGame.getGamblers()) {
            blackJackGame.bet(gambler, InputView.askBettingMoney(gambler));
        }

        blackJackGame.checkBlackJack();
    }

    private void printInitialCards(final Dealer dealer, final Gamblers gamblers) {
        OutputView.printDistributeResult(gamblers.getNames());
        OutputView.printPlayerCardsInformation(dealer.getNameValue(), dealer.getCards());

        for (final Player gambler : gamblers) {
            OutputView.printPlayerCardsInformation(gambler.getNameValue(), gambler.getCards());
        }
    }

    private void drawAdditionalCards(final BlackJackGame blackJackGame) {
        for (final Player gambler : blackJackGame.getGamblers()) {
            askGamblerDraw(blackJackGame, gambler);
        }
        giveDealerCards(blackJackGame);
    }

    private void askGamblerDraw(final BlackJackGame blackJackGame, final Player gambler) {
        while (YesOrNo.of(InputView.askDrawOrNot(gambler)).isYes()) {
            blackJackGame.giveCard(gambler);
            OutputView.printPlayerCardsInformation(gambler.getNameValue(), gambler.getCards());
        }
    }

    private void giveDealerCards(final BlackJackGame blackJackGame) {
        final Dealer dealer = blackJackGame.getDealer();
        while (dealer.ableToDraw()) {
            blackJackGame.giveCard(dealer);
            OutputView.informDealerReceived();
        }
    }

    private void calculateResults(final Dealer dealer, final Gamblers gamblers) {
        OutputView.printPlayerCardsInformation(dealer.getNameValue(), dealer.getCards());
        for(final Gambler gambler : gamblers){
            OutputView.printPlayerCardsInformation(gambler.getNameValue(), gambler.getCards());
        }
        OutputView.printFinalRevenue(dealer, gamblers);
    }
}
