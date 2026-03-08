package blackjack.domain.participant;

public enum Role {
    PLAYER("플레이어"),
    DEALER("딜러"),
    ;

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
