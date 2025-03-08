package controller;

import static factory.BlackJackCreator.createCardBundle;
import static factory.BlackJackCreator.createCardDeck;
import static factory.BlackJackCreator.createParticipants;

import domain.BlackJackResultCalculator;
import domain.CardDeck;
import domain.Participant;
import domain.ParticipantResult;
import domain.Participants;
import domain.ParticipantsResult;
import domain.Player;
import java.util.Set;
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

        for (Participant participant : participants.getParticipants()) {
            if (participant instanceof Player) {
                inputAskReceiveExtraCard(cardDeck, participant);
                continue;
            }
            receiveCardProcessOfDealer(participant, cardDeck);
        }
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
        ParticipantsResult participantsResults = BlackJackResultCalculator.calculate(participants);
        Set<ParticipantResult> participantsResult = participantsResults.getParticipantsResult();
        for (ParticipantResult participantResult : participantsResult) {
            System.out.println(participantResult.get());
        }
    }

    private void printAllParticipantsInfo(Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            outputView.printFullParticipantInfo(participant);
        }
    }

    private void receiveCardProcessOfDealer(Participant participant, CardDeck cardDeck) {
        while (participant.canPick()) {
            participant.addCard(cardDeck.getAndRemoveFrontCard());
            outputView.printDealerPickMessage();
        }
        outputView.printBlankLine();
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
