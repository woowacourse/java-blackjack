package domain.participant;

import static error.ErrorMessage.NOT_EXIST_DEALER;

import domain.card.CardDeck;
import error.exception.InvalidParticipantSizeException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participants {

    private static final int PLAYER_MAX_SIZE = 8;
    private static final int DEALER_MAX_SIZE = 1;

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateParticipantSize(participants);
        this.participants = participants;
    }

    public void initCards(CardDeck cardDeck) {
        for (Participant participant : participants) {
            participant.addCard(cardDeck.getAndRemoveFrontCard());
            participant.addCard(cardDeck.getAndRemoveFrontCard());
        }
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public ParticipantsResult calculateOfResult() {
        Participant dealer = findDealer();
        Map<Participant, GameResult> playersResult = new HashMap<>();
        Map<GameResult, Integer> dealerResult = new HashMap<>();
        calculateResultOfParticipant(dealer, dealerResult, playersResult);
        return new ParticipantsResult(playersResult, dealerResult);
    }

    private void calculateResultOfParticipant(Participant dealer,
        Map<GameResult, Integer> dealerResult,
        Map<Participant, GameResult> playersResult) {
        for (Participant participant : participants) {
            addResultOfParticipant(dealer, dealerResult, playersResult, participant);
        }
    }

    private void addResultOfParticipant(Participant dealer, Map<GameResult, Integer> dealerResult,
        Map<Participant, GameResult> playersResult, Participant participant) {
        if (participant.isPlayer()) {
            GameResult gameResult = GameResult.calculateResultOfPlayer(dealer, participant);
            dealerResult.put(gameResult.convertOfDealer(),
                dealerResult.getOrDefault(gameResult.convertOfDealer(), 0) + 1);
            playersResult.put(participant, gameResult);
        }
    }

    private Participant findDealer() {
        return participants.stream()
            .filter(participant -> !participant.isPlayer())
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(NOT_EXIST_DEALER.getMessage()));
    }

    private void validateParticipantSize(List<Participant> participants) {
        if (participants.size() > PLAYER_MAX_SIZE + DEALER_MAX_SIZE) {
            throw new InvalidParticipantSizeException(PLAYER_MAX_SIZE + DEALER_MAX_SIZE);
        }
    }
}
