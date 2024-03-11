package blackjack.view.format;

import blackjack.domain.result.WinStatus;
import java.util.Arrays;

public enum WinningStatusFormat {
    LOSE(WinStatus.LOSE.name(), "패"),
    DRAW(WinStatus.DRAW.name(), "무"),
    WIN(WinStatus.WIN.name(), "승");

    private final String signal;
    private final String format;

    WinningStatusFormat(final String signal, final String format) {
        this.signal = signal;
        this.format = format;
    }

    public static WinningStatusFormat of(final String inputSignal) {
        return Arrays.stream(values())
                .filter(value -> value.signal.equals(inputSignal))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 결과 형식입니다."));
    }

    public String getFormat() {
        return format;
    }
}
