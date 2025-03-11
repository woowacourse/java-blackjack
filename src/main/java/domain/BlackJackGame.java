package domain;

import static factory.BlackJackCreator.createCardDeck;
import static factory.BlackJackCreator.createParticipants;

import domain.card.CardBundle;
import domain.card.CardDeck;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.ParticipantsResult;
import java.util.Collections;
import java.util.List;

public class BlackJackGame {

    private final CardDeck cardDeck;
    private final Participants participants;

    public BlackJackGame(CardBundle cardBundle, String userNames) {
        this.cardDeck = createCardDeck(cardBundle);
        this.participants = createParticipants(userNames);
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

    // TODO: userOpinion을 컨트롤러에게 넘기기 (여기서는 바로 addCard 호출하는 것만)
    public void receiveExtraCardProcessOfPlayer(Participant participant, String userOpinion) {
        if (userOpinion.equalsIgnoreCase("y")) {
            participant.addCard(cardDeck.getAndRemoveFrontCard());
        }
    }

    public ParticipantsResult calculateParticipantsResult() {
        return participants.calculate();
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants.getParticipants());
    }
}
