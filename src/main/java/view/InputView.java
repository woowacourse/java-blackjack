package view;

import domain.participant.Name;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final String YES = "y";
    private static final String NO = "n";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> userNameRequest() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(readLine().split(","))
                .collect(Collectors.toList());
    }

    public Integer betRequest(final Name name) {
        System.out.printf("%s의 배팅 금액은?\n", name.getValue());
        return toNumber(readLine());
    }

    private Integer toNumber(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.", exception);
        }
    }

    public boolean cardRequest(Name name) {
        System.out.printf(
                "%s는 한 장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)\n",
                name.getValue()
                , YES, NO
        );
        return validateInputAndGet(readLine());
    }

    private boolean validateInputAndGet(final String input) {
        if (input.equals(YES)) {
            return true;
        }
        if (input.equals(NO)) {
            return false;
        }
        throw new IllegalArgumentException(
                String.format("%s 또는 %s만 입력 가능합니다.", YES, NO)
        );
    }

    private String readLine() {
        return scanner.nextLine();
    }


}
