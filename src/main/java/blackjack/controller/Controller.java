package blackjack.controller;

import blackjack.YesOrNo;
import blackjack.domain.card.CardDeck;
import blackjack.domain.result.Results;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;


public class Controller {
    private static int INITIAL_SET_CARD_SIZE = 2;
    private static String WRONG_INPUT_ERROR_MSG = "잘못된 입력입니다.";
    private Dealer dealer;
    private Players players;
    private CardDeck deck;

    public Controller(Dealer dealer, Players players, CardDeck cardDeck) {
        this.dealer = dealer;
        this.players = players;
        this.deck = cardDeck;

        for (Player player : players.getPlayers()) {
            setInitialCards(player, deck);
        }
        setInitialCards(dealer, deck);

        OutputView.printInitialStatus(players, dealer);
    }

    public void play() {
        for (Player player : players.getPlayers()) {
            playEachPlayer(player, deck);
        }

        if (dealer.receivable()) {
            dealer.addCard(deck.getCard());
            OutputView.printDealerReceiveMoreCard(Dealer.LOWER_BOUND);
        }
    }

    public void computeResult() {
        OutputView.printFinalStatus(players, dealer);

        Results results = Results.createResults(players, dealer);
        OutputView.printFinalResult(results);
    }


    private void playEachPlayer(Player player, CardDeck deck) {
        while (proceed(player)) {
            player.addCard(deck.getCard());
            OutputView.printStatus(player);
        }
    }

    private boolean proceed(Player player) {
        if (!player.receivable()) {
            return  false;
        }
        YesOrNo answer = InputView.getYorN(player.getName());
        if (YesOrNo.isYes(answer)) {
            return true;
        }
        return false;
    }

    private void setInitialCards(User user, CardDeck deck) {
        for (int i = 0; i < INITIAL_SET_CARD_SIZE; i++) {
            user.addCard(deck.getCard());
        }
    }
}
