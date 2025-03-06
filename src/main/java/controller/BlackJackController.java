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

        outputView.printInitialParticipantHands(participants);
    }

}
