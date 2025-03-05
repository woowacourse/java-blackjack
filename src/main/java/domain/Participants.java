package domain;

import java.util.List;

public class Participants {
    private static final int MAX_SIZE = 5;

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateSize(participants);
        this.participants = participants;
    }

    private void validateSize(List<Participant> participants) {
        if (participants.isEmpty() || participants.size() > MAX_SIZE) {
            throw new IllegalArgumentException("참여자 수는 최소 1인 이상 최대 5인 이하여야 합니다.");
        }
    }

    public void distributeTwoCards(List<Card> cards) {
        if (cards.size() != 2 * size()) {
            throw new IllegalArgumentException(size() + ": 카드가 부족해서 2장씩 나눠줄 수 없습니다.");
        }

        for (Participant participant : participants) {
            participant.receiveCards(List.of(cards.removeLast(), cards.removeLast()));
        }
    }

    public int size() {
        return participants.size();
    }
}
