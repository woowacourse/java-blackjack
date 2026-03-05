package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    @DisplayName("유저_생성_테스트")
    void 유저_생성_테스트() {
        // given
        String name = "흑곰";

        // when & then
        assertThatCode(() -> new User(name))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유저이름이_5자가_넘을경우_예외_발생")
    void 유저이름이_5자가_넘을경우_예외_발생() {
        // given
        String name = "흑곰흑곰흑곰";

        // when & then
        assertThatThrownBy(() -> new User(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자");
    }

    @Test
    @DisplayName("유저가 카드 한 장을 가져오는 테스트")
    void 유저가_카드_한_장을_가져오는_테스트() {
        // given
        User user = new User("밀란");
        Card card = new Card(CardValue.A, Shape.DIAMOND);

        // when
        int before = user.getCards().size();
        user.bring(card);

        // then
        assertThat(user.getCards().size()).isEqualTo(before + 1);
    }

}
