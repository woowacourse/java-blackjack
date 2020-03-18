package blackjack.controller.dto.response;

import java.util.Map;

public class GamersResultResponseDto {

    private final Map<String, Integer> nameMoneyTable;

    public GamersResultResponseDto(Map<String, Integer> nameMoneyTable) {
        this.nameMoneyTable = nameMoneyTable;
    }

    public Map<String, Integer> getNameMoneyTable() {
        return nameMoneyTable;
    }
}