package view;

public class InputView {
    private final static String NAME_REQUEST_TEXT = "게임에 참여할 사람의 이름을 입력하세요.(쉼펴 기준으로 분리)";
    private final static String DRAW_REQUEST_TEXT = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니요는 n)";

    public void printNameRequest() {
        System.out.println(NAME_REQUEST_TEXT);
    }

    public void printDrawRequest(String name) {
        System.out.println(DRAW_REQUEST_TEXT);
    }
}
