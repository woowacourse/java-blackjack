package domain.result;

public enum ResultInfo {
    WIN("승", 0),
    DRAW("무", 1),
    DEFEAT("패", 2)
    ;

    private final String info;
    private final int code;

    ResultInfo(String info, int code) {
        this.info = info;
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public int getCode() {
        return code;
    }
}
