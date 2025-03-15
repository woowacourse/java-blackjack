package game;

import bet.BetCenter;
import io.ConsoleInput;
import io.ConsoleOutput;
import participant.Dealer;
import participant.Player;
import participant.Players;
import strategy.DeckShuffleStrategy;


public class BlackJackGame {

    private final ConsoleInput input;
    private final ConsoleOutput output;

    public BlackJackGame(ConsoleInput input, ConsoleOutput output) {
        this.input = input;
        this.output = output;
    }

    public static final String HIT_COMMAND = "y";

    public void play() {
        Dealer dealer = new Dealer(new DeckShuffleStrategy());
        Players players = Players.registerPlayers(input.readParticipantsNames(), dealer);
        BetCenter betCenter = new BetCenter(input.readPlayerBetAmounts(players));

        output.printInitialGameSettings(players, dealer);
        performPlayerTurn(players, dealer);
        performDealerTurn(dealer, output);

        output.printGameResults(players, dealer);
        output.printFinalProfit(betCenter, dealer);
    }

    private void performPlayerTurn(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            selectHitOrStand(player, dealer);
            output.printPlayerCards(player);
        }
    }

    private void selectHitOrStand(Player player, Dealer dealer) {
        while (input.readShouldHit(player.getNickname()).equals(HIT_COMMAND) && player.addOneCard(dealer.drawCard())) {
            output.printPlayerCards(player);
        }
    }

    private void performDealerTurn(Dealer dealer, ConsoleOutput output) {
        while (dealer.shouldDealerHit()) {
            output.printDealerHitMessage();
            dealer.addOneCard(dealer.drawCard());
        }
    }
}
