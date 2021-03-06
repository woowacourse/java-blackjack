package blackjack.controller;

import blackjack.domain.Result;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
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
        Deck deck = new Deck(CardFactory.create());

        initialDrawDealer(deck, dealer);
        initialDrawPlayer(deck, players);

        showCards(dealer, players);

        distributeCards(players, deck);
        distributeDealer(deck, dealer);

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

    public void distributeCards(List<Player> players, Deck deck) {
        for (Player player : players) {
            distributePlayer(deck, player);
        }
    }

    private void distributePlayer(Deck deck, Player player) {
        OutputView.printCards(player.getName(), player.getCards());

        while (player.canDrawOneMore(player.getScore()) && InputView.inputDraw(player.getName())) {
            player.draw(deck);
            OutputView.printCards(player.getName(), player.getCards());
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
        OutputView.printCards(dealer.getName()
                , new Cards(Collections.singletonList(dealer.getCards().getCard(0))));

        for(Player player : players){
            OutputView.printCards(player.getName(), player.getCards());
        }

        OutputView.printNewLine();
    }

    public void distributeDealer(Deck deck, Dealer dealer) {
        Cards dealerCards = dealer.getCards();

        while (dealer.canDrawOneMore(dealerCards.calculateScore())){
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
