package domain.game;

import static domain.constants.Outcome.WIN;

import controller.dto.InitialCardStatus;
import controller.dto.JudgeResult;
import controller.dto.ParticipantHandStatus;
import controller.dto.PlayerOutcome;
import domain.game.deck.Deck;
import domain.game.deck.DeckGenerator;
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

    private BlackJackGame(final Participants participants, final Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackJackGame from(final List<String> playerNames, final DeckGenerator deckGenerator) {
        return new BlackJackGame(Participants.from(playerNames), deckGenerator.generate());
    }

    public InitialCardStatus initialize() {
        List<ParticipantHandStatus> status = new ArrayList<>();
        status.add(createInitialHandStatusAfterPick(participants.getDealer()));

        for (Player player : participants.getPlayers()) {
            status.add(createInitialHandStatusAfterPick(player));
        }
        return new InitialCardStatus(INITIAL_CARD_SIZE, status);
    }

    private ParticipantHandStatus createInitialHandStatusAfterPick(final Participant participant) {
        participant.pickCard(deck, INITIAL_CARD_SIZE);
        return participant.createInitialHandStatus();
    }

    public List<ParticipantHandStatus> createHandStatuses() {
        List<ParticipantHandStatus> status = new ArrayList<>();
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
            ParticipantHandStatus currentStatus = participant.createHandStatus();
            action.accept(currentStatus);
        }
    }

    public JudgeResult judge() {
        Referee referee = new Referee(participants);
        List<PlayerOutcome> outcomes = referee.judge();

        return new JudgeResult(outcomes, countWinner(outcomes));
    }

    private int countWinner(List<PlayerOutcome> results) {
        return (int) results.stream()
                .filter(result -> WIN.equals(result.outcome()))
                .count();
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }
}
