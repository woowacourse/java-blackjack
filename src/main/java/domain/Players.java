package domain;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class Players {

    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 8;

    private final List<Participant> names;

    public Players(final List<Participant> names) {
        this.names = names;
    }

    public static Players from(final List<String> names) {
        List<Participant> participantNames = mapToPlayers(names);
        validate(participantNames);
        return new Players(participantNames);
    }

    public void forEach(Consumer<? super Participant> action) {
        names.forEach(action);
    }

    public boolean isAllBust() {
        return names.stream()
                .allMatch(Participant::isBust);
    }

    private static List<Participant> mapToPlayers(final List<String> names) {
        return names.stream()
                .map(String::trim)
                .map(name -> new Participant(name, Hands.createEmptyPacket()))
                .toList();
    }

    private static void validate(final List<Participant> participants) {
        validateSize(participants);
        validateDuplicate(participants);
    }

    private static void validateSize(final List<Participant> participants) {
        if (participants.size() < MIN_SIZE || MAX_SIZE < participants.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 참여자 수입니다.");
        }
    }

    private static void validateDuplicate(final List<Participant> participants) {
        if (participants.size() != Set.copyOf(participants).size()) {
            throw new IllegalArgumentException("[ERROR] 참여자 이름은 중복될 수 없습니다.");
        }
    }

    public List<Participant> getPlayers() {
        return names;
    }
}
