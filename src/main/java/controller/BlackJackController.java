package controller;

import static factory.BlackJackCreator.createCardDeck;
import static factory.BlackJackCreator.createParticipants;

import domain.card.CardBundle;
import domain.card.CardDeck;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.ParticipantsResult;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CardBundle cardBundle;

    public BlackJackController(InputView inputView, OutputView outputView, CardBundle cardBundle) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardBundle = cardBundle;
    }

    public void start() {
        CardDeck cardDeck = createCardDeck(cardBundle);
        Participants participants = initParticipants();
        receiveCardProcessOfParticipants(participants, cardDeck);
        receiveExtraCardProcessOfPlayer(participants, cardDeck);
        receiveExtraCardProcessOfDealer(participants, cardDeck);
        calculateBlackJackResultProcess(participants);
    }

    private void receiveExtraCardProcessOfDealer(Participants participants, CardDeck cardDeck) {
        participants.getParticipants().stream()
            .filter(participant -> !participant.isPlayer())
            .findFirst()
            .ifPresent(participant -> receiveCardProcessOfDealer(participant, cardDeck));
    }

    private void receiveCardProcessOfDealer(Participant participant, CardDeck cardDeck) {
        while (participant.canPick()) {
            participant.addCard(cardDeck.getAndRemoveFrontCard());
            outputView.printDealerPickMessage();
        }
        outputView.printBlankLine();
    }

    private void receiveExtraCardProcessOfPlayer(Participants participants, CardDeck cardDeck) {
        participants.getParticipants().stream()
            .filter(Participant::isPlayer)
            .forEach(participant -> inputAskReceiveExtraCard(participant, cardDeck));
    }

    private void receiveCardProcessOfParticipants(Participants participants, CardDeck cardDeck) {
        for (Participant participant : participants.getParticipants()) {
            participant.addCard(cardDeck.getAndRemoveFrontCard());
            participant.addCard(cardDeck.getAndRemoveFrontCard());
        }
        outputView.printInitialParticipantHands(participants.getParticipants());
    }

    private void calculateBlackJackResultProcess(Participants participants) {
        printAllParticipantsInfo(participants);
        printAllParticipantGameResult(participants);
    }

    private void printAllParticipantGameResult(Participants participants) {
        ParticipantsResult participantsResult = participants.calculate();
        outputView.printGameResult(participantsResult);
    }

    private void printAllParticipantsInfo(Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            outputView.printFullParticipantInfo(participant);
        }
    }

    /***
     * extraReceiveCardProcess에서 카드를 받은 후 들고 있는 카드를 출력하며 true를 반환합니다.
     *  카드를 한 번도 받지 않은 상태라면 들고 있는 카드를 출력한다.
     */
    private void inputAskReceiveExtraCard(Participant participant, CardDeck cardDeck) {
        if (!extraReceiveCardProcess(participant, cardDeck)) {
            outputView.printParticipantHand(participant);
        }
    }

    private boolean extraReceiveCardProcess(Participant participant, CardDeck cardDeck) {
        boolean isPrinted = false;
        while (participant.canPick()) {
            String playerWantMoreCard = inputView.inputPlayerWantMoreCard(participant);
            if (playerWantMoreCard.equalsIgnoreCase("n")) {
                break;
            }
            participant.addCard(cardDeck.getAndRemoveFrontCard());
            outputView.printParticipantHand(participant);
            isPrinted = true;
        }
        return isPrinted;
    }

    private Participants initParticipants() {
        String inputUserNames = inputView.inputUserNames();
        return createParticipants(inputUserNames);
    }
}
