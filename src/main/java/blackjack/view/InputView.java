package blackjack.view;

import blackjack.dto.PlayerDto;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String QUESTION_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String DRAW_CARD_FORMAT = "%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private static final String ERROR_NO_SUCH_SIGN = "[ERROR] y또는 n으로만 입력하세요.";

    private static final String NAME_DELIMITER = ",";

    public List<String> inputEntryNames() {
        System.out.println(QUESTION_NAME);
        String names = new Scanner(System.in).nextLine();
        return Arrays.asList(names.split(NAME_DELIMITER));
    }

    public String inputDrawCardSign(PlayerDto playerDto) {
        System.out.printf(DRAW_CARD_FORMAT, playerDto.getName());
        String sign = new Scanner(System.in).nextLine();
        return sign.trim();
    }
}
