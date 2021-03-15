package blackjack.controller;

import blackjack.domain.GameTable;
import blackjack.domain.HitStay;
import blackjack.domain.Money;
import blackjack.domain.ProfitCalculator;
import blackjack.domain.Result;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackJackController {

    public void run() {
        try {
            OutputView.printPlayersGuideMessage();
            Dealer dealer = new Dealer();
            Players players = createPlayers();
            playGame(dealer, players);
            revealResult(dealer, players);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
        }
    }

    private Players createPlayers() {
        List<String> playerNames = InputView.inputPlayers();
        List<Money> playerBettingMonies = new ArrayList<>();
        for (String name : playerNames) {
            OutputView.printPlayerBettingMoneyGuide(new Name(name));
            playerBettingMonies.add(new Money(InputView.inputMoney()));
        }
        return Players.makePlayers(playerNames, playerBettingMonies);
    }

    private void playGame(Dealer dealer, Players players) {
        GameTable gameTable = new GameTable(dealer, players);
        gameTable.drawAtFirst();
        drawPlayers(gameTable, players);
        drawDealer(gameTable, dealer);
    }

    private void revealResult(Dealer dealer, Players players) {
        OutputView.printCardsAndScore(dealer, players);
        Result result = new Result(dealer, players);
        ProfitCalculator profit = new ProfitCalculator();
        Map<User, Money> profitResult = profit.calculateProfit(dealer, result.getResult());
        OutputView.printProfitResult(profitResult);
    }

    private void drawPlayers(GameTable gameTable, Players players) {
        tryHit(gameTable, players);
    }

    public void tryHit(GameTable gameTable, Players players) {
        players.getPlayers()
            .forEach(player -> askHit(gameTable, player));
    }

    private void askHit(GameTable gameTable, Player player) {
        while (player.canHit() && wantCard(player)) {
            gameTable.draw(player);
            OutputView.printUserCards(player);
        }
    }

    private boolean wantCard(Player player) {
        OutputView.printHitGuideMessage(player);
        HitStay hitValue = HitStay.of(InputView.inputHitValue());
        return hitValue.isHit();
    }

    private void drawDealer(GameTable gameTable, Dealer dealer) {
        while (dealer.canHit()) {
            OutputView.printDealerHitMessage();
            gameTable.draw(dealer);
        }
    }
}
