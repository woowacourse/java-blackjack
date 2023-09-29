package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

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

    public void printInitPlayerStatus(final String name, final String cardNames) {
        System.out.println(name + "카드: " + cardNames);
    }

    public void eachPrint(List<String> input) {
        for (String line : input) {
            System.out.println(line);
        }
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

    public void giveDealerCardAlert(final String dealer, final int score) {
        System.out.println();
        System.out.println(dealer + "는 " + score + "이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public void dealerEnoughAlert(final String dealer, final int score) {
        System.out.println();
        System.out.println(dealer + "의 점수는 " + score + "점 이상으로 충분하기에 더 받지 않습니다.");
        System.out.println();
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
