package blackjack.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.card.CardHand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerNameTest {

    @Nested
    @DisplayName("이름 생성 테스트")
    class CreatePlayerNameTest {

        @ParameterizedTest
        @ValueSource(strings = {"iiif", "pppk", "이프", "sa나"})
        @DisplayName("이름은 영어/한글로 구성될 수 있다.")
        void createPlayerByName(String name) {
            PlayerName playerName = new PlayerName(name);

            assertThat(playerName).isInstanceOf(PlayerName.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"포비_", "sa나!", "훌라627", "HULA,"})
        @DisplayName("이름은 영어/한글 이외의 문자로 구성될 수 없다.")
        void createPlayerByEmptyName(String name) {
            assertThatThrownBy(() -> new Player(new PlayerName(name), new CardHand(21)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 영어/한글만 입력 가능합니다.");
        }
    }
}
