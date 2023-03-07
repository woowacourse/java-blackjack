package domain.participant;

public enum ParticipantOffset {

    DEALER(0),
    PLAYER(1);

    private final int offset;

    ParticipantOffset(final int offset) {
        this.offset = offset;
    }

    public int mapToIndexFromOrder(final int participantOrder) {
        return this.offset + participantOrder;
    }
}
