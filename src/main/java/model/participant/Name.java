package model.participant;

import java.util.List;

public class Name {

    private static final List<String> DEALER_NAMES = List.of("딜러", "dealer", "Dealer");
    private static final int DEALER_NAME_INDEX = 0;

    private final String value;

    private Name(String value) {
        this.value = value;
    }

    public static Name createDealerName() {
        return new Name(DEALER_NAMES.get(DEALER_NAME_INDEX));
    }

    public static Name createPlayerName(String value) {
        validateNotDealerName(value);
        return new Name(value);
    }

    private static void validateNotDealerName(String value) {
        if (DEALER_NAMES.contains(value)) {
            throw new IllegalArgumentException("플레이어 이름은 딜러의 이름일 수 없습니다.");
        }
    }
}
