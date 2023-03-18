package controller;

import domain.Participants;
import domain.user.Dealer;
import domain.user.Player;
import java.util.Iterator;
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
        addCardPlayers();
        addCardToDealerIfPossible();
        OutputView.printCardsStatusWithScore(participants.getDealer(), participants.getPlayers());
        OutputView.printResults(participants.calculateAllResults());
    }

    private void addCardToDealerIfPossible() {
        if (participants.addCardToDealerIfPossible()) {
            OutputView.announceAddCardToDealer();
        }
    }

    private void addCardPlayers() {
        Iterator<Player> iterator = participants.iterator();
        Dealer dealer = participants.getDealer();
        while (iterator.hasNext()) {
            addCardPlayer(iterator.next(), dealer);
        }
    }

    private void addCardPlayer(Player player, Dealer dealer) {
        boolean whetherDrawCard = false;
        while (player.canAdd() && (whetherDrawCard = InputView.readWhetherDrawCardOrNot(player))) {
            player.addCard(dealer.drawCard());
            OutputView.printCardsStatusOfPlayer(player);
        }
        if (!whetherDrawCard) {
            OutputView.printCardsStatusOfPlayer(player);
        }
    }
}
