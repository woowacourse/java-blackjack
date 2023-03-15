package controller;

import domain.Participants;
import domain.user.Player;
import java.util.List;
import ui.InputView;
import ui.OutputView;

public class BlackjackController {

    private final Participants participants;

    public BlackjackController() {
        List<Player> players = InputView.readPlayers();
        this.participants = new Participants(players);
    }

    public void run() {
        participants.initGame();
        OutputView.printCardsStatus(participants.getDealer(), participants.getPlayers());
        participants.drawCardForPlayers(InputView::readWhetherDrawCardOrNot,
                OutputView::printCardsStatusOfPlayer);
        addCardToDealerIfPossible();
        OutputView.printCardsStatusWithScore(participants.getDealer(), participants.getPlayers());
        participants.calculateAllResults();
        OutputView.printResults(participants.getPlayerRevenues());
    }

    private void addCardToDealerIfPossible() {
        if (participants.addCardToDealerIfPossible()) {
            OutputView.announceAddCardToDealer();
        }
    }
}
