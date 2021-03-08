package blackjack.view;

import blackjack.domain.player.dto.PlayerDTO;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getUsersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String value = SCANNER.nextLine();
        System.out.println();
        return value;
    }

    public static String getYesOrNo(PlayerDTO userDTO) {
        System.out.println(userDTO.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return SCANNER.nextLine();
    }

    public static String getUserBetAmount(PlayerDTO playerDTO) {
        System.out.println(playerDTO.getName() + "의 배팅 금액은?");
        String value = SCANNER.nextLine();
        System.out.println();
        return value;
    }
}