package blackjack.controller;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Result;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    private static final String DEALER = "딜러";

    public void run() {
        List<Player> players = createPlayers(InputView.inputPlayerNames());
        Dealer dealer = new Dealer(DEALER);
        Deck deck = new Deck();
        deck.shuffle();
        int index = 0;

        index = initialDrawDealer(deck, dealer, index);
        index = initialDrawPlayer(deck, players, index);

        showCards(dealer, players);

        index = distributeCards(players, deck, index);
        distributeDealer(deck, dealer, index);

        compareAllPlayersWithDealer(dealer, players);
        OutputView.printCardScore(dealer.getName(), dealer.getCards());
        showResult(dealer, players);
    }

    public List<Player> createPlayers(List<String> names) {
        OutputView.printParticipate(names);
        return names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public int distributeCards(List<Player> players, Deck deck, int index) {
        for (Player player : players) {
            distributePlayer(deck, player, index);
        }
        return index;
    }

    private int distributePlayer(Deck deck, Player player, int index) {
        OutputView.printCards(player.getName(), player.getCards());

        while (player.canDrawOneMore(player.getCards().getScore()) && InputView.inputDraw(player.getName())) {
            index = player.draw(deck, index);
            OutputView.printCards(player.getName(), player.getCards());
        }

        return index;
    }

    public int initialDrawDealer(Deck deck, Dealer dealer, int index) {
        return dealer.initializeDraw(deck, index);
    }

    public int initialDrawPlayer(Deck deck, List<Player> players, int index) {
        for (Player player : players) {
            index = player.initializeDraw(deck, index);
        }
        return index;
    }

    public void showCards(Dealer dealer, List<Player> players) {
        OutputView.printCards(dealer.getName()
                , new Cards(Collections.singletonList(dealer.getCards().getCard(0))));

        for(Player player : players){
            OutputView.printCards(player.getName(), player.getCards());
        }

        OutputView.printNewLine();
    }

    public static int distributeDealer(Deck deck, Dealer dealer, int index) {
        Cards dealerCards = dealer.getCards();

        while (dealer.canDrawOneMore(dealerCards.getScore())){
            OutputView.printOneMoreCard();
            index = dealer.draw(deck, index);
        }

        OutputView.printNewLine();

        return index;
    }

    public void compareAllPlayersWithDealer(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            player.compareWithDealer(dealer);
        }
    }

    public void showResult(Dealer dealer, List<Player> players) {
        for(Player player : players){
            OutputView.printCardScore(player.getName(), player.getCards());
        }

        OutputView.printDealerResult(
                dealer.findResultCount(Result.WIN),
                dealer.findResultCount(Result.DRAW),
                dealer.findResultCount(Result.LOSE));

        OutputView.printPlayerResult(players);
    }
}
