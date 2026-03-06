public class InputView {

    private static final String ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_HIT_OR_STAND = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";

    public static String askPlayerNames() {
        System.out.println(ASK_PLAYER_NAMES);
        return System.console().readLine();
    }

    public static String askHitOrStand(String playerName) {
        System.out.printf(ASK_HIT_OR_STAND, playerName);
        return System.console().readLine();
    }
}
