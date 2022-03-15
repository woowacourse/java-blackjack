package blackjack.dto;

import blackjack.domain.Record;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DealerRecordDto {

    private final Map<String, Integer> map;

    private DealerRecordDto(final Map<String, Integer> map) {
        this.map = map;
    }

    public static DealerRecordDto from(final List<PlayerRecordDto> dtos) {
        final Map<String, Integer> map = initMap();

        for (PlayerRecordDto dto : dtos) {
            final Record record = Record.fromOppositeName(dto.getRecord().getName());
            map.put(record.getName(), map.get(record.getName()) + 1);
        }
        return new DealerRecordDto(map);
    }

    private static Map<String, Integer> initMap() {
        return Arrays.stream(Record.values())
                .map(Record::getName)
                .collect(Collectors.toMap(
                        recordName -> recordName,
                        recordName -> 0,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public Set<String> getKeys() {
        return map.keySet();
    }

    public int getValue(String key) {
        return map.get(key);
    }
}
