package controller;

import static factory.BlackJackCreator.createCardBundle;
import static factory.BlackJackCreator.createCardDeck;
import static factory.BlackJackCreator.createParticipants;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.ParticipantsResult;
import domain.participant.Player;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Participants participants = createGameParticipants();
        CardDeck cardDeck = createCardDeck(createCardBundle());
        receiveCardProcessOfParticipants(participants, cardDeck);
        receiveExtraCardProcessOfPlayer(participants, cardDeck);
        receiveExtraCardProcessOfDealer(participants, cardDeck);
        calculateBackJackResultProcess(participants);
    }

    private void receiveExtraCardProcessOfDealer(Participants participants, CardDeck cardDeck) {
        participants.getParticipants().stream()
            .filter(participant -> participant instanceof Dealer)
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
            .filter(participant -> participant instanceof Player)
            .forEach(participant -> inputAskReceiveExtraCard(cardDeck, participant));
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
    private void inputAskReceiveExtraCard(CardDeck cardDeck, Participant participant) {
        if (!extraReceiveCardProcess(cardDeck, participant)) {
            outputView.printParticipantHand(participant);
        }
    }

    private boolean extraReceiveCardProcess(CardDeck cardDeck, Participant participant) {
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

    private Participants createGameParticipants() {
        String inputUserNames = inputView.inputUserNames();
        return createParticipants(inputUserNames);
    }
}
