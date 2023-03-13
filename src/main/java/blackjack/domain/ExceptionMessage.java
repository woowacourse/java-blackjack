package blackjack.domain;

public class ExceptionMessage {

    public static final String INVALID_PARTICIPANT_NAME_EMPTY = "이름은 빈 문자열이거나 공백일 수 없습니다.";
    public static final String INVALID_PLAYERS_COUNT_FORMAT = "플레이어 인원 수는 최소 %d명 최대 %d명입니다.";
    public static final String INVALID_BETTING_AMOUNT_FORMAT = "베팅 금액은 %d원 이상이어야 합니다.";
    public static final String INVALID_PLAYER_NAMES_DUPLICATED = "플레이어 이름은 중복될 수 없습니다.";
    public static final String INVALID_PLAYER_NAMES_RESERVED_FORMAT = "플레이어 이름은 딜러 이름(%s)과 중복될 수 없습니다.";
    public static final String INVALID_TAKEOUT_CARDS_EMPTY = "더 이상 꺼낼 카드가 없습니다.";
    public static final String INVALID_HAND_CARDS_EMPTY = "카드가 존재하지 않는 핸드입니다.";
    public static final String HIT_COMMAND_NOT_FOUND = "키워드에 해당하는 명령어가 존재하지 않습니다.";
}
