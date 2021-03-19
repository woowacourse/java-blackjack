package blakcjack.domain.participant;

public enum ParticipantType {
    DEALER("딜러"),
    PLAYER("플레이어");

    private final String description;

    ParticipantType(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
