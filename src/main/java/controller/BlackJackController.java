package controller;

import domain.BlackJackManager;
import domain.Card;
import domain.CardBundle;
import domain.CardDeck;
import domain.Dealer;
import domain.Participant;
import domain.Participants;
import domain.Player;
import domain.Results;
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
        BlackJackManager blackJackManager = new BlackJackManager(participants.getParticipants(),
            cardDeck);

        // 딜러를 포함한 모든 참여자에게 2장의 카드 분배
        blackJackManager.start();
        // 게임 시작 이후 각자의 손패를 출력
        outputView.printInitialParticipantHands(participants.getParticipants());

        // Note: 모든 참가자들의 카드 받기 기능
        playerReceiveCardProcess(participants, cardDeck);
        dealerReceiveCardProcess(participants, cardDeck);

        System.out.println();
        calculateBackJackResultProcess(participants, blackJackManager);
    }

    private void calculateBackJackResultProcess(Participants participants, BlackJackManager blackJackManager) {
        printAllParticipantsInfo(participants);
        printAllParticipantGameResult(blackJackManager);
    }

    private void printAllParticipantGameResult(BlackJackManager blackJackManager) {
        Results results = blackJackManager.calculateResult();
        outputView.printGameResult(results);
    }

    private void printAllParticipantsInfo(Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            outputView.printFullParticipantInfo(participant);
        }
    }

    private void dealerReceiveCardProcess(Participants participants, CardDeck cardDeck) {
        Participant dealer = participants.getDealer();
        while (dealer.canPick()) {
            dealer.addCard(cardDeck.getAndRemoveFrontCard());
            outputView.printDealerPickMessage();
        }
    }

    private void playerReceiveCardProcess(Participants participants, CardDeck cardDeck) {
        for (Participant participant : participants.getPlayerParticipants()) {
            inputAskReceiveExtraCard(cardDeck, participant);
        }
        System.out.println();
    }

    private void inputAskReceiveExtraCard(CardDeck cardDeck, Participant participant) {
        boolean isPrinted = false;
        isPrinted = extraReceiveCardProcess(cardDeck, participant, isPrinted);
        // 카드를 한 번도 받지 않은 상태라면 손패를 출력한다.
        if (!isPrinted) {
            outputView.printParticipantHand(participant);
        }
    }

    private boolean extraReceiveCardProcess(CardDeck cardDeck, Participant participant,
        boolean isPrinted) {
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
