package controller;

import domain.Answer;
import domain.GameResult;
import domain.Names;
import domain.card.CardDeck;
import domain.card.CardFactory;
import domain.rule.DealerRule;
import domain.rule.PlayerRule;
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

        passResult(dealer, players);
    }

    private Players createPlayers() {
        Names names = new Names(InputView.requestName());
        return new Players(names);
    }

    private void drawFirstCards(Dealer dealer, Players players) {
        OutputView.printFirstDrawFormat(dealer, players);
        dealer.firstDraw(cardDeck);
        OutputView.printStatus(dealer);
        players.firstDraw(cardDeck);
        OutputView.printStatus(players);
    }

    private void drawMoreCards(Dealer dealer) {
        while (dealer.isDrawable(new DealerRule())) {
            OutputView.printDealerDraw(dealer);
            dealer.draw(cardDeck);
        }
    }

    private void drawMoreCards(Player player) {
        while (player.isDrawable(new PlayerRule()) && askPlayerDraw(player)) {
            player.draw(cardDeck);
            OutputView.printStatus(player);
        }
    }

    private boolean askPlayerDraw(Player player) {
        OutputView.printCardFormat(player);
        Answer answer = InputView.requestDraw();
        return answer.isAgree();
    }

    private void passResult(Dealer dealer, Players players) {
        OutputView.printStatusWithScore(dealer);
        OutputView.printStatusWithScore(players);
        OutputView.printGameResult(new GameResult(players, dealer), dealer);
    }
}
