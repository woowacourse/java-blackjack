package blackjack.controller;

import blackjack.domain.YorN;
import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class Controller {

    public void run() {
        // 초기화
        Dealer dealer = new Dealer();
        List<String> names = InputView.getNames();
        Players players = new Players(names);
        CardDeck deck = new CardDeck();

        for (Player player : players.getPlayers()) {
            setInitialCards(player, deck);
        }
        setInitialCards(dealer, deck);

        OutputView.printInitialStatus(players, dealer);

        // 게임 진행 ~ 다른 클래스로 추출해서 사용하는 방법?
        for (Player player : players.getPlayers()) {
            while (!player.canReceiveMoreCard() && proceed(player.getName())) {
               player.addCard(deck.getCard());
               OutputView.printStatus(player.getName(), player.getCards());
            }
        }

        if (dealer.canReceiveMoreCard()) {
            dealer.addCard(deck.getCard());
            OutputView.printDealerGetMoreCard(Dealer.LOWER_BOUND);
        }

        OutputView.printFinalStatus(players, dealer);

        // 결과 계산
        players.computeResult(dealer);
        dealer.computeResult(players.getResult());

        OutputView.printFinalResult(dealer, players);
    }

    private boolean proceed(String name) {
        String input = InputView.getYorN(name);
        return YorN.valueOf(input).getResult();
    }

    private void setInitialCards(User user, CardDeck deck) {
        user.addCard(deck.getCard());
        user.addCard(deck.getCard());
    }
}
