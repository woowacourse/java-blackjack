package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.StartCardsDTO;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = createPlayers();
        BlackjackGame blackjackGame = new BlackjackGame(players);
        StartCardsDTO startCardsDTO = blackjackGame.start();

        outputView.printStartCards(startCardsDTO);
    }

    private Players createPlayers() {
        List<String> playerNames = inputView.readPlayerNames();
        return Players.from(playerNames);
    }
}
