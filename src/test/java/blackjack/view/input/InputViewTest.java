package blackjack.view.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import blackjack.view.input.reader.CustomReader;

public class InputViewTest {

    private final CustomReader customReader = new CustomReader();
    private final InputView inputView = new InputView(customReader);

    @DisplayName("플레이어 이름은 쉼표로 구분되어야 한다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @MethodSource("blackjack.view.input.provider.InputViewTestProvider#provideForRequestPlayerNamesTest")
    void requestPlayerNamesTest(final String inputLine, final List<String> expectedPlayerNames) {
        customReader.initTest(inputLine);
        final List<String> actualPlayerNames = inputView.requestPlayerNames();
        assertThat(actualPlayerNames).isEqualTo(expectedPlayerNames);
    }

    @DisplayName("플레이어의 베팅 금액을 입력받을 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"-10", "0", "100"})
    void requestBetAmountTest(final String inputLine) {
        customReader.initTest(inputLine);
        final int actualBetAmount = inputView.requestBettingAmount();
        final int expectedBetAmount = Integer.parseInt(inputLine);
        assertThat(actualBetAmount).isEqualTo(expectedBetAmount);
    }

    @DisplayName("플레이어의 베팅 금액은 숫자여야 한다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"", "a", "1a"})
    void betAmountNotNumericTest(final String inputLine) {
        customReader.initTest(inputLine);
        assertThatThrownBy(inputView::requestBettingAmount)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("베팅 금액은 숫자여야 합니다.");
    }

    @DisplayName("y 또는 n을 입력받을 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @MethodSource("blackjack.view.input.provider.InputViewTestProvider#provideForRequestDrawingCardChoiceTest")
    void requestDrawingCardChoiceTest(final String inputLine, final boolean expectedContinuable) {
        customReader.initTest(inputLine);
        final boolean actualContinuable = inputView.requestDrawingCardChoice();
        assertThat(actualContinuable).isEqualTo(expectedContinuable);
    }

    @DisplayName("y 또는 n만 입력할 수 있습니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"", " ", "yyy", "nnn", "123"})
    void drawingCardChoiceWrongInputExceptionTest(final String inputLine) {
        customReader.initTest(inputLine);
        assertThatThrownBy(inputView::requestDrawingCardChoice)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력은 y 또는 n 이어야 합니다.");
    }

}
