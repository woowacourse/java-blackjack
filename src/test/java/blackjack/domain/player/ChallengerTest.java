package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.domain.player.exception.InvalidPlayerNameException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChallengerTest {

    @Test
    @DisplayName("플레이어는 초기 카드 2장을 받는다")
    void get_two_cards() {
        Player player = new Challenger("oing");
        Card card1 = new Card(Shape.HEART, Number.FOUR);
        Card card2 = new Card(Shape.CLOVER, Number.KING);
        player.pickStartCards(List.of(card1, card2));

        assertThat(player.getHoldingCards().getCards())
                .containsExactly(card1, card2);
    }

    @Test
    @DisplayName("추가 카드를 뽑는다.")
    void pick_card() {
        Player player = new Challenger("ditoo");
        Card card = new Card(Shape.DIAMOND, Number.JACK);
        player.pick(card);

        assertThat(player.getHoldingCards().getCards())
                .contains(card);
    }

    @Test
    @DisplayName("이름이 '딜러'인 경우 예외가 발생한다")
    void validate_name() {
        assertThrows(InvalidPlayerNameException.class,
                () -> new Challenger("딜러"));
    }
}
