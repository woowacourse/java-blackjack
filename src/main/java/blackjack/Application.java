package blackjack;

import blackjack.domain.Game;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Game game = new Game(InputView.getNames());
        OutputView.printParticipantsCards(game.getParticipants());

        simulate(game);
        OutputView.printResult(game.getParticipants());
    }

    private static void simulate(Game game) {
        for (Player player : game.getPlayers()) {
            final Player playerResult = game
                .turnForPlayer(player, InputView.requestOneMoreCard(player.getName()));
            OutputView.printCards(playerResult);
        }

        game.turnForDealer();
        OutputView.printDealerGetCard();
    }


}
