package controller;

import domain.Answer;
import domain.DealerRule;
import domain.PlayerRule;
import domain.card.CardDeck;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    public void run() {
        List<Player> players = new ArrayList<>();
        Dealer dealer = new Dealer();
        String[] names = InputView.requestName();
        for (String name : names) {
            name = name.trim();
            players.add(new Player(name));
        }
        OutputView.printDrawTurn(dealer.getName(), names);
        //---------
        CardDeck cardDeck = CardFactory.createCardDeck();
        dealer.draw(cardDeck.draw());
        dealer.draw(cardDeck.draw());
        OutputView.printStatus(dealer.getFirstStatus());
        for (Player player : players) {
            player.draw(cardDeck.draw());
            player.draw(cardDeck.draw());
            OutputView.printStatus(player.getStatus());
        }
        //---------
        for (Player player : players) {
            while (player.isDrawable(new PlayerRule())) {
                OutputView.printCardFormat(player.getName());
                Answer answer = InputView.askMoreCard();
                if (answer.isDisagree()) {
                    break;
                }
                player.draw(cardDeck.draw());
                OutputView.printStatus(player.getStatus());
            }
        }
        while (dealer.isDrawable(new DealerRule())) {
            OutputView.printAutoDraw(dealer);
            dealer.draw(cardDeck.draw());
        }
        //--------
        OutputView.printStatusWithScore(dealer.getStatus(), dealer.getScore());
        for (Player player : players) {
            OutputView.printStatusWithScore(player.getStatus(), player.getScore());
        }

        //------
        Map<String, Boolean> gameResult = new HashMap<>();
        Map<String, Integer> dealerResult = new HashMap<>();
        dealerResult.put(dealer.getName(),0);
        for (Player player : players) {
            gameResult.put(player.getName(),!dealer.isWinner(player));
            if(dealer.isWinner(player)) {
                dealerResult.put(dealer.getName(), dealerResult.get(dealer.getName())+1);
            }
        }
        OutputView.printGameResult(gameResult, dealerResult);
    }
}
