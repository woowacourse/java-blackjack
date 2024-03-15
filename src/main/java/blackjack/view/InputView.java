package blackjack.view;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.participant.ParticipantName;
import blackjack.view.format.CardRequestFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = SCANNER.nextLine();

        validateDelimiter(input);
        final List<String> names = Arrays.asList(input.split(DELIMITER));
        return names;
    }

    private void validateDelimiter(final String input) {
        if (input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException(DELIMITER + " 로 끝날 수 없습니다.");
        }
    }

    public boolean readNeedMoreCard(final String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%n", name,
                CardRequestFormat.YES.getFormat(),
                CardRequestFormat.NO.getFormat());

        String input = SCANNER.nextLine();

        return CardRequestFormat.from(input);
    }

    // TODO: List<string>으로 보내는 방법은?
    public Map<String, Integer> readBettings(final List<ParticipantName> names) {
        return names.stream()
                .collect(toMap(ParticipantName::getName,
                        this::readBetting,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    private int readBetting(final ParticipantName name) {
        System.out.printf("%s의 배팅 금액은?", name.getName());
        String input = SCANNER.nextLine();
        validateBetting(input);

        return Integer.parseInt(input);
    }

    private void validateBetting(final String input) {
        validateEmpty(input);
        validateNumeric(input);
    }

    private void validateEmpty(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("금액에 공백을 입력할 수 없습니다.");
        }
    }

    private void validateNumeric(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정상적인 숫자를 입력해주세요");
        }
    }
}
