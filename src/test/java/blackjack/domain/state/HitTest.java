package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.player.BettingMoney;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void 힛_상태에서_수익계산_테스트() {
        State hit = StateFactory.drawFirstCards(Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.TWO, Shape.CLUBS)
        ));

        assertThatThrownBy(() -> {
            hit.calculateProfit(new BettingMoney(10000));
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임이 끝나지 않아 수익을 계산할 수 없습니다.");
    }
}