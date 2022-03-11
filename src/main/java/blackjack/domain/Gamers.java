package blackjack.domain;

import java.util.List;
import java.util.Objects;

public class Gamers {

    private static final int MAX_GAMER = 10;
    private static final String TOO_MANY_GAMER_ERROR_MESSAGE = "최대 플레이어 수는 " + MAX_GAMER + "명 입니다.";
    private static final String DUPLICATE_NAME_ERROR_MESSAGE = "플레이어의 이름이 중복되었습니다.";
    private static final String SAME_DEALER_NAME_ERROR_MESSAGE = "플레이어의 이름가 딜러면 안된다.";
    private static final String DEALER_NAME = "딜러";

    private final List<Gamer> gamers;

    public Gamers(List<Gamer> gamers) {
        validateSize(gamers);
        validateDuplicateName(gamers);
        validateSameDealerName(gamers);

        this.gamers = gamers;
    }

    private void validateSize(List<Gamer> gamers) {
        if (gamers.size() > MAX_GAMER) {
            throw new IllegalArgumentException(TOO_MANY_GAMER_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateName(List<Gamer> gamers) {
        int distinctNameCount = (int) gamers.stream()
                .map(gamer -> gamer.getName().get())
                .distinct()
                .count();

        if (gamers.size() != distinctNameCount) {
            throw new IllegalArgumentException(DUPLICATE_NAME_ERROR_MESSAGE);
        }
    }

    private void validateSameDealerName(List<Gamer> gamers) {
        boolean hasSameDealerName = gamers.stream()
                .anyMatch(gamer -> gamer.getName().get().equals(DEALER_NAME));

        if (hasSameDealerName) {
            throw new IllegalArgumentException(SAME_DEALER_NAME_ERROR_MESSAGE);
        }
    }

    public void dealCards(Deck deck) {
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
