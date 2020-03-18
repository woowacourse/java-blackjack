package controller;

import domain.UserResponse;
import domain.card.Deck;
import domain.card.DeckFactory;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.gamer.dto.GamerCardsDto;
import domain.gamer.dto.GamerCardsWithScoreDto;
import domain.gamer.dto.GamerMoneyDto;
import domain.result.GameResults;
import view.InputView;
import view.OutputView;

public class BlackJackGame {
    public void play() {
        Deck deck = DeckFactory.create();
        Players players = Players.valueOf(deck, InputView.inputGamerDto());
        Dealer dealer = new Dealer(deck.dealInitCards());

        OutputView.printInitGamersState(GamerCardsDto.of(dealer, players));
        receiveGamerCards(deck, players, dealer);
        OutputView.printGamerCardsStateWithScores(GamerCardsWithScoreDto.of(dealer, players));

        printGameResult(dealer, players);
    }

    private void printGameResult(Dealer dealer, Players players) {
        GameResults gameResults = new GameResults(dealer, players);

        OutputView.printGameResultTitle();
        OutputView.printEachResult(GamerMoneyDto.of(dealer, gameResults.calculateTotalDealerEarning()));
        for (Player player : players) {
            OutputView.printEachResult(GamerMoneyDto.of(player, gameResults.calculateEarning(player)));
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
            OutputView.printGamerCardsState(GamerCardsDto.of(player));
        }
    }

    private boolean askResponse(Player player) {
        UserResponse userResponse = UserResponse.of(InputView.inputGetMoreCard(player.getName()));
        return userResponse.isYes();
    }
}
