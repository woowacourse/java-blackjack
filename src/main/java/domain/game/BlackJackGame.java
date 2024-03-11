package domain.game;

import controller.dto.HandStatus;
import controller.dto.InitialCardStatus;
import controller.dto.JudgeResult;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    public static final int BLACKJACK_SCORE = 21;
    private static final int INITIAL_CARD_SIZE = 2;
    private static final int CARD_PICK_SIZE = 1;

    private final Participants participants;
    private final Deck deck;

    public BlackJackGame(final Participants participants, final Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackJackGame from(final List<String> playerNames) {
        return new BlackJackGame(Participants.from(playerNames), new Deck());
    }

    public InitialCardStatus initialize() {
        List<HandStatus> status = new ArrayList<>();
        status.add(createHandStatusAfterPick(participants.getDealer()));

        for (Player player : participants.getPlayers()) {
            status.add(createHandStatusAfterPick(player));
        }
        return new InitialCardStatus(INITIAL_CARD_SIZE, status);
    }

    private HandStatus createHandStatusAfterPick(final Participant participant) {
        participant.pickCard(deck, INITIAL_CARD_SIZE);
        return participant.createHandStatus();
    }

    public List<HandStatus> createHandStatuses() {
        List<HandStatus> status = new ArrayList<>();
        status.add(participants.getDealer().createHandStatus());

        for (Player player : participants.getPlayers()) {
            status.add(player.createHandStatus());
        }
        return status;
    }


    public void giveCard(
            final Participant participant,
            final ActionAfterPick action,
            final DecisionToContinue decision
    ) {
        while (participant.canPickCard(decision)) {
            participant.pickCard(deck, CARD_PICK_SIZE);
            HandStatus currentStatus = participant.createHandStatus();
            action.accept(currentStatus);
        }
    }

    public JudgeResult judge() {
        Referee referee = new Referee(participants);
        return referee.judge();
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }
}
