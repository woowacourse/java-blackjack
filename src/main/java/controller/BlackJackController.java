package controller;

import domain.BlackJackManager;
import domain.Card;
import domain.CardBundle;
import domain.CardDeck;
import domain.Dealer;
import domain.Participant;
import domain.Player;
import java.util.ArrayList;
import java.util.Collections;
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

    public void start() {
        List<Participant> participants = new ArrayList<>();
        // 딜러 생성
        participants.add(new Dealer());

        // 게임 참여자 객체 생성
        String userNames = inputView.inputUserNames();
        String[] splitNames = userNames.split(",");
        for (String splitName : splitNames) {
            String trimName = splitName.trim();
            Participant participant = new Player(trimName);
            participants.add(participant);
        }

        // 덱 생성
        CardBundle cardBundle = new CardBundle();
        List<Card> allCards = cardBundle.getAllCards();
        List<Card> shuffledAllCards = new ArrayList<>(allCards);
        Collections.shuffle(shuffledAllCards);

        CardDeck cardDeck = new CardDeck(shuffledAllCards);
        BlackJackManager blackJackManager = new BlackJackManager(participants, cardDeck);

        // 딜러를 포함한 모든 참여자에게 2장의 카드 분배
        blackJackManager.start();
        // 게임 시작 이후 각자의 손패를 출력
        outputView.printInitialParticipantHands(participants);

        // 참가자 카드 분배 입력 기능
        // 참가자를 앞에서부터 순회하며
        // 각 참가자가 카드를 추가로 받을 수 있다면
        // 카드를 받을지 여부를 입력받는다.
        for (Participant participant : participants) {
            if (participant instanceof Dealer) {
                continue;
            }

            // 카드를 추가로 받을 수 있다면
            // 카드를 받을지 여부를 입력받는다
            boolean isPrinted = false;
            while (participant.canPick()) {
                String playerWantMoreCard = inputView.inputPlayerWantMoreCard(participant);
                if (playerWantMoreCard.equalsIgnoreCase("n")) {
                    break;
                }
                // 카드를 추가로 받고 출력한다.
                participant.addCard(cardDeck.getAndRemoveFrontCard());
                outputView.printParticipantHand(participant);
                isPrinted = true;
            }

            // 카드를 한 번도 받지 않은 상태라면 손패를 출력한다.
            if (!isPrinted) {
                outputView.printParticipantHand(participant);
            }
        }

        // 딜러가 카드를 뽑을 수 있다면 카드를 뽑는다
        for (Participant participant : participants) {
            if (participant instanceof Dealer) {
                // 카드를 추가로 받을 수 있다면
                // 카드를 받을지 여부를 입력받는다
                while (participant.canPick()) {
                    // 카드를 추가로 받고 출력한다.
                    participant.addCard(cardDeck.getAndRemoveFrontCard());
                    outputView.printDealerPickMessage();
                }
            }
        }

        // 모든 참가자의 손패와, 점수를 출력한다
        for (Participant participant : participants) {
            outputView.printFullParticipantInfo(participant);
        }
    }

}
