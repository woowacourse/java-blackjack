package blackjack.domain;

import java.util.Map;
import java.util.Objects;

import blackjack.domain.participant.Name;

public class Betting {

    private final Name name;
    private final long money;

    public Betting(Name name, long money) {
        validatePositiveMoney(money);
        this.name = name;
        this.money = money;
    }

    private void validatePositiveMoney(long money) {
        if (money <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양수여야 합니다.");
        }
    }

    long revenue(Map<Name, PlayRecord> recordMap) {
        return getMoney(getPlayRecord(recordMap));
    }

    private long getMoney(PlayRecord playRecord) {
        if (playRecord == PlayRecord.LOSS) {
            return -money;
        }

        if (playRecord == PlayRecord.WIN) {
            return money;
        }

        if (playRecord == PlayRecord.BLACKJACK) {
            return (long)(1.5 * money);
        }
        return 0;
    }

    private PlayRecord getPlayRecord(Map<Name, PlayRecord> recordMap) {
        return recordMap.get(name);
    }

    Name getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Betting betting = (Betting)o;
        return money == betting.money && Objects.equals(name, betting.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money);
    }
}
