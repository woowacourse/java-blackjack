package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackjackTest {

    @Test
    void 블랙잭_상태_테스트() {
        State blackjack = StateFactory.drawFirstCards(Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.ACE, Shape.CLUBS)
        ));

        assertThat(blackjack).isInstanceOf(Blackjack.class);
    }

    @Test
    void 블랙잭_상태에서_드로우_테스트() {
        State blackjack = StateFactory.drawFirstCards(Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.ACE, Shape.CLUBS)
        ));

        assertThatThrownBy(() -> {
            blackjack.draw(Card.of(Denomination.FIVE, Shape.CLUBS));
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 이미 끝이나서 카드를 뽑을 수 없습니다.");
    }
}