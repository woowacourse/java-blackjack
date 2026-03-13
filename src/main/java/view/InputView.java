package view;

import static view.reader.Console.readLine;

import java.util.Arrays;
import java.util.List;
import model.paticipant.Player;

public class InputView {

    private InputView() {
    }

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(readLine().split(","))
                .toList();
    }

    public static int readBetAmount(String playerName) {
        System.out.println();
        System.out.printf("%s의 배팅 금액은?%n", playerName);
        return Integer.parseInt(readLine());
    }

    public static String readMoreCard(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return readLine();
    }
}
