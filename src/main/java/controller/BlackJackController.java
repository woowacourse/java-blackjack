package controller;

import static factory.BlackJackCreator.createParticipants;

import domain.card.CardDeck;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.ParticipantsResult;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CardDeck cardDeck;
    private Participants participants;

    public BlackJackController(InputView inputView, OutputView outputView, CardDeck cardDeck) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardDeck = cardDeck;
    }

    public void start() {
        participants = initParticipants();
        receiveCardProcessOfParticipants();
        receiveExtraCardProcessOfPlayer();
        receiveExtraCardProcessOfDealer();
        calculateBlackJackResultProcess();
    }

    private void receiveExtraCardProcessOfDealer() {
        participants.getParticipants().stream()
            .filter(participant -> !participant.isPlayer())
            .findFirst()
            .ifPresent(this::receiveCardProcessOfDealer);
    }

    private void receiveCardProcessOfDealer(Participant participant) {
        while (participant.canPick()) {
            participant.addCard(cardDeck.getAndRemoveFrontCard());
            outputView.printDealerPickMessage();
        }
        outputView.printBlankLine();
    }

    private void receiveExtraCardProcessOfPlayer() {
        participants.getParticipants().stream()
            .filter(Participant::isPlayer)
            .forEach(this::inputAskReceiveExtraCard);
    }

    private void receiveCardProcessOfParticipants() {
        for (Participant participant : participants.getParticipants()) {
            participant.addCard(cardDeck.getAndRemoveFrontCard());
            participant.addCard(cardDeck.getAndRemoveFrontCard());
        }
        outputView.printInitialParticipantHands(participants.getParticipants());
    }

    private void calculateBlackJackResultProcess() {
        printAllParticipantsInfo(participants);
        printAllParticipantGameResult();
    }

    private void printAllParticipantGameResult() {
        ParticipantsResult calculateParticipantsResult = participants.calculate();
        outputView.printGameResult(calculateParticipantsResult);
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
    private void inputAskReceiveExtraCard(Participant participant) {
        if (!extraReceiveCardProcess(participant)) {
            outputView.printParticipantHand(participant);
        }
    }

    private boolean extraReceiveCardProcess(Participant participant) {
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
