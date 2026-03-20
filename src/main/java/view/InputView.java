package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner sc = new Scanner(System.in);

    public List<String> inputPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawPlayers = sc.nextLine();
        return Arrays.stream(rawPlayers.split(","))
                .map(String::trim)
                .toList();
    }

    public int inputBettingAmount(String playerName) {
        System.out.println(playerName + "의 배팅 금액은?");
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("배팅 금액은 숫자여야 합니다.");
        }
    }

    public boolean askHit(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return sc.nextLine().equals("y");
    }


}
