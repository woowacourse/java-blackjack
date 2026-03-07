package domain.game_result;

public enum ResultInfo {

    WIN("승"),
    DEFEAT("패"),
    DRAW("무");

    private final String info;

    ResultInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
