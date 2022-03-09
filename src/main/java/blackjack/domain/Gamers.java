package blackjack.domain;

import java.util.List;
import java.util.Objects;

public class Gamers {

    private static final int MAX_GAMER = 10;
    private static final String TOO_MANY_GAMER_ERROR_MESSAGE = "최대 플레이어 수는 " + MAX_GAMER + "명 입니다.";

    private final List<Gamer> gamers;

    public Gamers(List<Gamer> gamers) {
        validateSize(gamers);

        this.gamers = gamers;
    }

    private void validateSize(List<Gamer> gamers) {
        if (gamers.size() > MAX_GAMER) {
            throw new IllegalArgumentException(TOO_MANY_GAMER_ERROR_MESSAGE);
        }
    }

    public void initCards(Deck deck) {
        for (Gamer gamer : gamers) {
            gamer.hit(deck.pick());
            gamer.hit(deck.pick());
        }
    }

    public List<Gamer> get() {
        return gamers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Gamers)) {
            return false;
        }
        Gamers gamers1 = (Gamers) o;
        return Objects.equals(gamers, gamers1.gamers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gamers);
    }

    @Override
    public String toString() {
        return "Gamers{" +
                "gamers=" + gamers +
                '}';
    }
}
