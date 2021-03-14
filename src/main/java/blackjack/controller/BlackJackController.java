package blackjack.controller;

import blackjack.domain.CardInfoDto;
import blackjack.domain.RevenueInfoDto;
import blackjack.domain.YesOrNo;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.player.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
    private static final String NAME_SEPARATOR = ",";
    private final BlackJackGame game = new BlackJackGame();

    public void run() {
        setPlayers();
        showInitialCards(game.getDealer(), game.getGamblers());

        drawAdditionalCards();
        showGameResult();
    }

    private void setPlayers() {
        final String nameLine = InputView.askPlayerNames();
        for (final String name : nameLine.split(NAME_SEPARATOR)) {
            game.addPlayer(new Gambler(name), InputView.askBettingMoney(name));
        }
        game.distributeInitialCards();
    }

    private void showInitialCards(final Dealer dealer, final Gamblers gamblers) {
        OutputView.printDistributeResult(gamblers.getNames());
        OutputView.printPlayerCardsInformation(new CardInfoDto(dealer));

        for (final Gambler gambler : gamblers) {
            OutputView.printPlayerCardsInformation(new CardInfoDto(gambler));
        }
    }

    private void drawAdditionalCards() {
        for (final Gambler gambler : game.getGamblers()) {
            askGamblerDraw(gambler);
        }
        giveDealerCards();
    }

    private void askGamblerDraw(final Gambler gambler) {
        while (gambler.ableToDraw() && YesOrNo.isYes(InputView.askDrawOrNot(gambler.getNameValue()))) {
            game.giveCard(gambler);
            OutputView.printPlayerCardsInformation(new CardInfoDto(gambler));
        }
    }

    private void giveDealerCards() {
        final Dealer dealer = game.getDealer();
        while (dealer.ableToDraw()) {
            game.giveCard(dealer);
            OutputView.informDealerReceived();
        }
    }

    private void showGameResult() {
        game.calculateMoney();
        showPlayerCards(game.getDealer(), game.getGamblers());
        showFinalRevenue(game.getDealer(), game.getGamblers());
    }

    private void showPlayerCards(final Dealer dealer, final Gamblers gamblers) {
        OutputView.printPlayerCardsInformation(new CardInfoDto(dealer));
        for (final Gambler gambler : gamblers) {
            OutputView.printPlayerCardsInformation(new CardInfoDto(gambler));
        }
    }

    private void showFinalRevenue(final Dealer dealer, final Gamblers gamblers) {
        OutputView.printFinalRevenue(new RevenueInfoDto(dealer));
        for (final Gambler gambler : gamblers) {
            OutputView.printFinalRevenue(new RevenueInfoDto(gambler));
        }
    }
}
