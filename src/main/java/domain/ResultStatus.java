package domain;

public enum ResultStatus {
    WIN("승"),
    LOSE("패"),
    PUSH("무승부")
    ;

    private String status;

    ResultStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
