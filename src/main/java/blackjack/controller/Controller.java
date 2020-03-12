package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class Controller {
    private static String WRONG_INPUT_ERROR_MSG = "잘못된 입력입니다.";
    private Dealer dealer;
    private Players players;
    private CardDeck deck;

    public Controller() {
        dealer = new Dealer();
        List<String> names = InputView.getNames();
        players = new Players(names);
        deck = new CardDeck();

        for (Player player : players.getPlayers()) {
            setInitialCards(player, deck);
        }
        setInitialCards(dealer, deck);

        OutputView.printInitialStatus(players, dealer);
    }

    public void play() {
        for (Player player : players.getPlayers()) {
            playerBlackjack(player, deck);
        }

        if (dealer.canReceiveMoreCard()) {
            dealer.addCard(deck.getCard());
            OutputView.printDealerGetMoreCard(Dealer.LOWER_BOUND);
        }
    }

    public void computeResult() {
        OutputView.printFinalStatus(players, dealer);

        players.computeResult(dealer);
        dealer.computeResult(players.getResult());

        OutputView.printFinalResult(dealer, players);
    }

    private boolean proceed(String name) {
        String input = InputView.getYorN(name);
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException(WRONG_INPUT_ERROR_MSG);
    }

    private void playerBlackjack(Player player, CardDeck deck) {
        while (!player.canReceiveMoreCard() && proceed(player.getName())) {
            player.addCard(deck.getCard());
            OutputView.printStatus(player.getName(), player.getCards());
        }
    }

    private void setInitialCards(User user, CardDeck deck) {
        user.addCard(deck.getCard());
        user.addCard(deck.getCard());
    }
}
