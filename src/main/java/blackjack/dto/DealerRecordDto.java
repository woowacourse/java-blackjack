package blackjack.dto;

import blackjack.domain.Record;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealerRecordDto {

    private final Map<Record, Integer> map;

    private DealerRecordDto(final Map<Record, Integer> map) {
        this.map = map;
    }

    public static DealerRecordDto of(final List<PlayerRecordDto> dtos) {
        final Map<Record, Integer> map = new HashMap<>();
        for (PlayerRecordDto dto : dtos) {
            final Record record = Record.fromOpposite(dto.getRecord().getName());
            map.put(record, map.getOrDefault(record, 0) + 1);
        }

        return new DealerRecordDto(map);
    }

    public int getValue(Record key) {
        return map.getOrDefault(key, 0);
    }
}
