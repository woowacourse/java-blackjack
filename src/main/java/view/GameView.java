package view;

import java.io.BufferedReader;
import java.io.IOException;

public class GameView {

    private static final String YES = "y";
    private static final String NO = "n";

    private final BufferedReader inputReader;

    public GameView(BufferedReader inputReader) {
        this.inputReader = inputReader;
    }

    public void askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
    }

    public String getPlayerNameInput() throws IOException {
        return inputReader.readLine();
    }

    public void giveInitCardAlert(String dealer, String names, int cards) {
        System.out.println(dealer + "와 " + names + "에게 " + cards + "장을 나누었습니다.");
        System.out.println();
    }

    public void print(String input) {
        System.out.println(input);
    }

    public boolean isWantMoreCard(String name) throws IOException {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 " + YES + ", 아니오는 " + NO + ")");

        String answer = getMoreCardInput();
        while (isAnswerNotValid(answer)) {
            System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 " + YES + ", 아니오는 " + NO + ")");
            answer = getMoreCardInput();
        }

        return convertAnswerToBoolean(answer);
    }

    private boolean convertAnswerToBoolean(String answer) {
        return answer.equals(YES);
    }

    private String getMoreCardInput() throws IOException {
        return inputReader.readLine();
    }

    private boolean isAnswerNotValid(String answer) {
        return !YES.equals(answer) && !NO.equals(answer);
    }
}
