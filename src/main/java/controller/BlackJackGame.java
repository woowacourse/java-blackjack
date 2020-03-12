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
                .map(name -> new Player(deck.getInitCards(), name))
                .collect(Collectors.toList());
        Dealer dealer = new Dealer(deck.getInitCards());

        OutputView.printInitGamersState(GamerDto.of(dealer), players.stream().map(GamerDto::of).collect(Collectors.toList()));
        receivePlayerCards(deck, players);
        receiveDealerCard(deck, dealer);

        OutputView.printGamerCardsStateWithScore(GamerDto.of(dealer), dealer.calculateScore());
        for(Player player : players) {
            OutputView.printGamerCardsStateWithScore(GamerDto.of(player), player.calculateScore());
        }
        Map<PlayerResult, List<Player>> gameResults = calculateGameResults(players, dealer);
        OutputView.printGameResult(gameResults);
}

    private Map<PlayerResult, List<Player>> calculateGameResults(List<Player> players, Dealer dealer) {
        Map<PlayerResult, List<Player>> gameResults = players.stream()
                .collect(Collectors.groupingBy(player -> PlayerResult.match(dealer, player), Collectors.toList()));
        for(PlayerResult playerResult : PlayerResult.values()) {
            gameResults.putIfAbsent(playerResult, new ArrayList<>());
        }
        return gameResults;
    }

    private void receiveDealerCard(Deck deck, Dealer dealer) {
        if (dealer.canGetExtraCard()) {
            dealer.addCard(deck.handOutCard());
            OutputView.printDealerHit();
        }
    }

    private void receivePlayerCards(Deck deck, List<Player> players) {
        for (Player player : players) {
            while (!player.isBust() && InputView.inputGetMoreCard(player.getName()).equals("y")) {
                player.addCard(deck.handOutCard());
                OutputView.printGamerCardsState(GamerDto.of(player));
            }
        }
    }
}
