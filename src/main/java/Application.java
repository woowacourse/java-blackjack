import bet.BetCenter;
import game.Deck;
import io.ConsoleInput;
import io.ConsoleOutput;
import participant.Dealer;
import participant.Participants;
import participant.Player;
import participant.Players;
import strategy.setting.DeckShuffleStrategy;

public class Application {
    public static final String HIT_COMMAND = "y";

    private static final ConsoleInput input = new ConsoleInput();
    private static final ConsoleOutput output = new ConsoleOutput();

    public static void main(String[] args) {
        Deck deck = new Deck(new DeckShuffleStrategy());
        Participants participants = initializeParticipants(deck);
        BetCenter betCenter = new BetCenter(input.readPlayerBetAmounts(participants));

        output.printInitialGameSettings(participants);

        performPlayerTurn(participants, deck);
        performDealerTurn(participants, deck);

        output.printGameResults(participants);
        output.printFinalProfit(betCenter, participants);
    }

    private static Participants initializeParticipants(Deck deck) {
        Players players = Players.registerPlayers(input.readParticipantsNames(), deck);
        Dealer dealer = new Dealer(deck);
        return Participants.initializeSetting(dealer, players);
    }

    private static void performPlayerTurn(Participants participants, Deck deck) {
        for (Player player : participants.getPlayers()) {
            selectHitOrStand(deck, player);
            output.printPlayerCards(player);
        }
    }

    private static void selectHitOrStand(Deck deck, Player player) {
        while (input.readShouldHit(player.getNickname()).equals(HIT_COMMAND) && player.addOneCard(deck.drawOneCard())) {
            output.printPlayerCards(player);
        }
    }

    private static void performDealerTurn(Participants participants, Deck deck) {
        while (participants.shouldDealerHit()) {
            output.printDealerHitMessage();
            participants.addOneCardWithDealer(deck.drawOneCard());
        }
    }
}
