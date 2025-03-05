package domain.participant;

public class Dealer implements Participant {
    private final String nickname;

    private Dealer(final String nickname) {
        this.nickname = nickname;
    }

    public static Dealer generate() {
        return new Dealer("딜러");
    }

}
