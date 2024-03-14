package blackjack;

import blackjack.domain.betting.Bets;
import blackjack.domain.betting.Money;
import blackjack.domain.betting.OwnedMoney;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        BlackJackGame game = createBlackjackGame();
        Bets bets = betting(game);
        drawStartCards(game);
        drawAdditionalCard(game);
        printResult(game.getDealer(), game.getPlayers());
        printPrize(bets, game);
    }

    private BlackJackGame createBlackjackGame() {
        List<String> playerNames = inputView.inputPlayerNames();
        BlackJackGame game = new BlackJackGame(playerNames);
        return game;
    }

    private Bets betting(BlackJackGame game) {
        List<Name> playerNames = game.getPlayerNames();
        List<OwnedMoney> bets = playerNames.stream()
                .map(this::betEachPlayer)
                .toList();
        return new Bets(bets);
    }

    private OwnedMoney betEachPlayer(Name name) {
        int bettingAmount = inputView.inputBettingAmount(name);
        return new OwnedMoney(name, bettingAmount);
    }

    private void drawStartCards(BlackJackGame game) {
        game.drawStartCards();
        outputView.printStartStatus(game.getDealer(), game.getPlayers());
    }

    private void drawAdditionalCard(BlackJackGame game) {
        game.drawAdditionalCard();
        while (game.isDealerDrawable()) {
            game.drawDealerCard();
            outputView.printDealerDraw();
        }
    }

    private void printResult(Dealer dealer, Players players) {
        outputView.printEndingStatus(dealer, players);
    }

    private void printPrize(Bets bets, BlackJackGame game) {
        outputView.printPrizeTitle();
        List<OwnedMoney> prizes = game.getAllGameResults().stream()
                .map(bets::getPrize)
                .toList();
        Money dealerPrize = calculateDealerPrize(prizes);
        outputView.printDealerPrize(dealerPrize);
        for (OwnedMoney prize : prizes) {
            outputView.printPlayerPrize(prize.getOwnerName(), prize.getMoney());
        }
    }

    private Money calculateDealerPrize(List<OwnedMoney> prizes) {
        Money dealerPrize = Money.ZERO;
        for (OwnedMoney prize : prizes) {
            dealerPrize = dealerPrize.subtract(prize.getMoney());
        }
        return dealerPrize;
    }
}
