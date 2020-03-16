package blackjack.view;

import blackjack.domain.gamer.Player;
import blackjack.dto.NamesDto;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ASK_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private static final String ASK_HIT_OR_STAND_MESSAGE = "%s는 한 장의 카드를 더 받겠습니까?(에는 y, 아니오는 n)";

    public static NamesDto askPlayerNames() {
        System.out.println(ASK_PLAYER_NAMES_MESSAGE);
        return new NamesDto(SCANNER.nextLine());
    }

    public static String askHitOrStand(Player player) {
        System.out.println(String.format(ASK_HIT_OR_STAND_MESSAGE, player.getName()));
        return SCANNER.nextLine();
    }
}
