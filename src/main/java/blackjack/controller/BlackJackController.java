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
        final BlackJackGame game = new BlackJackGame(InputView.askPlayerNames());

        askGamblerBet(game);
        printInitialCards(game.getDealer(), game.getGamblers());

        drawAdditionalCards(game);

        game.calculateMoney();
        calculateResults(game.getDealer(), game.getGamblers());
    }

    private void askGamblerBet(final BlackJackGame game) {
        for (final Gambler gambler : game.getGamblers()) {
            game.bet(gambler, InputView.askBettingMoney(gambler));
        }

        game.checkBlackJack();
    }

    private void printInitialCards(final Dealer dealer, final Gamblers gamblers) {
        OutputView.printDistributeResult(gamblers.getNames());
        OutputView.printPlayerCardsInformation(dealer.getNameValue(), dealer.getCards());

        for (final Player gambler : gamblers) {
            OutputView.printPlayerCardsInformation(gambler.getNameValue(), gambler.getCards());
        }
    }

    private void drawAdditionalCards(final BlackJackGame game) {
        for (final Player gambler : game.getGamblers()) {
            askGamblerDraw(game, gambler);
        }
        giveDealerCards(game);
    }

    private void askGamblerDraw(final BlackJackGame game, final Player gambler) {
        while (YesOrNo.from(InputView.askDrawOrNot(gambler)).isYes()) {
            game.giveCard(gambler);
            OutputView.printPlayerCardsInformation(gambler.getNameValue(), gambler.getCards());
        }
    }

    private void giveDealerCards(final BlackJackGame game) {
        final Dealer dealer = game.getDealer();
        while (dealer.ableToDraw()) {
            game.giveCard(dealer);
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
