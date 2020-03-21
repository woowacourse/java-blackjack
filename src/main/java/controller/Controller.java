package controller;

import domain.GameResult;
import domain.Moneys;
import domain.Names;
import domain.PlayerAnswer;
import domain.card.CardDeck;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import view.InputView;
import view.OutputView;

public class Controller {
    private CardDeck cardDeck = CardFactory.createCardDeck();

    public void run() {
        Dealer dealer = new Dealer();
        Players players = createPlayers();

        drawFirstCards(dealer, players);
        for (Player player : players.get()) {
            drawMoreCards(player);
        }
        drawMoreCards(dealer);
        passResult(dealer, players, new GameResult(players, dealer));
    }

    private Players createPlayers() {
        Names names = new Names(InputView.requestName());
        Moneys moneys = new Moneys();
        for (String name : names.get()) {
            OutputView.printMoneyFormat(name);
            moneys.add(name, InputView.requestMoney());
        }
        return new Players(names, moneys);
    }

    private void drawFirstCards(Dealer dealer, Players players) {
        OutputView.printFirstDrawFormat(dealer, players);
        dealer.firstDraw(cardDeck);
        OutputView.printStatus(dealer);
        players.firstDraw(cardDeck);
        OutputView.printStatus(players);
    }

    private void drawMoreCards(Dealer dealer) {
        while (dealer.isDrawable()) {
            OutputView.printDealerDraw(dealer);
            dealer.draw(cardDeck);
        }
    }

    private void drawMoreCards(Player player) {
        while (player.isDrawable() && askPlayerDraw(player)) {
            player.draw(cardDeck);
            OutputView.printStatus(player);
        }
    }

    private boolean askPlayerDraw(Player player) {
        OutputView.printCardFormat(player);
        PlayerAnswer playerAnswer = InputView.requestDraw();
        return playerAnswer.isAgree();
    }

    private void passResult(Dealer dealer, Players players, GameResult gameResult) {
        OutputView.printStatusWithScore(dealer);
        OutputView.printStatusWithScore(players);
        OutputView.printGameResult(gameResult, players, dealer);
    }
}
