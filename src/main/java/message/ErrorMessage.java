package message;

import static domain.participant.BetAmount.BET_AMOUNT_UNIT;
import static domain.participant.BetAmount.MAX_BET_AMOUNT;
import static domain.participant.BetAmount.MIN_BET_AMOUNT;
import static domain.participant.Name.MAX_NAME_LENGTH;
import static domain.participant.Name.MIN_NAME_LENGTH;
import static domain.participant.Players.MAX_PLAYER_NUMBER;
import static domain.participant.Players.MIN_PLAYER_NUMBER;

public enum ErrorMessage {
    INVALID_HIT_INPUT("[ERROR] 허용된 입력이 아닙니다."),
    PLAYER_NAME_OUT_OF_RANGE(String.format("[ERROR] 플레이어 이름은 %d-%d글자 사이어야 합니다.", MIN_NAME_LENGTH, MAX_NAME_LENGTH)),
    PLAYER_NAME_DUPLICATED("[ERROR] 플레이어 이름은 중복될 수 없습니다."),
    PLAYER_NUMBER_OUT_OF_RANGE(
            String.format("[ERROR] 플레이어는 %d명 이상 %d명 이하여야 합니다.", MIN_PLAYER_NUMBER, MAX_PLAYER_NUMBER)),
    PLAYER_NOT_FOUND("[ERROR] 플레이어를 찾을 수 없습니다."),
    DECK_CAN_NOT_DUPLICATED("[ERROR] 덱에 중복된 카드가 존재합니다."),
    DECK_IS_EMPTY("[ERROR] 더 이상 뽑을 카드가 없습니다."),
    BET_AMOUNT_OUT_OF_RANGE(
            String.format("[ERROR] 배팅 금액은 %,d원 이상, %,d원 이하여야 합니다.", MIN_BET_AMOUNT, MAX_BET_AMOUNT)),
    BET_AMOUNT_INVALID_UNIT(
            String.format("[ERROR] 배팅 금액은 %,d원 단위로만 입력할 수 있습니다.", BET_AMOUNT_UNIT)),
    BET_AMOUNT_INVALID_FORMAT("[ERROR] 배팅 금액은 숫자만 입력할 수 있습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
