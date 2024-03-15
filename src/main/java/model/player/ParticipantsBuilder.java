package model.player;

import model.GameMoney;
import model.card.Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParticipantsBuilder {
    public static final int MINIMUM_PARTICIPANT_SIZE = 2;
    public static final int MAXIMUM_PARTICIPANT_SIZE = 8;

    private List<Name> names;
    private List<GameMoney> gameMoneys;
    private List<Cards> cards;

    public ParticipantsBuilder names(List<Name> names) {
        validateNotDuplicatedParticipant(names);
        validateParticipantSize(names);

        this.names = new ArrayList<>(names);
        return this;
    }

    private void validateNotDuplicatedParticipant(List<Name> names) {
        Set<String> duplicates = names.stream()
                .map(Name::getName)
                .filter(name -> Collections.frequency(names, name) > 1)
                .collect(Collectors.toSet());

        if (!duplicates.isEmpty()) {
            String duplicatedName = String.join(",", duplicates);
            throw new IllegalArgumentException("중복된 이름(" + duplicatedName + ")가 있습니다, 참가자들의 이름은 중복되면 안됩니다.");
        }
    }

    private void validateParticipantSize(List<Name> names) {
        if (names.size() < MINIMUM_PARTICIPANT_SIZE || names.size() > MAXIMUM_PARTICIPANT_SIZE) {
            throw new IllegalArgumentException("참가자의 수는 2~8명이어야 합니다.");
        }
    }

    public ParticipantsBuilder gameMoneys(List<Integer> gameMoneys) {
        this.gameMoneys = gameMoneys.stream()
                .map(GameMoney::new)
                .toList();

        return this;
    }

    public ParticipantsBuilder cards(Supplier<Cards> selectCard) {
        this.cards = Stream.generate(selectCard)
                .limit(getParticipantSize()).toList();

        return this;
    }

    private int getParticipantSize() {
        if (names != null) {
            return names.size();
        }
        if (gameMoneys != null) {
            return gameMoneys.size();
        }

        throw new IllegalStateException("현재 참가자의 갯수를 셀 수 있는 인스턴스가 없습니다.");
    }

    public Participants build() {
        validateInstance();
        List<Participant> participants = IntStream.range(0, names.size())
                .mapToObj(i -> new Participant(names.get(i), cards.get(i), gameMoneys.get(i)))
                .toList();

        return new Participants(participants);
    }

    private void validateInstance() {
        if (names == null || gameMoneys == null || cards == null) {
            throw new IllegalStateException("Participants 제작에 필요한 인자 중 입력되지 않은 인자가 있습니다.");
        }

        if (names.size() != gameMoneys.size() || gameMoneys.size() != cards.size()) {
            throw new IllegalStateException("Participants 제작에 필요한 인자들의 리스트 크기가 다릅니다.");
        }
    }
}
