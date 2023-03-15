package ui;

import domain.BettingAmount;
import domain.Name;
import domain.user.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String HIT = "y";
    private static final String STAND = "n";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static boolean readWhetherDrawCardOrNot(Player player) {
        String input = null;
        do {
            System.out.println(player.getNameValue() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            input = SCANNER.nextLine();
        } while (!validateIntentionInput(input));
        return HIT.equals(input);
    }

    private static boolean validateIntentionInput(String input) {
        if (HIT.equals(input) || STAND.equals(input)) {
            return true;
        }
        System.out.println("y또는 n으로 입력해주세요");
        return false;
    }

    private static List<Name> readPlayerNames() {
        try {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            List<String> names = Arrays.stream(SCANNER.nextLine().split(",")).collect(Collectors.toList());
            return names.stream().map(Name::new).collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readPlayerNames();
        }
    }

    public static List<Player> readPlayers() {
        List<Name> names = readPlayerNames();
        List<Player> players = new ArrayList<>();
        for (Name name : names) {
            String nameValue = name.getValue();
            players.add(new Player(nameValue, readBettingAmount(nameValue)));
        }
        return players;
    }

    private static int readBettingAmount(String name) {
        try {
            System.out.println("\n" + name + "의 베팅 금액은?");
            return new BettingAmount(Integer.parseInt(SCANNER.nextLine())).getBettingAmount();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readBettingAmount(name);
        }
    }
}
