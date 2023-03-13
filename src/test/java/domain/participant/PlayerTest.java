package domain.participant;

import domain.deck.ShuffledDeck;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.game.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.collection;

class PlayerTest {

    private List<Card> initialData;
    private Player player;

    @BeforeEach
    void init() {
        initialData = List.of(
                Card.of(CardShape.HEART, CardNumber.of(1)),
                Card.of(CardShape.HEART, CardNumber.of(2))
        );
        player = Player.create(Name.of("HK"), Hand.create(initialData), 10_000);
    }

    @DisplayName("플레이어는 '딜러'라는 이름을 가지면 예외가 발생한다.")
    @Test
    void invalidNameTest() {
        assertThatThrownBy(() -> Player.of(Name.of("딜러"), 10_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'딜러'라는 이름을 가질 수 없습니다.");
    }

    @DisplayName("플레이어는 한 장의 카드를 받을 수 있다.")
    @Test
    void playerTakeCardsTest() {
        player.takeCard(Card.of(CardShape.HEART, CardNumber.of(3)));

        assertThat(player)
                .extracting("cards")
                .extracting("cards", collection(List.class))
                .size()
                .isSameAs(3);
    }

    @DisplayName("플레이어는 다수의 카드를 받을 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    void playerTakeCardTest(int value) {
        player.takeInitialCards(new ShuffledDeck(), value);

        assertThat(player)
                .extracting("cards")
                .extracting("cards", collection(List.class))
                .size()
                .isSameAs(value + initialData.size());
    }

    @DisplayName("플레이어는 자신이 가진 카드의 점수 합을 구할 수 있다.")
    @Test
    void calculateGamePoint() {
        for (int i = 0; i < 12; i++) {
            player.takeCard(Card.of(CardShape.HEART, CardNumber.of(1)));
        }
        assertThat(player.calculatePoint())
                .extracting("gamePoint")
                .isSameAs(15);
    }
}
