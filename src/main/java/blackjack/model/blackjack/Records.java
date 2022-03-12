package blackjack.model.blackjack;

import blackjack.model.player.Name;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class Records {

    private final Map<Name, Result> records;

    public Records(Map<Name, Result> records) {
        this.records = records;
    }

    public Result resultByName(Name name) {
        if (!records.containsKey(name)) {
            throw new IllegalArgumentException(
                String.format("%s의 이름을 가진 플레이어가 없습니다.", name.value()));
        }
        return records.get(name);
    }

    public Collection<Result> results() {
        return Collections.unmodifiableCollection(records.values());
    }
}
