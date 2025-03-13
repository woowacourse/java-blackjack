package domain;

import static factory.BlackJackCreator.createCardDeck;

import domain.card.CardBundle;
import domain.card.CardDeck;
import domain.participant.Betting;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.ParticipantsResult;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackGame {

    private final CardDeck cardDeck;
    private final Participants participants;

    public BlackJackGame(CardBundle cardBundle, List<String> userNames,
        List<Integer> bettingAmount) {
        this.cardDeck = createCardDeck(cardBundle);
        participants = createParticipants(userNames, bettingAmount);
        receiveCardProcessOfParticipants();
    }

    private void receiveCardProcessOfParticipants() {
        for (Participant participant : participants.getParticipants()) {
            participant.addCard(cardDeck.getAndRemoveFrontCard());
            participant.addCard(cardDeck.getAndRemoveFrontCard());
        }
    }

    public int receiveExtraCardProcessOfDealer() {
        return participants.getParticipants().stream()
            .filter(participant -> !participant.isPlayer())
            .findFirst()
            .map(participant -> receiveCardProcessOfDealer(participant, cardDeck))
            .orElse(0);
    }

    private int receiveCardProcessOfDealer(Participant participant, CardDeck cardDeck) {
        int count = 0;
        while (participant.canPick()) {
            participant.addCard(cardDeck.getAndRemoveFrontCard());
            count++;
        }
        return count;
    }

    public boolean canExtraReceiveOfPlayer(Participant participant) {
        return participant.canPick() && participant.isPlayer();
    }

    public void receiveExtraCardProcessOfPlayer(Participant participant) {
        participant.addCard(cardDeck.getAndRemoveFrontCard());
    }

    public ParticipantsResult calculateParticipantsResult() {
        return participants.calculateOfResult();
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants.getParticipants());
    }

    public Participant createParticipant(String userName, int bettingAmount) {
        return new Player(userName, new Betting(bettingAmount));
    }

    public Participants createParticipants(List<String> userNames, List<Integer> bettingAmounts) {
        List<Participant> participants = new ArrayList<>();
        createDealer(participants);
        createPlayer(userNames, bettingAmounts, participants);
        return new Participants(participants);
    }

    private void createPlayer(List<String> userNames, List<Integer> bettingAmounts,
        List<Participant> participants) {
        for (int i = 0; i < userNames.size(); i++) {
            String userName = userNames.get(i);
            int bettingAmount = bettingAmounts.get(i);
            participants.add(createParticipant(userName, bettingAmount));
        }
    }

    private void createDealer(List<Participant> participants) {
        participants.add(new Dealer());
    }
}
