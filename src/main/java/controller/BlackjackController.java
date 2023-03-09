package controller;

import domain.Participants;
import domain.PlayerResultRepository;
import domain.user.Dealer;
import domain.user.Player;
import ui.InputView;
import ui.OutputView;

public class BlackjackController {
    private static final String HIT = "y";
    private static final String STAND = "n";
    private final PlayerResultRepository playerResultRepository;

    public BlackjackController() {
        this.playerResultRepository = new PlayerResultRepository();
    }

    public void run() {
        Participants participants = new Participants(InputView.readPlayersName());
        participants.initGame();
        OutputView.printCardsStatus(participants.getDealer(), participants.getPlayers());
        participants.getPlayers().forEach(player -> giveCardUntilImpossible(player, participants.getDealer()));
        addCardToDealerIfPossible(participants);
        OutputView.printCardsStatusWithScore(participants.getDealer(), participants.getPlayers());
        participants.calculateAllResults(playerResultRepository);
        OutputView.printResults(playerResultRepository);
    }

    private void addCardToDealerIfPossible(Participants participants) {
        if (participants.getDealer().canAdd()) {
            OutputView.announceAddCardToDealer();
            participants.addCardToDealerIfPossible();
        }
    }

    private void giveCardUntilImpossible(Player player, Dealer dealer) {
        String whetherDrawCard = HIT;
        while (player.canAdd() && (whetherDrawCard = InputView.readWhetherDrawCardOrNot(player)).equals(HIT)) {
            player.addCard(dealer.drawCard());
            OutputView.printCardsStatusOfPlayer(player);
        }
        if (STAND.equals(whetherDrawCard)) {
            OutputView.printCardsStatusOfPlayer(player);
        }
    }
}
