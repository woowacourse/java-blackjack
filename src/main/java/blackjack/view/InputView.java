package blackjack.view;

import blackjack.domain.player.Information;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCAN = new Scanner(System.in);
    private static final String ASK_DRAWABLE_FORM =
        "%s는 한장의 카드를 더 받겠습니까? (예는 y, 아니요는 n)" + System.lineSeparator();
    private static final String BAT_MONEY_FORM = "%s의 배팅 금액은?" + System.lineSeparator();
    private static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private static final String YES_OR_NO_ERROR_MESSAGE = "y 또는 n 중에 입력해주세요.";
    private static final String NUMBER_ERROR_MESSAGE = "숫자만 입력해주세요.";
    private static final String NAME_REGEX = ",";
    private static final String YES = "y";
    private static final String NO = "n";

    public static boolean drawable(String name) {
        System.out.printf(ASK_DRAWABLE_FORM, name);
        String yesOrNo = SCAN.nextLine();
        if (yesOrNo.equals(YES)) {
            return true;
        }
        if (yesOrNo.equals(NO)) {
            return false;
        }
        System.out.println(YES_OR_NO_ERROR_MESSAGE);
        return drawable(name);
    }

    public static List<Information> informations() {
        String[] names = names();
        return information(names);
    }

    private static String[] names() {
        System.out.println(INPUT_NAME_MESSAGE);
        return SCAN.nextLine().split(NAME_REGEX);
    }

    private static List<Information> information(String... names) {
        try {
            return Arrays.stream(names)
                .map(name -> {
                    System.out.printf(BAT_MONEY_FORM, name);
                    int batMoney = Integer.parseInt(SCAN.nextLine());
                    return new Information(name, batMoney);
                }).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println(NUMBER_ERROR_MESSAGE);
            return information(names);
        }
    }
}
