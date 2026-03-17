package message;

public enum IOMessage {
    ASK_GAME_PARTICIPANT("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    ASK_HIT_OR_STAND("는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"),
    ASK_PLAYER_BETTING("의 배팅 금액은?"),
    INVALID_HIT_OR_STAND_DECISION("잘못된 입력입니다. y 또는 n만 입력해주세요."),
    DEALER_HIT_ONE_CARD("딜러는 16이하라 한장의 카드를 더 받았습니다."),
    WINNING_STATISTICS("## 최종 승패");

    private final String message;

    IOMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
