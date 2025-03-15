package game;

import bet.BetCenter;
import io.ConsoleInput;
import io.ConsoleOutput;
import participant.Dealer;
import participant.Player;
import participant.Players;
import strategy.DeckShuffleStrategy;


public class BlackJackGame {

    public static final String HIT_COMMAND = "y";

    public void play(ConsoleInput input, ConsoleOutput output) {
        Dealer dealer = new Dealer(new DeckShuffleStrategy());
        Players players = Players.registerPlayers(input.readParticipantsNames(), dealer);
        BetCenter betCenter = new BetCenter(input.readPlayerBetAmounts(players));

        output.printInitialGameSettings(players, dealer);
        performPlayerTurn(players, dealer, input, output);
        performDealerTurn(dealer, output);

        output.printGameResults(players, dealer);
        output.printFinalProfit(betCenter, dealer);
    }

    private void performPlayerTurn(Players players, Dealer dealer, ConsoleInput input, ConsoleOutput output) {
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

    private void performDealerTurn(Dealer dealer, ConsoleOutput output) {
        while (dealer.shouldDealerHit()) {
            output.printDealerHitMessage();
            dealer.addOneCard(dealer.drawCard());
        }
    }
}
