package blackjack.controller;

import blackjack.domain.CardInfoDto;
import blackjack.domain.RevenueInfoDto;
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
        showInitialCards(game.getDealer(), game.getGamblers());

        drawAdditionalCards(game);
        showGameResult(game);
    }

    private void askGamblerBet(final BlackJackGame game) {
        for (final Gambler gambler : game.getGamblers()) {
            game.bet(gambler, InputView.askBettingMoney(gambler));
        }
        game.checkBlackJack();
    }

    private void showInitialCards(final Dealer dealer, final Gamblers gamblers) {
        OutputView.printDistributeResult(gamblers.getNames());
        OutputView.printPlayerCardsInformation(new CardInfoDto(dealer));

        for (final Player gambler : gamblers) {
            OutputView.printPlayerCardsInformation(new CardInfoDto(gambler));
        }
    }

    private void drawAdditionalCards(final BlackJackGame game) {
        for (final Player gambler : game.getGamblers()) {
            askGamblerDraw(game, gambler);
        }
        giveDealerCards(game);
    }

    private void askGamblerDraw(final BlackJackGame game, final Player gambler) {
        while (YesOrNo.isYes(InputView.askDrawOrNot(gambler))) {
            game.giveCard(gambler);
            OutputView.printPlayerCardsInformation(new CardInfoDto(gambler));
        }
    }

    private void giveDealerCards(final BlackJackGame game) {
        final Dealer dealer = game.getDealer();
        while (dealer.ableToDraw()) {
            game.giveCard(dealer);
            OutputView.informDealerReceived();
        }
    }

    private void showGameResult(final BlackJackGame game){
        game.calculateMoney();
        showPlayerCards(game.getDealer(), game.getGamblers());
        showFinalRevenue(game.getDealer(), game.getGamblers());
    }

    private void showPlayerCards(final Dealer dealer, final Gamblers gamblers){
        OutputView.printPlayerCardsInformation(new CardInfoDto(dealer));
        for(final Gambler gambler : gamblers){
            OutputView.printPlayerCardsInformation(new CardInfoDto(gambler));
        }
    }

    private void showFinalRevenue(final Dealer dealer, final Gamblers gamblers){
        OutputView.printFinalRevenue(new RevenueInfoDto(dealer));
        for(final Gambler gambler : gamblers){
            OutputView.printFinalRevenue(new RevenueInfoDto(gambler));
        }
    }
}
