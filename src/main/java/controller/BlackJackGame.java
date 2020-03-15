package controller;

import domain.deck.Deck;
import domain.deck.DeckFactory;
import domain.result.WinningResult;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import util.YesOrNo;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackGame {

    private static final int FIRST_CARD_COUNT = 2;
    private final Deck deck;

    public BlackJackGame() {
        deck = DeckFactory.getDeck();
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
        return YesOrNo.isYes(input);
    }

    public void decideWinner(Dealer dealer, Players players) {
        players.decideWinner(dealer);
        List<WinningResult> playerWinningResults = players.getWinningResults();
        playerWinningResults.forEach(winningResult -> dealer.applyWinningResult(winningResult.reverse()));
    }
}
