package blackjack.view;

import blackjack.YesOrNo;
import blackjack.domain.user.component.BettingAmount;
import blackjack.domain.user.component.Name;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<Name> getNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return Arrays.stream(input.split(DELIMITER))
                .map(Name::new)
                .collect(Collectors.toList());
    }

    public static List<BettingAmount> getBettingAmounts(List<Name> names) {
        List<BettingAmount> bettingAmounts = new ArrayList<>();
        for (Name name : names) {
            System.out.println(String.format("%s의 베팅 금액은?", name.getName()));
            bettingAmounts.add(new BettingAmount(scanner.nextLine()));
        }
        return bettingAmounts;
    }

    public static YesOrNo getYorN(Name name) {
        String msg = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
        System.out.println(String.format(msg, name));
        return YesOrNo.get(scanner.nextLine());
    }
}
