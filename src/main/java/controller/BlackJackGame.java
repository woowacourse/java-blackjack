package controller;

import common.GamerDto;
import domain.PlayerResult;
import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Player;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {
    public void play() {
        Deck deck = Deck.create();
        List<String> playerNames = InputView.inputPlayerNames();
        List<Player> players = playerNames.stream()
                .map(name -> new Player(deck.dealInitCards(), name))
                .collect(Collectors.toList());
        Dealer dealer = new Dealer(deck.dealInitCards());

        OutputView.printInitGamersState(GamerDto.of(dealer), players.stream()
                .map(GamerDto::of)
                .collect(Collectors.toList()));

        for (Player player : players) {
            receivePlayerCards(deck, player);
        }
        receiveDealerCards(deck, dealer);

        OutputView.printGamerCardsStateWithScore(GamerDto.of(dealer), dealer.calculateScore());
        for (Player player : players) {
            OutputView.printGamerCardsStateWithScore(GamerDto.of(player), player.calculateScore());
        }
        Map<PlayerResult, List<Player>> gameResults = calculateGameResults(players, dealer);
        OutputView.printGameResult(gameResults);
    }

    private Map<PlayerResult, List<Player>> calculateGameResults(List<Player> players, Dealer dealer) {
        Map<PlayerResult, List<Player>> gameResults = players.stream()
                .collect(Collectors.groupingBy(player -> PlayerResult.match(dealer, player), Collectors.toList()));
        for (PlayerResult playerResult : PlayerResult.values()) {
            gameResults.putIfAbsent(playerResult, new ArrayList<>());
            gameResults.get(playerResult);
        }
        return gameResults;
    }

    private void receiveDealerCards(Deck deck, Dealer dealer) {
        while (dealer.canGetCard()) {
            dealer.addCard(deck.dealCard());
            OutputView.printDealerHit();
        }
    }

    private void receivePlayerCards(Deck deck, Player player) {
        while (player.isNotBust() && InputView.inputGetMoreCard(player.getName()).equals("y")) {
            player.addCard(deck.dealCard());
            OutputView.printGamerCardsState(GamerDto.of(player));
        }
    }
}
