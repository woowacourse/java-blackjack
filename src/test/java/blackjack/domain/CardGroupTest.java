package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardGroupTest {

    private final Card spadeAceCard = new Card(CardShape.SPADE, CardNumber.ACE);
    private final Card spadeEightCard = new Card(CardShape.SPADE, CardNumber.EIGHT);

    private CardGroup cards;

    @BeforeEach
    void setup() {
        cards = new CardGroup(spadeAceCard, spadeEightCard);
    }

    @Test
    @DisplayName("카드 숫자의 총합을 반환하는 기능 추가")
    void getTotalValueTest() {

        int value = cards.getTotalValue();

        Assertions.assertThat(value).isEqualTo(spadeAceCard.getNumber().getValue() + spadeEightCard.getNumber().getValue());
    }

    @Test
    @DisplayName("카드 그룹에 카드 하나를 추가하는 기능 테스트")
    void addCardTest() {
        final Card heartTwoCard = new Card(CardShape.HEART, CardNumber.TWO);

        cards.add(heartTwoCard);

        Assertions.assertThat(cards).extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .contains(spadeAceCard, spadeEightCard, heartTwoCard);
    }

    @Test
    @DisplayName("Ace의 개수를 반환하는 기능 테스트")
    void getAceCountTest() {
        final Card heartAceCard = new Card(CardShape.HEART, CardNumber.ACE);

        cards.add(heartAceCard);
        int aceCount = cards.getAceCount();

        Assertions.assertThat(aceCount).isEqualTo(2);
    }
}
