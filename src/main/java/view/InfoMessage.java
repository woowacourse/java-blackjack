package view;

public enum InfoMessage {

    PLAYER_INPUT("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    SELECT("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

    ;


    private final String message;
    InfoMessage(String message) {
        this.message = message;
    }

    public String message() {
        return this.message;
    }
}
