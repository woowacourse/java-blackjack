package blackjack.domain.participant;

public class Name {
    private static final String NAME_BLANK_ERROR_MESSAGE = "공백으로만 이루어진 이름은 사용할 수 없습니다.";
    private static final String NAME_DEALER_ERROR_MESSAGE = "'딜러'는 플레이어 이름으로 사용할 수 없습니다.";

    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name.trim();
    }

    private void validateName(String name) {
        validateNameBlank(name);
        validateNameNotDealer(name);
    }

    private static void validateNameBlank(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException(NAME_BLANK_ERROR_MESSAGE);
        }
    }

    private static void validateNameNotDealer(String name) {
        if (name.equals(Dealer.DEALER_NAME)) {
            throw new IllegalArgumentException(NAME_DEALER_ERROR_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }
}
