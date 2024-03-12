package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import domain.constants.CardCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardCommandTest {
    @DisplayName("문자열로 입력받은 명령어를 enum으로 변환한다.")
    @Test
    void convertStringCommandToEnum() {
        String command = "y";
        CardCommand cardCommand = CardCommand.from(command);

        assertThat(cardCommand).isEqualTo(CardCommand.HIT);
    }
}
