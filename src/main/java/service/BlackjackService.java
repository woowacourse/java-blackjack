package service;

import domain.PlayerGameResult;
import domain.card.Deck;
import domain.participant.Participant;
import domain.participant.Participants;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackService {

    private static final String HIT = "y";
    private static final String STAND = "n";
    private static final String ERROR_INVALID_COMMAND = "[ERROR] y 혹은 n만 입력하실 수 있습니다.";

    private final Deck deck;
    private final Participants participants;

    private BlackjackService(Participants participants) {
        this.deck = Deck.create();
        this.participants = participants;
    }

    public static BlackjackService of(List<String> playersName) {
        return new BlackjackService(Participants.of(playersName));
    }

    public void start() {
        participants.initHand(deck);
    }

    public boolean existNextPlayerTurn() {
        return participants.getNextTurnPlayer().isPresent();
    }

    public Participant getNextPlayer() {
        return participants.getNextTurnPlayer().get();
    }

    public void nextTurn(String hit) {
        Participant nextPlayer = participants.getNextTurnPlayer().get();

        if (hit.equals(STAND)) {
            nextPlayer.stand();
            return;
        }
        if (hit.equals(HIT)) {
            nextPlayer.addCard(deck.pollAvailableCard());
            return;
        }
        throw new IllegalArgumentException(ERROR_INVALID_COMMAND);
    }

    public void dealerTurn() {
        participants.playDealerTurn(deck);
    }

    public boolean isDealerStand() {
        return participants.isDealerStand();
    }

    public List<String> getPlayersName() {
        return participants.getPlayers().stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }

    public Map<String, PlayerGameResult> getResult() {
        return participants.getResult();
    }

    public Participant getDealer() {
        return participants.getDealer();
    }

    public List<Participant> getPlayers() {
        return participants.getPlayers();
    }
}
