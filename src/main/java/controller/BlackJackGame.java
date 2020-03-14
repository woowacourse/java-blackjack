package controller;

import domain.PlayerResult;
import domain.UserResponse;
import domain.card.Deck;
import domain.card.DeckFactory;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.gamer.dto.GamerDto;
import domain.gamer.dto.GamerWithScoreDto;
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

        OutputView.printGamerCardsStateWithScore(GamerWithScoreDto.of(dealer));
        for (Player player : players.getPlayers()) {
            OutputView.printGamerCardsStateWithScore(GamerWithScoreDto.of(player));
        }

        Map<PlayerResult, List<GamerDto>> gameResults = calculateGameResults(dealer, players.getPlayers());
        OutputView.printGameResult(gameResults);
    }

    private Map<PlayerResult, List<GamerDto>> calculateGameResults(Dealer dealer, List<Player> players) {
        Map<PlayerResult, List<GamerDto>> gameResults = players.stream()
                .collect(Collectors.groupingBy(player -> PlayerResult.match(dealer, player),
                        Collectors.mapping(GamerDto::of, Collectors.toList())));
        for (PlayerResult playerResult : PlayerResult.values()) {
            gameResults.putIfAbsent(playerResult, new ArrayList<>());
        }
        return gameResults;
    }

    private void receiveDealerCards(Deck deck, Dealer dealer) {
        while (dealer.isHittable()) {
            dealer.hit(deck);
            OutputView.printDealerHit();
        }
    }

    private void receivePlayerCards(Deck deck, Player player) {
        while (player.isHittable() && askResponse(player)) {
            player.hit(deck);
            OutputView.printGamerCardsState(GamerDto.of(player));
        }
    }

    private boolean askResponse(Player player) {
        UserResponse userResponse = UserResponse.of(InputView.inputGetMoreCard(player.getName()));
        return userResponse.isYes();
    }
}
