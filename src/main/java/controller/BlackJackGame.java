package controller;

import common.GamerDto;
import domain.GameResult;
import domain.PlayerResult;
import domain.card.DeckImpl;
import domain.user.Dealer;
import domain.user.Player;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {
    public void play() {
        DeckImpl deck = DeckImpl.create();
        List<String> playerNames = InputView.inputPlayerNames();
        List<Player> players = playerNames.stream()
                .map(name -> new Player(deck.getInitCards(), name))
                .collect(Collectors.toList());
        Dealer dealer = new Dealer(deck.getInitCards());
        List<GamerDto> playerDtos = translateToDtos(players);
        OutputView.printInitGamersState(GamerDto.of(dealer), playerDtos);
        receivePlayerCards(deck, players);
        receiveDealerCard(deck, dealer);

        OutputView.printGamerCardsStateWithScore(GamerDto.of(dealer), dealer.calculateScore());
        for(Player player : players) {
            OutputView.printGamerCardsStateWithScore(GamerDto.of(player), player.calculateScore());
        }
        GameResult gameResult = GameResult.calculate(players, dealer);
        OutputView.printGameResult(gameResult);
}

    private List<GamerDto> translateToDtos(List<Player> players) {
        return players.stream().map(GamerDto::of).collect(Collectors.toList());
    }

    private Map<PlayerResult, List<Player>> calculateGameResults(List<Player> players, Dealer dealer) {
        Map<PlayerResult, List<Player>> gameResults = players.stream()
                .collect(Collectors.groupingBy(player -> PlayerResult.match(dealer, player), Collectors.toList()));
        for(PlayerResult playerResult : PlayerResult.values()) {
            gameResults.putIfAbsent(playerResult, new ArrayList<>());
        }
        return gameResults;
    }

    private void receiveDealerCard(DeckImpl deck, Dealer dealer) {
        if (dealer.canGetExtraCard()) {
            dealer.addCard(deck.handOutCard());
            OutputView.printDealerHit();
        }
    }

    private void receivePlayerCards(DeckImpl deck, List<Player> players) {
        for (Player player : players) {
            String willForMoreCard = InputView.inputGetMoreCard(player.getName());
            while (player.isNotBust() && player.wantToHit(willForMoreCard)) {
                player.addCard(deck.handOutCard());
                OutputView.printGamerCardsState(GamerDto.of(player));
                willForMoreCard = InputView.inputGetMoreCard(player.getName());
            }
        }
    }
}
