package blackjack.controller;

import blackjack.domain.Card.CardDeck;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class Controller {

    public void run() {
        List<String> names = InputView.getNames();
        Players players = new Players(names);
        Dealer dealer = new Dealer();

        CardDeck deck = new CardDeck();

        // 초기화
        for (Player player : players.getPlayers()) {
            player.addCard(deck.giveCard());
            player.addCard(deck.giveCard());
        }
        dealer.addCard(deck.giveCard());
        dealer.addCard(deck.giveCard());

        for (Player player : players.getPlayers()) {
           while(proceed(player.getName()) && !player.exceedMaxSum()){
               player.addCard(deck.giveCard());
            }
        }

        if (dealer.needMoreCard()) {
            deck.giveCard(dealer);
            OutputView.printDealerGetMoreCard(Dealer.LOWER_BOUND);
        }
    }

    private boolean proceed(String name) {
        String result = InputView.getYorN(name);
        if (result.equals("y")) {
            return true;
        }

        if (result.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }
}
