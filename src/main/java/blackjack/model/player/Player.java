package blackjack.model.player;

import java.util.Objects;

public class Player extends Participant {
    private final String name;
    private final BettingMoney bettingMoney;

    public Player(String name, int bettingMoney) {
        this.bettingMoney = new BettingMoney(bettingMoney);
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("참여자 이름을 입력해주세요.");
        }
        if (name.length() < 2 || name.length() > 5) {
            throw new IllegalArgumentException("참여자 이름은 2~5글자 입니다.");
        }
    }


    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player that = (Player) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }


}
