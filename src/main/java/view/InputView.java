package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    Scanner sc = new Scanner(System.in);

    public List<String> inputPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawPlayers = sc.nextLine();
        List<String> playerName = Arrays.stream(rawPlayers.split(","))
                .map(String::trim)
                .toList();
        return playerName;
    }

    public void isBlank(List<String> names) {
        for (String name : names) {
            if (name.isBlank()) {
                throw new IllegalArgumentException();
            }
        }
    }
}
