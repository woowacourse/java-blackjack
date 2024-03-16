package controller.gamestatus;

import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import util.ConsoleReader;
import view.InputView;
import view.OutputView;

public class MainGame implements GameStatus {
    private static final ConsoleReader CONSOLE_READER = new ConsoleReader();
    private static final String COMMAND_YES = "y";

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play(Dealer dealer, Players players, Deck deck) {
        hitPlayersStep(players, deck);
        hitDealerStep(dealer, deck);
        return new FinalGame();
    }

    private void hitPlayersStep(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            hitPlayerStep(player, deck);
        }
    }

    private void hitPlayerStep(Player player, Deck deck) {
        boolean isFirstTurn = true;
        String answer = "y";
        while (isHittable(player, answer)) {
            answer = InputView.readAnswer(CONSOLE_READER, player.getName().name());
            hitByAnswer(player, deck, answer);
            printPlayerHitResult(answer, player, isFirstTurn);
            isFirstTurn = false;
        }
    }

    private boolean isHittable(Player player, String answer) {
        return player.canHit() && COMMAND_YES.equals(answer);
    }

    private void hitByAnswer(Player player, Deck deck, String answer) {
        if (COMMAND_YES.equals(answer)) {
            player.hit(deck);
        }
    }

    private void printPlayerHitResult(String answer, Player player, boolean isFirstTurn) {
        if (isFirstTurn || COMMAND_YES.equals(answer)) {
            OutputView.printGamerCards(player);
        }
    }

    private void hitDealerStep(Dealer dealer, Deck deck) {
        int dealerDrawCount = 0;
        while (dealer.canHit()) {
            dealerDrawCount += dealer.hit(deck);
        }
        OutputView.printDealerHitCount(dealerDrawCount);
    }
}
