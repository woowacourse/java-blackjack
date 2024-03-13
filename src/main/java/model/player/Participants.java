package model.player;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import model.card.Card;
import model.card.Cards;

public class Participants {
    public static final int MINIMUM_PARTICIPANT_SIZE = 2;
    public static final int MAXIMUM_PARTICIPANT_SIZE = 8;
    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateNotDuplicatedParticipant(participants);
        validateParticipantSize(participants);
        this.participants = participants;
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

    public List<String> findParticipantsName() {
        return participants.stream()
                .map(participant -> participant.name).toList();
    }

    public void offerCardToParticipant(Function<String, Boolean> isMoreCard, BiConsumer<String, Cards> callback, Supplier<List<Card>> supplier) {
        for (Participant participant : participants) {
            while (participant.isHit() && isMoreCard.apply(participant.getName())) {
                participant.addCards(supplier.get());
                callback.accept(participant.getName(), participant.getCards());
            }

        }
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
