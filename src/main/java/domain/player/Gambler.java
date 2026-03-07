package domain.player;

public class Gambler extends Participant{

    // player마다 다른 전략(확장)
    // Hit or Stand 결정

    public Gambler(String name) {
        super(name);
    }
}
