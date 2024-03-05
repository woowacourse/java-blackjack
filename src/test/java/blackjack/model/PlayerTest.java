package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PlayerTest {

    @Test
    @DisplayName("참여할 사람은 이름을 가진다.")
    void createPlayer() {
        String name = "리브";
        Cards cards = new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_THREE));
        Player player = new Player(name, cards);
        assertThat(player.getName()).isEqualTo(name);
    }

    @ParameterizedTest(name = "이름 입력 시 blank가 들어온 경우 예외를 발생한다.")
    @NullAndEmptySource
    void createPlayerByBlank(String name) {
        Cards cards = new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_THREE));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Player(name, cards))
                .withMessage("이름에는 공백을 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("참여할 사람은 카드를 받는다.")
    void createPlayerWithCards() {
        Cards cards = new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_THREE));
        Player player = new Player("몰리", cards);
        player.receiveCard(Deck.CLOVER_ACE);

        assertThat(player.openCards()).isEqualTo(List.of(Deck.CLOVER_ACE, Deck.CLOVER_THREE, Deck.CLOVER_ACE));
    }
}
