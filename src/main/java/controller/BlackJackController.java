package controller;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.Players;
import domain.strategy.DeckShuffleStrategy;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    public static final String HIT_COMMAND = "y";

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Deck deck = new Deck(new DeckShuffleStrategy());
        Players players = Players.registerPlayers(inputView.readParticipantsNames(), deck);
        Dealer dealer = new Dealer(deck.drawInitialCards());
        outputView.printInitialGameSettings(players, dealer);

        selectPlayersHitOrStand(players, deck);
        checkDealerHit(dealer, deck);
        outputView.printGameResults(players, dealer);
        outputView.printWinningResults(players.deriveResults(dealer.sumCardNumbers()));
    }

    private void selectPlayersHitOrStand(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            selectHitOrStand(player, deck);
            outputView.printPlayerCards(player);
        }
    }

    private void selectHitOrStand(Player player, Deck deck) {
        boolean shouldHit = inputView.readShouldHit(player.getNickname()).equals(HIT_COMMAND);
        while (shouldHit && player.addOneCard(deck.drawOneCard())) {
            outputView.printPlayerCards(player);
        }
    }

    private void checkDealerHit(Dealer dealer, Deck deck) {
        while (dealer.shouldDealerHit()) {
            outputView.printDealerHitMessage();
            dealer.addOneCard(deck.drawOneCard());
        }
    }
}
