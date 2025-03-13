package game;

import io.InputView;
import io.OutputView;
import participant.Dealer;
import card.Deck;
import participant.Player;
import participant.Players;
import strategy.DeckShuffleStrategy;


public class BlackJackGame {

    public static final String HIT_COMMAND = "y";

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
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
