package view;

public class Message {
    public final static String INPUT_PARTICIPANTS_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public final static String DEAL_CARDS_MESSAGE = "\n딜러와 %s에게 2장을 나누었습니다.";
    public final static String DEALER_CARDS_MESSAGE = "딜러카드: %s, {blind}";
    public final static String DEALER_FINAL_CARDS_MESSAGE = "딜러카드: %s";
    public final static String REQUEST_GET_EXTRA_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    public final static String DEALER_CARD_RECEIVE_ANNOUNCE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    public final static String FINAL_RESULT_ANNOUNCE = "\n## 최종 수익";
    public static final String INPUT_BET_AMOUNT_MESSAGE = "%s의 배팅 금액은?";
    public static final String BUST_ANNOUNCE = "버스트 발생! 카드를 더 받을 수 없습니다.";
}
