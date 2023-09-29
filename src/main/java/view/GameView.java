package view;

import model.name.Name;

import java.io.BufferedReader;
import java.io.IOException;

public class GameView {

    private static final String WIN = "승 ";
    private static final String SAME = "무 ";
    private static final String LOSE = "패 ";
    private static final String YES = "y";
    private static final String NO = "n";

    private final BufferedReader inputReader;

    public GameView(BufferedReader inputReader) {
        this.inputReader = inputReader;
    }

    public void askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
    }

    public String getInput() throws IOException {
        return inputReader.readLine();
    }

    public void giveInitCardAlert(String dealer, String names, int cards) {
        System.out.println(dealer + "와 " + names + "에게 " + cards + "장을 나누었습니다.");
        System.out.println();
    }

    public void printPlayerDefaultStatus(final String name, final String cardNames) {
        if (name.equals(Name.getDealer())) {
            System.out.println(dealerDefaultAnswer(name, cardNames));
            return;
        }
        System.out.println(playerDefaultAnswer(name, cardNames));
    }

    private String playerDefaultAnswer(final String name, final String cardNames) {
        return name + "카드" + addDividerBeforeCardNames(cardNames);
    }

    private String dealerDefaultAnswer(final String name, final String cardNames) {
        return name + addDividerBeforeCardNames(cardNames);
    }

    public void printPlayerResultStatus(final String name, final String cardNames, final int score) {
        System.out.println(name + "카드" + addDividerBeforeCardNames(cardNames) + addResultDividerBeforeScore(score));
    }
    
    private static String addDividerBeforeCardNames(final String cardNames) {
        return ": " + cardNames;   
    }

    private static String addResultDividerBeforeScore(final int score) {
        return " - 결과: " + score;
    }

    public boolean askWantMoreCard(String name) throws IOException {
        askWantMoreCardAlert(name);

        String answer = getInput();
        while (isAnswerNotValid(answer)) {
            askWantMoreCardAlert(name);
            answer = getInput();
        }

        return convertAnswerToBoolean(answer);
    }

    private void askWantMoreCardAlert(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 " + YES + ", 아니오는 " + NO + ")");
    }

    public void giveDealerCardAlert(final String dealer, final int score) {
        System.out.println();
        System.out.println(dealer + "는 " + score + "이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public void dealerEnoughAlert(final String dealer, final int score) {
        System.out.println();
        System.out.println(dealer + "의 점수는 " + score + "점 초과로 충분하기에 더 받지 않습니다.");
        System.out.println();
    }

    public void alertFinalGrade() {
        System.out.println();
        System.out.println("## 최종 승패");
    }

    public void printWin(final String name) {
        System.out.println(name + ": " + WIN);
    }

    public void printSame(final String name) {
        System.out.println(name + ": " + SAME);
    }

    public void printLose(final String name) {
        System.out.println(name + ": " + LOSE);
    }

    public void printDealerScoreBoard(final String name, final int win, final int same, final int lose) {
        System.out.println(name + ": " + win + WIN + same + SAME + lose + LOSE);
    }

    private boolean convertAnswerToBoolean(String answer) {
        return answer.equals(YES);
    }

    private boolean isAnswerNotValid(String answer) {
        return !YES.equals(answer) && !NO.equals(answer);
    }
}
