package blackjack;

import java.util.Map;

import blackjack.domain.PlayRecord;
import blackjack.domain.participant.Name;

public class Betting {
    private final Name name;
    private final long money;

    public Betting(Name name, long money) {
        this.name = name;
        this.money = money;
    }

    public long result(PlayRecord playRecord) {
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

    public Name getName() {
        return name;
    }

    public PlayRecord getPlayRecord(Map<Name, PlayRecord> recordMap) {
        return recordMap.get(name);
    }

    public long result(Map<Name, PlayRecord> recordMap) {
        return result(getPlayRecord(recordMap));
    }
}
