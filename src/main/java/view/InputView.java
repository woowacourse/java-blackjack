package view;

import domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import machine.HitStay;

public class InputView {

    private static final String DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.asList(scanner.nextLine().split(DELIMITER, -1));
    }

    public int readBetAmount(String name) {
        System.out.printf("%s의 베팅 금액은?%n", name);
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력해 주세요.");
        }
    }

    public HitStay readHitOrStay(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%n", player.getName(), YES, NO);
        String rawHitStay = scanner.nextLine();
        if (rawHitStay.equals(YES)) {
            return HitStay.HIT;
        }
        if (rawHitStay.equals(NO)) {
            return HitStay.STAY;
        }
        throw new IllegalArgumentException(String.format("[ERROR] %s 혹은 %s만 입력해 주세요.", YES, NO));
    }
}
