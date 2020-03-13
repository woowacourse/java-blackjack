package controller;

import java.util.List;

import domain.card.Deck;
import domain.result.WinningResult;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import util.YesOrNo;
import view.InputView;
import view.OutputView;

public class BlackJackGame {

    private static final int FIRST_CARD_COUNT = 2;
    private final Deck deck;

    public static BlackJackGame set(Deck deck) {
        return new BlackJackGame(deck);
    }

    private BlackJackGame(Deck deck) {
        this.deck = deck;
    }

    public void firstDealOut(Dealer dealer, Players players) {
        for (int i = 0; i < FIRST_CARD_COUNT; i++) {
            dealer.draw(deck.dealOut());
            players.draw(deck);
        }
    }

    public void additionalDealOut(Dealer dealer, Players players) {
        players.getPlayers()
                .forEach(this::playersAdditionalDraw);

        while (dealer.isAvailableToDraw()) {
            OutputView.printDealerDealOut();
            dealer.draw(deck.dealOut());
        }
    }

    private void playersAdditionalDraw(Player player) {
        while (player.isAvailableToDraw() && isYes(player)) {
            player.draw(deck.dealOut());
            OutputView.printDealOutResult(player);
        }
    }

    private boolean isYes(Player player) {
        String input = InputView.receiveYesOrNoInput(player.getName());
        return YesOrNo.of(input).isYes();
    }
}
