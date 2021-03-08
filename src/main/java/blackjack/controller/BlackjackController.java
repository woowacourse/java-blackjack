package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.utils.HandInitializer;
import blackjack.domain.utils.ResultMapper;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        final Deck deck = Deck.create();
        final Dealer dealer = new Dealer();
        final Players players = Players.of(InputView.receiveNames());
        initHandsOf(deck, dealer, players);
        hitOrStand(deck, dealer, players);
        printResult(dealer, players);
    }

    private void hitOrStand(Deck deck, Dealer dealer, Players players) {
        for (Player player : players) {
            hitOrStandForPlayer(deck, player);
        }
        hitOrStandForDealer(deck, dealer);
    }

    private void initHandsOf(Deck deck, Dealer dealer, Players players) {
        HandInitializer.init(deck, dealer, players);
        OutputView.printInitialCards(dealer, players);
    }

    private void hitOrStandForPlayer(Deck deck, Player player) {
        while (InputView.receiveAnswer(player.getName()) && player.canDraw()) {
            player.receiveCard(deck.pick());
            OutputView.printAllCards(player);
        }
    }

    private void hitOrStandForDealer(Deck deck, Dealer dealer) {
        if (dealer.canDraw()) {
            dealer.receiveCard(deck.pick());
            OutputView.printDealerHitMessage();
        }
    }

    private void printResult(Dealer dealer, Players players) {
        OutputView.showAllCards(dealer, players);
        OutputView.printResultTitle();
        OutputView.printDealerResult(ResultMapper.resultOfDealer(dealer, players));
        OutputView.printPlayersResult(ResultMapper.resultOfPlayers(dealer, players));
    }
}
