package blackjack.controller;

import blackjack.model.BlackJackGame;
import blackjack.model.CardGenerator;
import blackjack.model.Cards;
import blackjack.model.Command;
import blackjack.model.Dealer;
import blackjack.model.Player;
import blackjack.model.generator.RandomIndexGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Dealer dealer = new Dealer(new Cards());
        List<Player> players = inputView.readPlayers();
        BlackJackGame blackJackGame = new BlackJackGame(dealer, players, new CardGenerator(new RandomIndexGenerator()));
        blackJackGame.distributeCards();

        for (int index = 0; index < players.size(); index++) {
            Player player = players.get(index);
            drawCardWithCommand(player, blackJackGame, index);
        }
    }

    private void drawCardWithCommand(Player player, BlackJackGame blackJackGame, int index) {
        while (player.checkDrawCardState() && inputView.readCommand(player) == Command.YES) {
            blackJackGame.update(index);
        }
    }
}
