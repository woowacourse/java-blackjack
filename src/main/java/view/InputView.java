package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static controller.DrawCardCommand.CARD_DRAW_AGAIN;
import static controller.DrawCardCommand.CARD_DRAW_STOP;

public final class InputView {

    private static final String PLAYER_NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String BETTING_MONEY_INPUT_MESSAGE_FORMAT = "%s의 배팅 금액은?";

    private static final String DRAW_CARD_CARD_MESSAGE_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)";

    private final BufferedReader bufferedReader;

    public InputView(final BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public <T> T getInputWithRetry(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException e) {
            OutputView.print(e.getMessage());
            return getInputWithRetry(inputReader);
        }
    }

    public List<String> getPlayerNames() {
        OutputView.print(PLAYER_NAME_INPUT_MESSAGE);
        List<String> playerNames = Arrays.asList(Objects.requireNonNull(readConsole()).split(","));
        return trimParticipantNames(playerNames);
    }

    public String getBettingMoney(final String playerName) {
        OutputView.print(System.lineSeparator() + String.format(BETTING_MONEY_INPUT_MESSAGE_FORMAT, playerName));
        return readConsole();
    }

    public String getDrawCardCommand(final String name) {
        final String drawCardMessage = System.lineSeparator() + String.format(DRAW_CARD_CARD_MESSAGE_FORMAT,
                name, CARD_DRAW_AGAIN.getCommand(), CARD_DRAW_STOP.getCommand());
        OutputView.print(drawCardMessage);
        return readConsole();
    }

    private String readConsole() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            OutputView.print(e.getMessage());
        }
        return null;
    }

    private List<String> trimParticipantNames(final List<String> playerNames) {
        return playerNames.stream().map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }
}
