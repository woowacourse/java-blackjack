package controller;

import domain.BlackJackManager;
import domain.BlackJackResultCalculator;
import domain.CardBundle;
import domain.CardDeck;
import domain.Dealer;
import domain.Participant;
import domain.Participants;
import domain.ParticipantsResult;
import domain.Player;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start(CardBundle cardBundle) {
        Participants participants = createGameParticipants();
        CardDeck cardDeck = new CardDeck(cardBundle.getShuffledAllCards());
        BlackJackManager blackJackManager = new BlackJackManager(participants, cardDeck);
        giveStartingCardsToParticipants(blackJackManager);
        processPlayersCardReceiving(blackJackManager);
        processDealerCardReceiving(blackJackManager);
        calculateBackJackResultProcess(participants);
    }

    private void giveStartingCardsToParticipants(BlackJackManager blackJackManager) {
        blackJackManager.giveStartingCardsToParticipants();
        outputView.printInitialParticipantHandsMessage(blackJackManager.getPlayerNames());
        outputView.printInitialParticipantHands(blackJackManager.getParticipants());
    }

    private void calculateBackJackResultProcess(Participants participants) {
        printAllParticipantsInfo(participants);
        printAllParticipantGameResult(participants);
    }

    private void printAllParticipantGameResult(Participants participants) {
        ParticipantsResult participantsResult = BlackJackResultCalculator.calculate(participants);
        outputView.printGameResult(participantsResult);
    }

    private void printAllParticipantsInfo(Participants participants) {
        outputView.printParticipantsFullInfo(participants.getParticipants());
    }

    private void processDealerCardReceiving(BlackJackManager blackJackManager) {
        while(blackJackManager.canDealerPick()) {
            blackJackManager.giveCardToDealer();
            outputView.printDealerReceivingCardMessage();
        }
        outputView.printBlankLine();
    }

    private void processPlayersCardReceiving(BlackJackManager blackJackManager) {
        for (String playerName : blackJackManager.getPlayerNames()) {
            processPlayerCardReceiving(blackJackManager, playerName);
        }
        outputView.printBlankLine();
    }

    private void processPlayerCardReceiving(BlackJackManager blackJackManager, String playerName) {
        boolean isFinalHandsPrinted = false;
        while (blackJackManager.canPlayerPick(playerName) && doesPlayerWantToReceiveCard(playerName)) {
            blackJackManager.giveCardToPlayer(playerName);
            outputView.printParticipantHand(playerName, blackJackManager.getPlayerShownCards(playerName));
            isFinalHandsPrinted = true;
        }
        if (!isFinalHandsPrinted) {
            outputView.printParticipantHand(playerName, blackJackManager.getPlayerShownCards(playerName));
        }
    }

    private boolean doesPlayerWantToReceiveCard(String playerName) {
        String playerWantMoreCard = inputView.inputPlayerWantMoreCard(playerName);
        return playerWantMoreCard.equalsIgnoreCase("y");
    }

    private Participants createGameParticipants() {
        List<Participant> participants = new ArrayList<>();
        // 초기 딜러 설정
        participants.add(new Dealer());
        List<String> userNames = inputView.inputPlayerNames();
        for (String userName : userNames) {
            Participant participant = new Player(userName);
            participants.add(participant);
        }
        return new Participants(participants);
    }
}
