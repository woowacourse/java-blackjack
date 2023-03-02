package view;

public class OutputVIew {

    public static final String INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    public void printInputPlayerNameMessage() {
        printMessage(INPUT_PLAYER_NAME_MESSAGE);
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }
}
