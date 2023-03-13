package domain.participant;

import domain.deck.ShuffledDeck;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.collection;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void init() {
        dealer = Dealer.create();
    }

    @DisplayName("딜러의 이름은 '딜러'다.")
    @Test
    void dealerName() {
        assertThat(dealer.getName().getValue()).isEqualTo("딜러");
    }

    @DisplayName("딜러는 다수의 카드를 받을 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    void dealerTakeCardTest(int value) {
        dealer.takeInitialCards(new ShuffledDeck(), value);

        assertThat(dealer)
                .extracting("cards")
                .extracting("cards", collection(List.class))
                .size()
                .isSameAs(value);
    }

    @DisplayName("딜러는 한 장의 카드를 받을 수 있다.")
    @Test
    void playerTakeCardsTest() {
        dealer.takeCard(Card.of(CardShape.HEART, CardNumber.of(3)));

        assertThat(dealer)
                .extracting("cards")
                .extracting("cards", collection(List.class))
                .size()
                .isSameAs(1);
    }

    @DisplayName("딜러는 자신이 가진 카드의 점수 합을 구할 수 있다.")
    @Test
    void calculateGamePoint() {
        for (int i = 0; i < 12; i++) {
            dealer.takeCard(Card.of(CardShape.HEART, CardNumber.of(1)));
        }
        assertThat(dealer.calculatePoint())
                .extracting("gamePoint")
                .isSameAs(12);
    }

}
