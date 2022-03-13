package blackjack.dto;

import blackjack.domain.Record;

public class PlayerRecordDto {

    private final String name;
    private final Record record;

    private PlayerRecordDto(final String name, final Record record) {
        this.name = name;
        this.record = record;
    }

    public static PlayerRecordDto of(String name, Record record) {
        return new PlayerRecordDto(name, record);
    }

    public String getName() {
        return name;
    }

    public Record getRecord() {
        return record;
    }
}
