package blackjack.domain.participant;

public enum Role {
    PLAYER("player"),
    DEALER("dealer"),
    ;

    private String name;

    Role(String name) {
        this.name = name;
    }
}
