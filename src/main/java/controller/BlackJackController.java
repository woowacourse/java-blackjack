package controller;

import domain.BlackJackGame;
import domain.BlackJackResultCalculator;
import domain.ParticipantsResult;
import domain.betting.BatMoney;
import domain.betting.BatMonies;
import domain.betting.Revenues;
import domain.card.CardBundle;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
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
        BatMonies batMonies = createBatMonies(participants.getPlayerNames());
        CardDeck cardDeck = new CardDeck(cardBundle.getShuffledAllCards());
        BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);
        giveStartingCardsToParticipants(blackJackGame);
        processCardReceiving(blackJackGame);
        calculateBackJackResultProcess(participants);
        calculateBlackjackRevenue(blackJackGame, batMonies);
    }

    private Participants createGameParticipants() {
        List<Participant> participants = new ArrayList<>();
        // NOTE: 딜러를 생성하여 0번에 넣어줘야 한다.
        participants.add(new Dealer());
        List<String> userNames = inputView.inputPlayerNames();
        for (String userName : userNames) {
            Participant participant = new Player(userName);
            participants.add(participant);
        }
        return new Participants(participants);
    }

    private BatMonies createBatMonies(List<String> playerNames) {
        List<BatMoney> batMonies = new ArrayList<>();
        for (String playerName : playerNames) {
            int money = inputView.inputPlayerBatMoney(playerName);
            batMonies.add(new BatMoney(playerName, money));
        }
        return new BatMonies(batMonies);
    }

    private void giveStartingCardsToParticipants(BlackJackGame blackJackGame) {
        blackJackGame.giveStartingCardsToParticipants();
        outputView.printInitialParticipantHandsMessage(blackJackGame.getPlayerNames());
        outputView.printInitialParticipantHand(blackJackGame.getDealer().getName(),
                blackJackGame.getDealerFirstShownCards());
        for (String playerName : blackJackGame.getPlayerNames()) {
            outputView.printInitialParticipantHand(playerName, blackJackGame.getPlayerFirstShownCards(playerName));
        }
    }

    private void processCardReceiving(BlackJackGame blackJackGame) {
        processPlayersCardReceiving(blackJackGame);
        processDealerCardReceiving(blackJackGame);
    }

    private void processPlayersCardReceiving(BlackJackGame blackJackGame) {
        for (String playerName : blackJackGame.getPlayerNames()) {
            processPlayerCardReceiving(blackJackGame, playerName);
        }
        outputView.printBlankLine();
    }

    private void processPlayerCardReceiving(BlackJackGame blackJackGame, String playerName) {
        while (blackJackGame.canPlayerPick(playerName) && doesPlayerWantToReceiveCard(playerName)) {
            blackJackGame.giveCardToPlayer(playerName);
            outputView.printParticipantHand(playerName, blackJackGame.getPlayerCards(playerName));
        }
        if (!blackJackGame.hasPlayerReceivedCard(playerName)) {
            outputView.printParticipantHand(playerName, blackJackGame.getPlayerCards(playerName));
        }
    }

    private boolean doesPlayerWantToReceiveCard(String playerName) {
        String playerWantMoreCard = inputView.inputPlayerWantMoreCard(playerName);
        return playerWantMoreCard.equalsIgnoreCase("y");
    }

    private void processDealerCardReceiving(BlackJackGame blackJackGame) {
        while (blackJackGame.canDealerPick()) {
            blackJackGame.giveCardToDealer();
            outputView.printDealerReceivingCardMessage();
        }
        outputView.printBlankLine();
    }

    private void calculateBackJackResultProcess(Participants participants) {
        printAllParticipantsInfo(participants);
        // NOTE: 모든 참가자들의 승패 결과를 출력하지 않도록 명세가 수정되었다.
        // printAllParticipantGameResult(participants);
    }

    private void printAllParticipantsInfo(Participants participants) {
        outputView.printParticipantsFullInfo(participants.getParticipants());
    }

    private void printAllParticipantGameResult(Participants participants) {
        ParticipantsResult participantsResult = BlackJackResultCalculator.calculate(participants);
        outputView.printGameResult(participantsResult);
    }

    private void calculateBlackjackRevenue(BlackJackGame blackJackGame, BatMonies batMonies) {
        Revenues revenues = blackJackGame.calculateRevenue(batMonies);
        outputView.printRevenue(revenues);
    }
}
