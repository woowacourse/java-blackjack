package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class Controller {

    public void run() {
        List<String> names = InputView.enterNames();

        Players players = new Players(names);
        Dealer dealer = new Dealer();
        CardDeck deck = new CardDeck();

        // 초기 카드 전달
        deck.dealFirstCards(dealer);
        for (Player player : players.getPlayers()) {
            deck.dealFirstCards(player);
        }
        OutputView.printInitialStatus(players, dealer);

        // 추가 카드 전달
        for (Player player : players.getPlayers()) {
            while (player.canGetMoreCard()) {
                String reply = InputView.selectYesOrNo(player.getName());
                if (deck.dealAdditionalCard(player, reply)) {
                    OutputView.printStatus(player.getName(), player.getCards());
                    continue;
                }
                break;
            }
        }

        while (dealer.canGetMoreCard()) {
            deck.dealAdditionalCard(dealer);
            OutputView.printDealerGetMoreCard(Dealer.LOWER_BOUND);
        }
        OutputView.printFinalStatus(players, dealer);

        // 결과 계산
        players.computeResult(dealer);
        dealer.computeResult(players.getResult());

        OutputView.printFinalResult(dealer, players);
    }
}
