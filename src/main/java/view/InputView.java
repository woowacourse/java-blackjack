package view;

import view.dto.participant.ParticipantDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView implements AutoCloseable {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public List<ParticipantDto> askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = requireNotBlank(readLine());
        return Arrays.stream(input.split(","))
                                             .map(name -> new ParticipantDto(name.trim()))
                                             .toList();
    }

    private String requireNotBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력이 공백으로만 이루어질 수 없습니다.");
        }
        return input;
    }

    public BlackJackGameCommand askMoreCard(final ParticipantDto player) {
        System.out.printf("%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", player.name());
        return requireYesOrNo(readLine());
    }

    private BlackJackGameCommand requireYesOrNo(final String input) {
        return BlackJackGameCommand.from(input);
    }

    private String readLine() {
        try {
            return READER.readLine();
        } catch (final IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void close() throws Exception {
        READER.close();
    }
}
