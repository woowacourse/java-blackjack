package domain.game;

import static domain.participant.Participants.CACHED_DEALER;

import controller.dto.request.PlayerBettingMoney;
import controller.dto.response.InitialCardStatus;
import controller.dto.response.ParticipantHandStatus;
import controller.dto.response.ParticipantProfitResponse;
import controller.dto.response.PlayerOutcome;
import domain.game.deck.Deck;
import domain.game.deck.DeckGenerator;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.List;
import java.util.stream.Stream;

public class BlackJackGame {
    public static final int BLACKJACK_SCORE = 21;
    public static final int BLACKJACK_CARD_SIZE = 2;
    private static final int INITIAL_CARD_SIZE = 2;
    private static final int CARD_PICK_SIZE = 1;

    private final Participants participants;
    private final Deck deck;

    private BlackJackGame(final Participants participants, final Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackJackGame from(final List<PlayerBettingMoney> requests,
                                     final DeckGenerator deckGenerator) {
        return new BlackJackGame(Participants.from(requests), deckGenerator.generate());
    }

    // TODO: 딜러가 먼저 오게 하면서 순서를 바꾸는 방법?
    public InitialCardStatus initialize() {
        List<ParticipantHandStatus> handStatuses = participants.getParticipantsStartsWithDealer()
                .stream()
                .map(participant -> {
                    participant.pickCard(deck, INITIAL_CARD_SIZE);
                    return participant.createInitialHandStatus();
                })
                .toList();
        return new InitialCardStatus(INITIAL_CARD_SIZE, handStatuses);
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

    public List<ParticipantHandStatus> createHandStatuses() {
        return participants.getParticipantsStartsWithDealer()
                .stream()
                .map(Participant::createHandStatus)
                .toList();
    }

    public List<ParticipantProfitResponse> judge() {
        Referee referee = new Referee(participants);
        List<PlayerOutcome> outcomes = referee.judge();

        return getParticipantProfitResponses(outcomes);
    }

    private List<ParticipantProfitResponse> getParticipantProfitResponses(final List<PlayerOutcome> outcomes) {
        List<ParticipantProfitResponse> playersProfit = outcomes.stream()
                .map(outcome -> outcome.player().createPlayerProfitResponse(outcome.outcome()))
                .toList();
        ParticipantProfitResponse dealerProfit = CACHED_DEALER.createDealerProfitResponse(playersProfit);

        return Stream.concat(
                Stream.of(dealerProfit),
                playersProfit.stream()
        ).toList();
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }
}
