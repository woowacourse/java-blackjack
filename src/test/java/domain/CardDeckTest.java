package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class CardDeckTest {
    @Test
    void 카드_덱의_총_카드_수는_52장이다() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck).extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(52);
    }

    // todo 셔플 전략 추가 후 원하는 카드를 draw하도록 변경
    @Test
    void 카드를_한_장_드로우한다() {
        CardDeck cardDeck = new CardDeck();

        Card card = cardDeck.draw();

        assertThat(card).isEqualTo(new Card(Denomination.KING, Emblem.DIAMOND));
    }
}
