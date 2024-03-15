package model.player;

import model.Outcome;
import model.card.Card;
import model.card.Cards;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public record Participants(List<Participant> participants) {
    public static final int MINIMUM_PARTICIPANT_SIZE = 2;
    public static final int MAXIMUM_PARTICIPANT_SIZE = 8;

    public Participants {
        validateNotDuplicatedParticipant(participants);
        validateParticipantSize(participants);
    }

    private void validateNotDuplicatedParticipant(List<Participant> participants) {
        Set<User> duplicates = participants.stream()
                .filter(n -> Collections.frequency(participants, n) > 1)
                .collect(Collectors.toSet());

        if (!duplicates.isEmpty()) {
            String duplicatedName = duplicates.stream()
                    .map(User::getName)
                    .collect(Collectors.joining(","));
            throw new IllegalArgumentException("중복된 이름(" + duplicatedName + ")가 있습니다, 참가자들의 이름은 중복되면 안됩니다.");
        }
    }

    private void validateParticipantSize(List<Participant> participants) {
        if (participants.size() < MINIMUM_PARTICIPANT_SIZE || participants.size() > MAXIMUM_PARTICIPANT_SIZE) {
            throw new IllegalArgumentException("참가자의 수는 2~8명이어야 합니다.");
        }
    }

    public void offerCardToParticipants(Predicate<String> inputForMoreCard, BiConsumer<String, Cards> printParticipantsCard, Supplier<Card> selectCard) {
        for (Participant participant : participants) {
            while (participant.isHit() && inputForMoreCard.test(participant.getName())) {
                participant.addCard(selectCard.get());
                printParticipantsCard.accept(participant.getName(), participant.getCards());
            }
        }
    }

    public List<String> findParticipantsName() {
        return participants.stream()
                .map(participant -> participant.name).toList();
    }

    public Map<Participant, Outcome> matchParticipantsOutcome(Dealer dealer) {
        return participants.stream().collect(
                toMap(
                        participant -> participant,
                        participant -> participant.findOutcome(dealer)
                ));
    }

    public Map<String, Cards> matchParticipantNameAndCards() {
        return participants.stream()
                .collect(toMap(
                        User::getName,
                        User::getCards));
    }

    public Map<String, Integer> matchNameAndRevenues(Dealer dealer) {
        return participants.stream()
                .collect(toMap(
                        User::getName,
                        participant -> participant.calculateRevenue(dealer)
                ));
    }

    @Override
    public List<Participant> participants() {
        return Collections.unmodifiableList(participants);
    }
}
