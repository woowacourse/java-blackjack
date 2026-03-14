package presentation.ui;

public enum ViewMessage {
    CARD_STATUS("%s카드: %s"),
    CARD_MORD_MESSAGE("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"),
    DEALER_DRAW_MESSAGE("딜러는 16이하라 한장의 카드를 더 받았습니다."),
    RESULT_MESSAGE(" - 결과: %d"),
    DISTRIBUTE_MESSAGE("%s와 %s에게 2장을 나누었습니다."),
    PLAYER_NAME_MESSAGE("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    PLAYER_BET_AMOUNT_MESSAGE("%s의 배팅 금액은?"),
    FINAL_GAME_RESULT_MESSAGE("## 최종 승패"),
    MEMBER_GAME_RESULT_MESSAGE("%s: %d");

    private final String word;

    ViewMessage(String word) {
        this.word = word;
    }

    public String format(Object... args) {
        return String.format(word, args);
    }
}
