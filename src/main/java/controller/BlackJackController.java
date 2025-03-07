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
        receiveCardProcessOfPlayer(participants, cardDeck);
        receiveCardProcessOfDealer(participants, cardDeck);

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

    private void receiveCardProcessOfPlayer(Participants participants, CardDeck cardDeck) {
        for (Participant participant : participants.getPlayerParticipants()) {
            inputAskReceiveExtraCard(cardDeck, participant);
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
