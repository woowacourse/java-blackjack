package blackjack.domain.result.ResponseDTO;

import java.util.Objects;

import static blackjack.domain.card.Card.NULL_ERR_MSG;

public class WinningDTO {
    private final String name;
    private final String record;

    public WinningDTO(String name, String record) {
        Objects.requireNonNull(name, NULL_ERR_MSG);
        Objects.requireNonNull(record, NULL_ERR_MSG);
        this.name = name;
        this.record = record;
    }

    public String getName() {
        return name;
    }

    public String getRecord() {
        return record;
    }
}
