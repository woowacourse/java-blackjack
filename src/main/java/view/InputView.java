package view;

import view.dto.participant.NameDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static view.ResultView.DELIMITER;

public class InputView {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^-?\\d+$");

    public List<NameDto> askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = requireNotBlank(readLine());
        return Arrays.stream(input.split(DELIMITER))
                     .map(name -> new NameDto(name.trim()))
                     .toList();
    }

    private String requireNotBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력이 공백으로만 이루어질 수 없습니다.");
        }
        return input;
    }

    public int askBettingMoney(final NameDto nameDto) {
        System.out.printf(System.lineSeparator() + "%s의 배팅 금액은?" + System.lineSeparator(), nameDto.name());
        String input = requireNotBlank(readLine());
        validateNumeric(input);
        return Integer.parseInt(input);
    }

    private void validateNumeric(final String input) {
        if (!NUMERIC_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("숫자로만 구성되게 입력해야 합니다.");
        }
    }

    public BlackJackGameCommand askMoreCard(final NameDto nameDto) {
        System.out.printf(System.lineSeparator() + "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + System.lineSeparator()
                , nameDto.name());
        return requireYesOrNo(readLine());
    }

    private BlackJackGameCommand requireYesOrNo(final String input) {
        return BlackJackGameCommand.from(input);
    }

    private String readLine() {
        try {
            return READER.readLine();
        } catch (final IOException exception) {
            System.out.println(exception.getMessage());
            return readLine();
        }
    }
}
