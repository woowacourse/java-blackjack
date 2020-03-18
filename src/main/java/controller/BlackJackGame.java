package controller;

import domain.UserResponse;
import domain.card.Deck;
import domain.card.DeckFactory;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.gamer.dto.GamerWithMoneyDto;
import domain.gamer.dto.GamerWithCardsDto;
import domain.gamer.dto.GamerWithScoreDto;
import domain.result.GameResults;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    public void play() {
        Deck deck = DeckFactory.create();
        Players players = Players.valueOf(deck, InputView.inputGamerDto());
        Dealer dealer = new Dealer(deck.dealInitCards());

        OutputView.printInitGamersState(GamerWithCardsDto.ofWithFirstCard(dealer), createPlayerDtos(players));
        receiveGamerCards(deck, players, dealer);
        OutputView.printGamerCardsStateWithScores(createGamersWithScoreDtos(dealer, players));

        printGameResult(dealer, players);
    }

    private List<GamerWithCardsDto> createPlayerDtos(Players players) {
        List<GamerWithCardsDto> playerDtos = new ArrayList<>();
        for (Player player : players) {
            playerDtos.add(GamerWithCardsDto.of(player));
        }
        return playerDtos;
    }

    private List<GamerWithScoreDto> createGamersWithScoreDtos(Dealer dealer, Players players) {
        List<GamerWithScoreDto> gamersWithScoreDtos = new ArrayList<>();
        gamersWithScoreDtos.add(GamerWithScoreDto.of(dealer));
        for (Player player : players) {
            gamersWithScoreDtos.add(GamerWithScoreDto.of(player));
        }
        return gamersWithScoreDtos;
    }

    private void printGameResult(Dealer dealer, Players players) {
        GameResults gameResults = new GameResults(dealer, players);

        OutputView.printGameResultTitle();
        OutputView.printEachResult(GamerWithMoneyDto.of(dealer, gameResults.calculateTotalDealerEarning()));
        for (Player player : players) {
            OutputView.printEachResult(GamerWithMoneyDto.of(player, gameResults.calculateEarning(player)));
        }
    }

    private void receiveGamerCards(Deck deck, Players players, Dealer dealer) {
        for (Player player : players) {
            receivePlayerCards(deck, player);
        }
        receiveDealerCards(deck, dealer);
    }

    private void receiveDealerCards(Deck deck, Dealer dealer) {
        while (dealer.isHittable()) {
            dealer.hit(deck);
            OutputView.printDealerHit(Dealer.HIT_THRESHOLD);
        }
    }

    private void receivePlayerCards(Deck deck, Player player) {
        while (player.isHittable() && askResponse(player)) {
            player.hit(deck);
            OutputView.printGamerCardsState(GamerWithCardsDto.of(player));
        }
    }

    private boolean askResponse(Player player) {
        UserResponse userResponse = UserResponse.of(InputView.inputGetMoreCard(player.getName()));
        return userResponse.isYes();
    }
}
