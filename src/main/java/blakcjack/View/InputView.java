package blakcjack.view;

import blakcjack.view.reply.AdditionalCardReply;
import blakcjack.view.reply.InvalidReplyException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> takePlayerNamesInput() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(SCANNER.nextLine().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static boolean takeHitOrStand(final String name) {
        try {
            final AdditionalCardReply additionalCardReply = inputAdditionalCardReply(name);
            return additionalCardReply.isHit();
        } catch (InvalidReplyException e) {
            System.out.println(e.getMessage());
            return takeHitOrStand(name);
        }
    }

    private static AdditionalCardReply inputAdditionalCardReply(final String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%n", name,
                AdditionalCardReply.HIT.getValue(),
                AdditionalCardReply.STAND.getValue());

        String input = SCANNER.nextLine().trim();
        return AdditionalCardReply.from(input);
    }
}
