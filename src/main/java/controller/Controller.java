package controller;

import domain.Answer;
import domain.GameResult;
import domain.Names;
import domain.card.CardDeck;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private CardDeck cardDeck = CardFactory.createCardDeck();

    public void run() {
        Dealer dealer = new Dealer();
        List<Player> players = createPlayers();

        drawFirstCards(dealer, players);
        for (Player player : players) {
            drawMoreCards(player);
        }
        drawMoreCards(dealer);

        passResult(dealer, players);
    }

    private void drawMoreCards(Dealer dealer) {
        while (dealer.isDrawable()) {
            OutputView.printAutoDraw(dealer);
            dealer.draw(cardDeck.draw());
        }
    }

    private void drawMoreCards(Player player) {
        while (player.isDrawable() && askPlayerDraw(player)) {
            player.draw(cardDeck.draw());
            OutputView.printStatus(player.getStatus());
        }
    }

    private boolean askPlayerDraw(Player player) {
        OutputView.printCardFormat(player.getName());
        Answer answer = InputView.requestDraw();
        return answer.isAgree();
    }

    private void passResult(Dealer dealer, List<Player> players) {
        OutputView.printStatusWithScore(dealer.getStatus(), dealer.getScore());
        for (Player player : players) {
            OutputView.printStatusWithScore(player.getStatus(), player.getScore());
        }
        OutputView.printGameResult(new GameResult(players, dealer), dealer);
    }

    private void drawFirstCards(Dealer dealer, List<Player> players) {
        OutputView.printDrawTurn(dealer, players);
        dealer.draw(cardDeck.draw());
        dealer.draw(cardDeck.draw());
        OutputView.printStatus(dealer.getFirstStatus());
        for (Player player : players) {
            player.draw(cardDeck.draw());
            player.draw(cardDeck.draw());
            OutputView.printStatus(player.getStatus());
        }
    }

    private List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        Names names = new Names(Arrays.asList(InputView.requestName()));

        for (String name : names.get()) {
            players.add(new Player(name));
        }
        return players;
    }
}
