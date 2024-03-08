package domain.score;

public enum Status {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
