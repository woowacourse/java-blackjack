package blackjack.controller;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    private static final int SHOW_INITIAL_DEALER_CARD_COUNT = 1;

    private static final String DEALER = "딜러";

    public void run() {
        List<Player> players = createPlayers(InputView.inputPlayerNames());
        OutputView.printParticipate(players);

        Dealer dealer = new Dealer(DEALER);
        Deck deck = new Deck(CardFactory.createWithShuffle());

        initialDrawDealer(deck, dealer);
        initialDrawPlayer(deck, players);

        showCards(dealer, players);

        distributeCards(players, deck);
        distributeDealer(deck, dealer);

        compareAllPlayersWithDealer(dealer, players);
        OutputView.printCardsWithScore(dealer);
        showResult(dealer, players);
    }

    public List<Player> createPlayers(List<String> names) {
        return names.stream()
                .map(name -> new Player(name, InputView.inputBetMoney(name)))
                .collect(Collectors.toList());
    }

    public void distributeCards(List<Player> players, Deck deck) {
        for (Player player : players) {
            distributePlayer(deck, player);
        }
    }

    private void distributePlayer(Deck deck, Player player) {
        OutputView.printCards(player);

        while (player.canDrawOneMore() && InputView.inputDraw(player.getName())) {
            player.draw(deck);
            OutputView.printCards(player);
        }
    }

    public void initialDrawDealer(Deck deck, Dealer dealer) {
        dealer.initializeDraw(deck);
    }

    public void initialDrawPlayer(Deck deck, List<Player> players) {
        for (Player player : players) {
            player.initializeDraw(deck);
        }
    }

    public void showCards(Dealer dealer, List<Player> players) {
        OutputView.printCards(dealer, SHOW_INITIAL_DEALER_CARD_COUNT);

        for(Player player : players){
            OutputView.printCards(player);
        }

        OutputView.printNewLine();
    }

    public void distributeDealer(Deck deck, Dealer dealer) {
        while (dealer.canDrawOneMore()){
            OutputView.printOneMoreCard();
            dealer.draw(deck);
        }

        OutputView.printNewLine();
    }

    public void compareAllPlayersWithDealer(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            player.compareWithDealer(dealer);
        }
    }

    public void showResult(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            OutputView.printCardsWithScore(player);
        }
        OutputView.printProfit();
        OutputView.printProfitDealer(dealer, players);
        OutputView.printProfitPlayers(dealer, players);
    }
}
