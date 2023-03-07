package service;

import domain.PlayerGameResult;
import domain.card.Deck;
import domain.card.ShuffleStrategy;
import domain.participant.Participant;
import domain.participant.Participants;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BlackjackService {

    private static final String HIT = "y";
    private static final String STAND = "n";
    public static final String ERROR_HIT_OPERATION = "[ERROR] y 또는 n만 입력할 수 있습니다";

    private final Deck deck;
    private final Participants participants;

    private BlackjackService(Deck deck, Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public static BlackjackService of(List<String> playersName, ShuffleStrategy shuffleStrategy) {
        Deck deck = Deck.create(shuffleStrategy);
        Participants participants = Participants.of(playersName, deck);

        return new BlackjackService(deck, participants);
    }

    public Optional<Participant> getNextPlayer() {
        return participants.getNextTurnPlayer();
    }

    public void nextTurn(Participant next, String hitSelection) {
        if (STAND.equals(hitSelection)) {
            next.stand();
            return;
        }
        if (HIT.equals(hitSelection)) {
            next.addCard(deck.pollAvailableCard());
            return;
        }

        throw new IllegalArgumentException(ERROR_HIT_OPERATION);
    }

    public void dealerTurn() {
        participants.playDealerTurn(deck);
    }

    public boolean isDealerStand() {
        return participants.isDealerStand();
    }

    public List<String> getPlayersName() {
        return participants.getPlayersName();
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
