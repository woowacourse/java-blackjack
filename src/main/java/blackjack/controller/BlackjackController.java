package blackjack.controller;

import blackjack.domain.card.DeckGenerator;
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
        List<Player> players = createPlayersWithBetMoney(InputView.inputPlayerNames());
        OutputView.printParticipate(players);

        Dealer dealer = new Dealer(DEALER);
        DeckGenerator cardFactory = new DeckGenerator();
        Deck deck = new Deck(cardFactory.createWithShuffle());

        initialDrawDealer(deck, dealer);
        initialDrawPlayer(deck, players);

        showCards(dealer, players);

        distributeCards(players, deck);
        distributeDealer(deck, dealer);

        compareAllPlayersWithDealer(dealer, players);
        OutputView.printCardsWithScore(dealer);
        showResult(dealer, players);
    }

    private List<Player> createPlayersWithBetMoney(List<String> names) {
        return names.stream()
                .map(name -> new Player(name, InputView.inputBetMoney(name)))
                .collect(Collectors.toList());
    }

    private void distributeCards(List<Player> players, Deck deck) {
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

    private void initialDrawDealer(Deck deck, Dealer dealer) {
        dealer.initializeDraw(deck);
    }

    private void initialDrawPlayer(Deck deck, List<Player> players) {
        for (Player player : players) {
            player.initializeDraw(deck);
        }
    }

    private void showCards(Dealer dealer, List<Player> players) {
        OutputView.printCards(dealer, SHOW_INITIAL_DEALER_CARD_COUNT);

        for(Player player : players){
            OutputView.printCards(player);
        }

        OutputView.printNewLine();
    }

    private void distributeDealer(Deck deck, Dealer dealer) {
        while (dealer.canDrawOneMore()){
            OutputView.printOneMoreCard();
            dealer.draw(deck);
        }

        OutputView.printNewLine();
    }

    private void compareAllPlayersWithDealer(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            player.compareWithDealer(dealer);
        }
    }

    private void showResult(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            OutputView.printCardsWithScore(player);
        }
        OutputView.printProfit();
        OutputView.printProfitDealer(dealer, players);
        OutputView.printProfitPlayers(dealer, players);
    }
}
