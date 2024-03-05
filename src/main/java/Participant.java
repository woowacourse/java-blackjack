import java.util.ArrayList;
import java.util.List;

public class Participant extends Player{
    private static final int BLACK_JACK_COUNT = 21;
    private String name;
    public Participant(String name) {
        this.name = name;
    }

    public boolean canHit() {
        return calculateScore() <= BLACK_JACK_COUNT;
    }
}
