package game;

import io.ConsoleInput;
import io.ConsoleOutput;
import participant.Dealer;
import participant.Player;
import participant.Players;
import strategy.DeckShuffleStrategy;


public class BlackJackGame {

    public static final String HIT_COMMAND = "y";

    private final Players players;
    private final Dealer dealer;

    private BlackJackGame(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static BlackJackGame from(ConsoleInput input) {
        Dealer dealer = new Dealer(new DeckShuffleStrategy());
        Players players = Players.registerPlayers(input.readParticipantsNames(), dealer);

        return new BlackJackGame(players, dealer);
    }

    public void play(ConsoleInput input, ConsoleOutput output) {
        output.printInitialGameSettings(players, dealer);
        selectPlayersHitOrStand(players, dealer, input, output);
        checkDealerHit(dealer, output);
        output.printGameResults(players, dealer);
        output.printWinningResults(players.deriveResults(dealer.sumCardNumbers()));
    }

    private void selectPlayersHitOrStand(Players players, Dealer dealer, ConsoleInput input,ConsoleOutput output) {
        for (Player player : players.getPlayers()) {
            selectHitOrStand(player, dealer, input, output);
            output.printPlayerCards(player);
        }
    }

    private void selectHitOrStand(Player player, Dealer dealer, ConsoleInput input, ConsoleOutput output) {
        while (input.readShouldHit(player.getNickname()).equals(HIT_COMMAND) && player.addOneCard(dealer.drawCard())) {
            output.printPlayerCards(player);
        }
    }

    private void checkDealerHit(Dealer dealer, ConsoleOutput output) {
        while (dealer.shouldDealerHit()) {
            output.printDealerHitMessage();
            dealer.addOneCard(dealer.drawCard());
        }
    }
}
