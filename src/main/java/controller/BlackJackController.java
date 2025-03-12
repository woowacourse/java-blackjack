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
        outputView.printGameSummary(players, dealer);
        outputView.printGameResult(players.deriveResults(dealer.sumCardNumbers()));
    }

    private void selectPlayersHitOrStand(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            selectHitOrStand(player, deck);
            outputView.printPlayerCards(player);
        }
    }

    private void selectHitOrStand(Player player, Deck deck) {
        while (inputView.readShouldHit(player.getNickname()).equals(HIT_COMMAND)
        && player.addOneCard(deck.drawOneCard())) {
            outputView.printPlayerCards(player);
        }
    }

    private void checkDealerHit(Dealer dealer, Deck deck) {
        while (dealer.shouldDealerHit()) {
            outputView.printDealerOneMoreCardMessage();
            dealer.addOneCard(deck.drawOneCard());
        }
    }
}
