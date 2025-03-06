package controller;

import domain.GameBoard;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> playerNames = inputView.askPlayerNames();
        Dealer dealer = Dealer.generate();
        List<Player> players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(Player.from(name));
        }

        ArrayList<Participant> participants = new ArrayList<>(players);
        participants.add(dealer);
        GameBoard gameBoard = new GameBoard(participants);

    }
}
