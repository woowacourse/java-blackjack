package controller;

import domain.Participants;
import domain.user.Player;
import java.util.List;
import java.util.stream.Collectors;
import ui.InputView;
import ui.OutputView;
import ui.dto.InputPlayerDTO;

public class BlackjackController {

    private final Participants participants;

    public BlackjackController() {
        List<InputPlayerDTO> playerDTOs = InputView.readPlayersInput();
        this.participants = new Participants(playerDTOs.stream()
                .map(inputPlayerDTO ->
                        new Player(inputPlayerDTO.getName(),
                                inputPlayerDTO.getBettingAmount()))
                .collect(Collectors.toList()));
    }

    public void run() {
        participants.initGame();
        OutputView.printCardsStatus(participants.getDealer(), participants.getPlayers());
        giveCardPlayers();
        addCardToDealerIfPossible();
        OutputView.printCardsStatusWithScore(participants.getDealer(), participants.getPlayers());
        participants.calculateAllResults();
        OutputView.printResults(participants.getPlayerRevenues());
    }

    private void addCardToDealerIfPossible() {
        if (participants.getDealer().canAdd()) {
            OutputView.announceAddCardToDealer();
            participants.addCardToDealerIfPossible();
        }
    }

    private void giveCardPlayers() {
        participants.getPlayers().forEach(this::giveCardUntilImpossible);
    }

    private void giveCardUntilImpossible(Player player) {
        boolean whetherDrawCard = false;
        while (player.canAdd() && (whetherDrawCard = InputView.readWhetherDrawCardOrNot(player))) {
            participants.drawCardPlayer(player);
            OutputView.printCardsStatusOfPlayer(player);
        }
        if (!whetherDrawCard) {
            OutputView.printCardsStatusOfPlayer(player);
        }
    }
}
