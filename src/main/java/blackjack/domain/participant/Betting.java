package blackjack.domain.participant;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import blackjack.domain.PlayRecord;

public final class Betting {

    private static final double BLACKJACK_MULTIPLIER = 1.5;

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

    Map<Name, Long> addRecord(Map<Name, Long> revenueMap, Map<Name, PlayRecord> recordMap) {
        Map<Name, Long> result = new LinkedHashMap<>(revenueMap);
        result.put(name, revenue(Map.copyOf(recordMap)));
        return result;
    }

    long revenue(Map<Name, PlayRecord> recordMap) {
        return calculateMoney(recordMap.get(name));
    }

    private long calculateMoney(PlayRecord playRecord) {
        if (playRecord == PlayRecord.LOSS) {
            return -money;
        }

        if (playRecord == PlayRecord.WIN) {
            return money;
        }

        if (playRecord == PlayRecord.BLACKJACK) {
            return (long)(BLACKJACK_MULTIPLIER * money);
        }
        return 0;
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
