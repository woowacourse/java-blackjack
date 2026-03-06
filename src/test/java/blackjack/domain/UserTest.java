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

    @Test
    @DisplayName("가지고있는 카드의 합을 계산하는 기능 테스트")
    void 가지고있는_카드의_합을_계산하는_기능_테스트() {
        // given
        User user = new User("밀란");
        user.bring(new Card(CardValue.A, Shape.DIAMOND));
        user.bring(new Card(CardValue.FIVE, Shape.DIAMOND));

        // when
        int sum = user.calculateCardsValue();

        // then
        assertThat(6).isEqualTo(sum);
    }

    @Test
    @DisplayName("가지고 있는 카드의 핪이 버스트인지 확인하는 테스트")
    void 가지고_있는_카드의_핪이_버스트인지_확인하는_테스트() {
        // given
        User user = new User("밀란");
        user.bring(new Card(CardValue.TEN, Shape.DIAMOND));
        user.bring(new Card(CardValue.TEN, Shape.DIAMOND));
        user.bring(new Card(CardValue.TEN, Shape.DIAMOND));

        // when
        boolean isBurst = user.isBurst();

        // then
        assertThat(isBurst).isTrue();
    }

}
