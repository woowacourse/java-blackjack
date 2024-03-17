package domain.result;

public enum BlackjackResultStatus {

    WIN("승"), LOSE("패"), PUSH("무");

    private final String value;

    BlackjackResultStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
