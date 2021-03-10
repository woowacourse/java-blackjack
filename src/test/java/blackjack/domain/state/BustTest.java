package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BustTest {

    @Test
    void 버스트_상태_테스트() {
        State hit = StateFactory.drawFirstCards(Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.TWO, Shape.CLUBS)
        ));

        State bust = hit.draw(Card.of(Denomination.JACK, Shape.CLUBS));

        assertThat(bust).isInstanceOf(Bust.class);
    }

    @Test
    void 버스트_상태에서_드로우_테스트() {
        State hit = StateFactory.drawFirstCards(Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.TWO, Shape.CLUBS)
        ));

        State bust = hit.draw(Card.of(Denomination.JACK, Shape.CLUBS));

        assertThatThrownBy(() -> {
            bust.draw(Card.of(Denomination.FIVE, Shape.CLUBS));
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 이미 끝이나서 카드를 뽑을 수 없습니다.");
    }

}