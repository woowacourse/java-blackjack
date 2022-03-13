package blackjack.domain.participant;

import static java.util.Map.entry;

import blackjack.domain.CardDeck;
import blackjack.domain.GameOutcome;
import blackjack.domain.card.Card;
import blackjack.dto.OutComeResult;
import blackjack.dto.ParticipantCards;
import blackjack.dto.ParticipantScoreResult;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants;
    private int currentTurnIndex;

    public Participants(final List<Participant> participants) {
        Objects.requireNonNull(participants, "participants는 null로 생성할 수 없습니다.");
        this.participants = new ArrayList<>(participants);
        validateParticipantsSize(participants);
        validateDuplicationPlayers(this.participants);
    }

    private void validateParticipantsSize(final List<Participant> participants) {
        if (participants.isEmpty()) {
            throw new IllegalArgumentException("참가자는 0명이 될 수 없습니다.");
        }
    }

    private void validateDuplicationPlayers(final List<Participant> participants) {
        if (calculateDistinctCount(participants) != participants.size()) {
            throw new IllegalArgumentException("이름 간에 중복이 있으면 안됩니다.");
        }
    }

    private int calculateDistinctCount(final List<Participant> participants) {
        return (int) participants.stream()
                .map(Participant::getName)
                .distinct()
                .count();
    }

    public static Participants createByPlayerNames(final List<String> playerNames, final CardDeck cardDeck) {
        final List<Participant> players = createPlayers(playerNames, cardDeck);
        return new Participants(players);
    }

    private static List<Participant> createPlayers(final List<String> playerNames, final CardDeck cardDeck) {
        return playerNames.stream()
                .map(name -> createPlayer(name, cardDeck))
                .collect(Collectors.toList());
    }

    private static Player createPlayer(final String name, final CardDeck cardDeck) {
        return Player.createNewPlayer(name, cardDeck.provideFirstHitCards());
    }

    public List<ParticipantCards> getFirstCards() {
        return participants.stream()
                .map(ParticipantCards::toParticipantFirstCards)
                .collect(Collectors.toUnmodifiableList());
    }

    public void turnToNextParticipant() {
        validateAllTurnEnd();
        currentTurnParticipant().changeFinishStatus();
        currentTurnIndex++;
    }

    private void validateAllTurnEnd() {
        if (isAllTurnEnd()) {
            throw new IllegalStateException("모든 턴이 종료되었습니다.");
        }
    }

    public boolean isAllTurnEnd() {
        return participants.size() <= currentTurnIndex;
    }

    public ParticipantCards hitCurrentParticipant(final Card card) {
        final Participant currentParticipant = currentTurnParticipant();
        currentParticipant.hit(card);
        checkCanTurnNext(currentParticipant);
        return ParticipantCards.toParticipantCards(currentParticipant);
    }

    private void checkCanTurnNext(final Participant currentParticipant) {
        if (!currentParticipant.canHit()) {
            currentTurnIndex++;
        }
    }

    private Participant currentTurnParticipant() {
        validateAllTurnEnd();
        return participants.get(currentTurnIndex);
    }

    public String getCurrentParticipantName() {
        validateAllTurnEnd();
        return participants.get(currentTurnIndex).getName();
    }

    public ParticipantCards getCurrentParticipantCards() {
        return ParticipantCards.toParticipantCards(currentTurnParticipant());
    }

    public List<ParticipantScoreResult> getParticipantScoreResults() {
        return participants.stream()
                .map(ParticipantScoreResult::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public OutComeResult outcomeResult(Participant dealer) {
        return OutComeResult.from(calculateOutcomeResultWithDealer(dealer));
    }

    private Map<String, GameOutcome> calculateOutcomeResultWithDealer(final Participant dealer) {
        return participants.stream()
                .map(participant -> entry(participant.getName(), participant.fight(dealer)))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }
}
