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

        OutputView.printInitialStatus(players, dealer);

        // 게임
        for (Player player : players.getPlayers()) {
            while (!player.exceedMaxSum() && proceed(player.getName())) {
               player.addCard(deck.giveCard());
                OutputView.printStatus(player.getName(), player.getCards());
            }
        }

        if (dealer.needMoreCard()) {
            deck.giveCard(dealer);
            OutputView.printDealerGetMoreCard(Dealer.LOWER_BOUND);
        }

        OutputView.printFinalStatus(players, dealer);

        // 결과 계산
        players.computeResult(dealer);
        dealer.computeResult(players.getResult());

        OutputView.printFinalResult(dealer, players);
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
