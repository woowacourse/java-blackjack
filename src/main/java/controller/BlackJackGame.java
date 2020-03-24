package controller;

import domain.UserResponse;
import domain.card.Deck;
import domain.card.DeckFactory;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.gamer.dto.GamerCardsAssembler;
import domain.gamer.dto.GamerCardsWithScoreAssembler;
import domain.gamer.dto.GamerMoneyAssembler;
import domain.result.GameResults;
import view.InputView;
import view.OutputView;

public class BlackJackGame {
    public void play() {
        Deck deck = DeckFactory.create();
        Players players = Players.valueOf(deck, InputView.inputGamerDto());
        Dealer dealer = new Dealer(deck.dealInitCards());

        OutputView.printInitGamersState(GamerCardsAssembler.createDtos(dealer, players));
        receiveGamerCards(deck, players, dealer);
        OutputView.printGamerCardsStateWithScores(GamerCardsWithScoreAssembler.createDtos(dealer, players));

        OutputView.printResult(GamerMoneyAssembler.createDtos(dealer, players, new GameResults(dealer, players)));
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
            OutputView.printGamerCardsState(GamerCardsAssembler.create(player));
        }
    }

    private boolean askResponse(Player player) {
        UserResponse userResponse = UserResponse.of(InputView.inputGetMoreCard(player.getName()));
        return userResponse.isYes();
    }
}
