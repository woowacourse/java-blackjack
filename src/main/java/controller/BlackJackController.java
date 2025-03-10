package controller;

import domain.BlackJackResultCalculator;
import domain.Card;
import domain.CardBundle;
import domain.CardDeck;
import domain.Dealer;
import domain.Participant;
import domain.Participants;
import domain.ParticipantsResult;
import domain.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.InputSplitter;
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

        CardDeck cardDeck = createCardDeck(cardBundle);

        receiveCardProcessOfParticipants(participants, cardDeck);

        // Note: 모든 참가자들의 카드 받기 기능
        receiveCardProcessOfDealer(participants, cardDeck);
        processPlayersCardReceiving(participants, cardDeck);

        calculateBackJackResultProcess(participants);
    }

    private void receiveCardProcessOfParticipants(Participants participants,
        CardDeck cardDeck) {
        for (Participant participant : participants.getParticipants()) {
            participant.addCard(cardDeck.getAndRemoveFrontCard());
            participant.addCard(cardDeck.getAndRemoveFrontCard());
        }
        outputView.printInitialParticipantHands(participants.getParticipants());
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
        for (Participant participant : participants.getParticipants()) {
            outputView.printFullParticipantInfo(participant);
        }
    }

    private void receiveCardProcessOfDealer(Participants participants, CardDeck cardDeck) {
        Participant dealer = participants.getDealer();
        while (dealer.canPick()) {
            dealer.addCard(cardDeck.getAndRemoveFrontCard());
            outputView.printDealerPickMessage();
        }
        outputView.printBlankLine();
    }

    private void processPlayersCardReceiving(Participants participants, CardDeck cardDeck) {
        for (Participant participant : participants.getPlayerParticipants()) {
            processPlayerCardReceiving(participant, cardDeck);
        }
        outputView.printBlankLine();
    }

    private void processPlayerCardReceiving(Participant participant, CardDeck cardDeck) {
        boolean isFinalHandsPrinted = false;
        while (participant.canPick() && doesPlayerWantToReceiveCard(participant)) {
            participant.addCard(cardDeck.getAndRemoveFrontCard());
            outputView.printParticipantHand(participant);
            isFinalHandsPrinted = true;
        }
        if (!isFinalHandsPrinted) {
            outputView.printParticipantHand(participant);
        }
    }

    private boolean doesPlayerWantToReceiveCard(Participant participant) {
        String playerWantMoreCard = inputView.inputPlayerWantMoreCard(participant);
        return playerWantMoreCard.equalsIgnoreCase("y");
    }

    private CardDeck createCardDeck(CardBundle cardBundle) {
        return new CardDeck(getShuffledAllCards(cardBundle));
    }

    private List<Card> getShuffledAllCards(CardBundle cardBundle) {
        List<Card> allCards = cardBundle.getAllCards();
        List<Card> shuffledAllCards = new ArrayList<>(allCards);
        Collections.shuffle(shuffledAllCards);
        return shuffledAllCards;
    }

    private Participants createGameParticipants() {
        List<Participant> participants = new ArrayList<>();
        // 초기 딜러 설정
        participants.add(new Dealer());
        String inputUserNames = inputView.inputUserNames();
        List<String> userNames = InputSplitter.split(inputUserNames);
        for (String userName : userNames) {
            Participant participant = new Player(userName);
            participants.add(participant);
        }
        return new Participants(participants);
    }
}
