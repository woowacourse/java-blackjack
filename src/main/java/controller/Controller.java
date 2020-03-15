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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            dealer.draw(cardDeck);
        }
    }

    private void drawMoreCards(Player player) {
        while (player.isDrawable() && askPlayerDraw(player)) {
            player.draw(cardDeck);
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
        dealer.firstDraw(cardDeck);
        OutputView.printStatus(dealer.getFirstStatus());
        for (Player player : players) {
            player.firstDraw(cardDeck);
            OutputView.printStatus(player.getStatus());
        }
    }

    private List<Player> createPlayers() {
        Names names = new Names(Arrays.asList(InputView.requestName()));
        return names.get().stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }
}
