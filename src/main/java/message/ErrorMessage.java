package message;

import static constant.GameRule.MAX_NAME_LENGTH;
import static constant.GameRule.MAX_PLAYER_NUMBER;
import static constant.GameRule.MIN_NAME_LENGTH;
import static constant.GameRule.MIN_PLAYER_NUMBER;

public enum ErrorMessage {
    INVALID_HIT_INPUT("[ERROR] 허용된 입력이 아닙니다."),
    PLAYER_NAME_OUT_OF_RANGE(String.format("[ERROR] 플레이어 이름은 %d-%d글자 사이어야 합니다.", MIN_NAME_LENGTH, MAX_NAME_LENGTH)),
    PLAYER_NAME_DUPLICATED("[ERROR] 플레이어 이름은 중복될 수 없습니다."),
    PLAYER_NUMBER_OUT_OF_RANGE(
            String.format("[ERROR] 플레이어는 %d명 이상 %d명 이하여야 합니다.", MIN_PLAYER_NUMBER, MAX_PLAYER_NUMBER)),
    PLAYER_NOT_FOUND("[ERROR] 플레이어를 찾을 수 없습니다."),
    DECK_CAN_NOT_DUPLICATED("[ERROR] 덱에 중복된 카드가 존재합니다."),
    DECK_OUT_OF_CARD_STOCK("[ERROR] 덱에 드로우할 카드가 존재하지 않습니다."),
    PLAYER_NOT_IN_GAME("[ERROR] 게임에 참여한 플레이어만 배팅이 가능합니다."),
    BETTING_MONEY_NOT_AVAILABLE("[ERROR] 배팅 금액은 양수값만 가능합니다."),
    BETTING_MONEY_MUST_BE_MULTIPLE_OF_100("[ERROR] 배팅 금액은 100원 단위로만 가능합니다."),
    PLAYER_NOT_IN_BETTING("[ERROR] 배팅에 참여한 플레이어만 수익 계산이 가능합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
