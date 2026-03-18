package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    @Test
    @DisplayName("플레이어 이름은 1글자 이상 8글자 이하여야 한다.")
    void 이름은_1글자_이상_8글자_이하_성공() {
        // given
        String name = "pobi";

        // when - then
        assertDoesNotThrow(() -> new Player(name, 10000));
    }

    @Test
    @DisplayName("플레이어 이름은 공백을 허용하지 않는다.")
    void 이름_공백_실패() {
        // given
        String name = "";

        // when - then
        assertThatThrownBy(() -> new Player(name, 10000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다. 다시 입력해주세요.");
    }

    @Test
    @DisplayName("플레이어 이름은 8글자 초과를 허용하지 않는다.")
    void 이름_8글자_초과_실패() {
        // given
        String name = "pobipobip";

        // when - then
        assertThatThrownBy(() -> new Player(name, 10000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다. 다시 입력해주세요.");
    }

    @Test
    @DisplayName("플레이어 이름은 특수 문자를 허용하지 않는다.")
    void 이름_특수_문자_실패() {
        // given
        String name = "&*&@$";

        // when - then
        assertThatThrownBy(() -> new Player(name, 10000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 영문자만 포함되어야 합니다.");
    }

    @Test
    @DisplayName("hit 처리 시, 1장을 뽑는다.")
    void Hand에_1장_추가() {
        // given
        Player player = new Player("pobi", 10000);

        // when
        player.hit(new Card(Rank.ACE, Suit.DIAMOND));

        // then
        assertEquals(1, player.getHand().getHand().size());
    }
}
