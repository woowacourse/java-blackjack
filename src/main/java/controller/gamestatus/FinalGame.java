package controller.gamestatus;

import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.result.BettingResult;
import view.OutputView;

public class FinalGame extends Playable {

    @Override
    public GameStatus play(Dealer dealer, Players players, Deck deck) {
        OutputView.printGamerStatus(dealer);
        for (Player player : players.getPlayers()) {
            OutputView.printGamerStatus(player);
        }
        OutputView.printBettingResult(new BettingResult(dealer, players));
        return new TerminateGame();
    }
}
