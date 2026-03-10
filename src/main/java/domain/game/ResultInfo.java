package domain.game;

public enum ResultInfo {

    WIN("승"),
    DRAW("무"),
    DEFEAT("패");

    private final String info;

    ResultInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
