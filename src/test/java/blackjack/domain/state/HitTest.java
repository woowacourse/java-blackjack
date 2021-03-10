package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HitTest {

    @Test
    void 힛_상태_테스트() {
        State hit = StateFactory.drawFirstCards(Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.TWO, Shape.CLUBS)
        ));

        assertThat(hit).isInstanceOf(Hit.class);
    }

    @Test
    void 힛_상태에서_드로우하고_다시_힛_테스트() {
        State hit = StateFactory.drawFirstCards(Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.TWO, Shape.CLUBS)
        ));

        assertThat(hit.draw(Card.of(Denomination.FOUR, Shape.CLUBS))).isInstanceOf(Hit.class);
    }

}