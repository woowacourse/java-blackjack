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
        Player player = new Player(name);
        assertThat(player.getName()).isEqualTo(name);
    }

    @ParameterizedTest(name = "이름 입력 시 blank가 들어온 경우 예외를 발생한다.")
    @NullAndEmptySource
    void createPlayerByBlank(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Player(name))
                .withMessage("이름에는 공백을 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("참여할 사람은 카드를 받는다.")
    void createPlayerWithCards() {
        Player player = new Player("몰리");
        player.receiveCard(Deck.CLOVER_ACE);
        assertThat(player.getCards()).isEqualTo(List.of(Deck.CLOVER_ACE));
    }

    @Test
    @DisplayName("자신이 가진 카드의 합을 계산할 수 있다.")
    void calculateScore() {
        Player player = new Player("몰리");
        player.receiveCard(Deck.CLOVER_FIVE);
        player.receiveCard(Deck.CLOVER_FOUR);
        assertThat(player.calculateScore()).isEqualTo(9);
    }

    @Test
    @DisplayName("Ace를 여러 개 가진 경우 동일한 값인 1로 변경된다.")
    void calculateScoreWithAces() {
        Player player = new Player("몰리");
        player.receiveCard(Deck.SPADE_ACE);
        player.receiveCard(Deck.DIA_ACE);

        assertThat(player.calculateScore()).isEqualTo(2);
    }

    @Test
    @DisplayName("Ace의 기본값으로 결과를 계산했을 때 21을 초과한 경우 Ace 값은 1로 변경된다.")
    void calculateScoreWithAce() {
        Player player = new Player("몰리");
        player.receiveCard(Deck.CLOVER_JACK);
        player.receiveCard(Deck.CLOVER_TWO);
        player.receiveCard(Deck.SPADE_ACE);

        assertThat(player.calculateScore()).isEqualTo(13);
    }
}
