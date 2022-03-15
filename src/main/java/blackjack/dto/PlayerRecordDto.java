package blackjack.dto;

import blackjack.domain.Record;

public class PlayerRecordDto {

    private final String name;
    private final String record;

    private PlayerRecordDto(final String name, final String record) {
        this.name = name;
        this.record = record;
    }

    public static PlayerRecordDto of(String name, Record record) {
        return new PlayerRecordDto(name, record.getName());
    }

    public String getName() {
        return name;
    }

    public String getRecord() {
        return record;
    }
}
