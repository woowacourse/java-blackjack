package contoller;

import domain.BettingAmount;
import domain.card.Deck;
import domain.GameManager;
import domain.participant.Participant;
import domain.participant.ParticipantName;
import domain.participant.ParticipantNames;
import domain.participant.Participants;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private GameManager gameManager;

    public void run() {
        readyGame();
        printInitialCards();
        drawMorePlayersCards();
        drawMoreDealerCards();
        printFinalCards();
        printProfits();
    }

    private void readyGame() {
        ParticipantNames participantNames = getParticipantNames();
        Map<ParticipantName, BettingAmount> bettingAmounts = getBettingAmounts(participantNames);
        gameManager = new GameManager(participantNames, bettingAmounts, new Deck());
    }

    private ParticipantNames getParticipantNames() {
        List<String> playerNames = InputView.readPlayerNames();
        return new ParticipantNames(playerNames.stream()
                .map(ParticipantName::new)
                .toList());
    }

    private Map<ParticipantName, BettingAmount> getBettingAmounts(ParticipantNames playerNames) {
        return playerNames.getParticipantNames().stream()
                .collect(Collectors.toMap(
                        name -> name,
                        name -> new BettingAmount(InputView.readBettingAmount(name.getName())),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    private void printInitialCards() {
        Participants participants = gameManager.findParticipants();
        OutputView.printInitialParticipants(participants);
    }

    private void drawMorePlayersCards() {
        Participants participants = gameManager.findParticipants();
        List<Participant> players = participants.findPlayers();
        for (Participant player : players) {
            drawCard(player);
        }
    }

    private void drawCard(Participant player) {
        boolean answer;

        while (gameManager.shouldPlayerHit(player)) {
            answer = InputView.askForOneMoreCard(player.getParticipantName());
            gameManager.drawCardForPlayer(player, answer);
            OutputView.printPlayerCard(player);
            if (!answer) {
                break;
            }
        }
    }

    private void drawMoreDealerCards() {
        while (gameManager.shouldDealerHit()) {
            OutputView.printDealerDrawMessage();
        }
    }

    private void printFinalCards() {
        Participants participants = gameManager.findParticipants();
        OutputView.printFinalCards(participants);
    }

    private void printProfits() {
        Map<Participant, Integer> profits = gameManager.findPlayersProfits();
        int profitOfDealer = gameManager.findDealerProfit();
        OutputView.printProfits(profits, profitOfDealer);
    }
}
