package controller;

import common.GamerDto;
import domain.PlayerResult;
import domain.card.Deck;
import domain.card.DeckFactory;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {
    public void play() {
        Deck deck = DeckFactory.create();
        Players players = Players.valueOf(deck, InputView.inputPlayerNames());
        Dealer dealer = new Dealer(deck.dealInitCards());

        OutputView.printInitGamersState(GamerDto.of(dealer), players.getPlayers().stream()
                .map(GamerDto::of)
                .collect(Collectors.toList()));

        for (Player player : players.getPlayers()) {
            receivePlayerCards(deck, player);
        }
        receiveDealerCards(deck, dealer);

        OutputView.printGamerCardsStateWithScore(GamerDto.of(dealer), dealer.calculateScore().getScore());
        for (Player player : players.getPlayers()) {
            OutputView.printGamerCardsStateWithScore(GamerDto.of(player), player.calculateScore().getScore());
        }

        Map<PlayerResult, List<GamerDto>> gameResults = calculateGameResults(players.getPlayers(), dealer);
        OutputView.printGameResult(gameResults);
    }

    private Map<PlayerResult, List<GamerDto>> calculateGameResults(List<Player> players, Dealer dealer) {
        Map<PlayerResult, List<GamerDto>> gameResults = players.stream()
                .collect(Collectors.groupingBy(player -> PlayerResult.match(dealer, player),
                        Collectors.mapping(GamerDto::of, Collectors.toList())));
        for (PlayerResult playerResult : PlayerResult.values()) {
            gameResults.putIfAbsent(playerResult, new ArrayList<>());
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
        while (player.canGetCard() && InputView.inputGetMoreCard(player.getName()).equals("y")) {
            player.addCard(deck.dealCard());
            OutputView.printGamerCardsState(GamerDto.of(player));
        }
    }
}
