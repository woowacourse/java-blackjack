package game;

import io.ConsoleInput;
import io.ConsoleOutput;
import participant.Dealer;
import card.Deck;
import participant.Player;
import participant.Players;
import strategy.DeckShuffleStrategy;


public class BlackJackGame {

    public static final String HIT_COMMAND = "y";

    private final ConsoleInput consoleInput;
    private final ConsoleOutput consoleOutput;

    public BlackJackGame(ConsoleInput consoleInput, ConsoleOutput consoleOutput) {
        this.consoleInput = consoleInput;
        this.consoleOutput = consoleOutput;
    }

    public void play() {
        Deck deck = new Deck(new DeckShuffleStrategy());
        Players players = Players.registerPlayers(consoleInput.readParticipantsNames(), deck);
        Dealer dealer = new Dealer(deck.drawInitialCards());
        consoleOutput.printInitialGameSettings(players, dealer);

        selectPlayersHitOrStand(players, deck);
        checkDealerHit(dealer, deck);
        consoleOutput.printGameResults(players, dealer);
        consoleOutput.printWinningResults(players.deriveResults(dealer.sumCardNumbers()));
    }

    private void selectPlayersHitOrStand(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            selectHitOrStand(player, deck);
            consoleOutput.printPlayerCards(player);
        }
    }

    private void selectHitOrStand(Player player, Deck deck) {
        boolean shouldHit = consoleInput.readShouldHit(player.getNickname()).equals(HIT_COMMAND);
        while (shouldHit && player.addOneCard(deck.drawOneCard())) {
            consoleOutput.printPlayerCards(player);
        }
    }

    private void checkDealerHit(Dealer dealer, Deck deck) {
        while (dealer.shouldDealerHit()) {
            consoleOutput.printDealerHitMessage();
            dealer.addOneCard(deck.drawOneCard());
        }
    }
}
