package constant;

public enum ErrorMessage {
    NO_CARD_IN_DECK("카드 뭉치에 더 이상 남아있는 카드가 없습니다."),
    INPUT_IS_BLANK("빈 값을 입력하셨습니다."),

    INVALID_CONDITION_INPUT("유효한 형식의 입력으로 넣어주세요(y 또는 n)"),

    DUPLICATED_CARD_IN_DECK("덱에 중복된 카드가 있습니다."),

    NO_EMPTY_NAME("이름은 공백으로 가질 수 없습니다."),
    NO_PLAYER_NAME_DEALER("플레이어는 '딜러'라는 이름을 가질 수 없습니다."),
    DUPLICATED_NAME("중복된 이름이 있습니다."),
    NO_PLAYER_NAME("해당 이름을 갖는 플레이어가 업습니다."),

    INVALID_BET_INPUT("정수만 입력할 수  있습니다."),
    OUT_OF_RANGE_BET("배팅 금액은 10억 이하의 양의 정수만 가능합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
