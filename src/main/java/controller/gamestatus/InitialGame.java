package controller.gamestatus;

import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import view.OutputView;

public class InitialGame implements GameStatus {

    private static final int INITIAL_CARD_COUNT = 2;

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play(Dealer dealer, Players players, Deck deck) {
        OutputView.printInitialStep(players, INITIAL_CARD_COUNT);
        for (Player player : players.getPlayers()) {
            player.pickCards(deck, INITIAL_CARD_COUNT);
        }
        dealer.pickCards(deck, INITIAL_CARD_COUNT);
        OutputView.printGamerHiddenCards(dealer);
        printInitialPlayersCards(players);
        return new MainGame();
    }

    private void printInitialPlayersCards(Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printGamerCards(player);
        }
        OutputView.printNewLine();
    }
}
