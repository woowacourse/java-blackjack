package blackjack.domain;

import java.util.List;

public class Player extends Person {
    private static final List<String> BLACKLIST = List.of("딜러");

    public Player(String name) {
        super(name);
        validateBlacklist(name);
    }

    private void validateBlacklist(String name) {
        if (BLACKLIST.contains(name)) {
            throw new IllegalArgumentException("[ERROR] " + name + "는 사용할 수 없는 이름입니다.");
        }
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public List<Card> getInitCards() {
        return getCards();
    }

    @Override
    public boolean canDrawCard() {
        return getScore() < MAX_SCORE;
    }
}
