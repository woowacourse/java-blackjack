package view;

public class OutputView {
    private static final String INPUT_PLAYER_MESSAGE= "게임에 참여할 사람의 이름을 입력하세요.(쉼표  기준으로 분리)";
    private static final String HIT_OR_STAND_MESSAGE= "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private static final String DEALER_HIT_MESSAGE= "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public static void inputPlayerMessage(){
        System.out.println(INPUT_PLAYER_MESSAGE);
    }

    public static void hitOrStandMessage(String name){
        System.out.printf(HIT_OR_STAND_MESSAGE, name);
    }

    public static void dealerHitMessage(){
        System.out.println(DEALER_HIT_MESSAGE);
    }
}
