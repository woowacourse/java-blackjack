package model.participants;

public enum ParticipantType {
    PLAYER("플레이어"),
    DEALER("딜러");

    public static final String TYPE_PLAYER = "플레이어";
    public static final String TYPE_DEALER = "딜러";

    private final String type;

    ParticipantType(String type) {
        this.type = type;
    }

    public static boolean isDealer(String type) {
        return type.equals(DEALER.type);
    }
}
