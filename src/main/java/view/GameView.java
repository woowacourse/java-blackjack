package view;

import java.io.BufferedReader;
import java.io.IOException;

public class GameView {

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
}
