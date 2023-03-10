package controller;

import domain.Participants;
import domain.user.Dealer;
import domain.user.Player;
import ui.InputView;
import ui.OutputView;

public class BlackjackController {

    private final Participants participants;

    public BlackjackController() {
        this.participants = new Participants(InputView.readPlayersName());
    }

    public void run() {
        participants.initGame();
        OutputView.printCardsStatus(participants.getDealer(), participants.getPlayers());
        participants.getPlayers().forEach(player -> giveCardUntilImpossible(player, participants.getDealer()));
        addCardToDealerIfPossible();
        OutputView.printCardsStatusWithScore(participants.getDealer(), participants.getPlayers());
        participants.calculateAllResults();
        OutputView.printResults(participants.getPlayerResults());
    }

    private void addCardToDealerIfPossible() {
        if (participants.getDealer().canAdd()) {
            OutputView.announceAddCardToDealer();
            participants.addCardToDealerIfPossible();
        }
    }

    private void giveCardUntilImpossible(Player player, Dealer dealer) {
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
