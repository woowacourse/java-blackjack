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
    private Dealer dealer;
    private Players players;
    private CardDeck deck;

    public Controller(Dealer dealer, Players players, CardDeck cardDeck) {
        this.dealer = dealer;
        this.players = players;
        this.deck = cardDeck;
    }

    public void play() {
        setInitialCard(players, dealer);

        for (Player player : players.getPlayers()) {
            playEachPlayer(player, deck);
        }

        if (dealer.receivable()) {
            dealer.drawCard(deck);
            OutputView.printDealerReceiveMoreCard(Dealer.LOWER_BOUND);
        }
    }

    private void setInitialCard(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            player.drawCard(deck);
        }
        dealer.drawCard(deck);
        OutputView.printInitialStatus(players, dealer);
    }

    public void computeResult() {
        OutputView.printFinalStatus(players, dealer);

        Results results = Results.createResults(players, dealer);
        OutputView.printFinalResult(results);
    }

    private void playEachPlayer(Player player, CardDeck deck) {
        while (proceed(player)) {
            player.drawCard(deck);
            OutputView.printStatus(player);
        }
    }

    private boolean proceed(User player) {
        if (!player.receivable()) {
            return  false;
        }
        YesOrNo answer = InputView.getYorN(player.getName());
        return YesOrNo.isYes(answer);
    }
}
