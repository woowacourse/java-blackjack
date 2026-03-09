package domain.constant;

public enum DealerName {

    DEFAULT("딜러"),
    ;

    private final String name;

    DealerName(String name) {
        this.name = name;
    }

    public String dealerName() {
        return name;
    }
}
