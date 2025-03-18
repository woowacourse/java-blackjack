package blackjack.model.participants;

public enum ParticipantType {
    PLAYER,
    DEALER;

    public static boolean isDealer(ParticipantType type) {
        return type == DEALER;
    }
}
