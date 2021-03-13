package blakcjack.view;

import blakcjack.dto.PlayerCreationDto;
import blakcjack.view.reply.AdditionalCardReply;
import blakcjack.view.reply.InvalidReplyException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static PlayerCreationDto takePlayerCreationInformation() {
        final List<String> names = inputPlayerNames();
        final List<Integer> bettingMoneys = names.stream()
                .map(InputView::inputBettingMoney)
                .collect(Collectors.toList());

        return PlayerCreationDto.of(names, bettingMoneys);
    }

    private static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(SCANNER.nextLine().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static int inputBettingMoney(final String name) {
        System.out.printf("%n%s의 베팅 금액은?%n", name);
        return inputValidValue();
    }

    private static int inputValidValue() {
        try {
            String input = SCANNER.nextLine().trim();
            validateInteger(input);
            validatePositiveValue(input);
            return Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " 재 입력해 주세요.");
            return inputValidValue();
        }
    }

    private static void validateInteger(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("정수가 아닙니다.");
        }
    }

    private static void validatePositiveValue(final String input) {
        if (Integer.parseInt(input) <= 0) {
            throw new IllegalArgumentException("베팅 금액으로는 양수만 입력될 수 있습니다.");
        }
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
