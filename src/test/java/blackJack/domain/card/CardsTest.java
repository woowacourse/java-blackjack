package blackJack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    private final Cards cards = new Cards();

    @Test
    @DisplayName("Cards 생성 테스트")
    void createValidCards() {
        assertThat(new Cards()).isNotNull();
    }

    @Test
    @DisplayName("중복된 카드를 받는 경우가 존재하지 않는지 테스트")
    void receiveDuplicatedCard() {
        Card card1 = Card.from(Suit.CLOVER, Denomination.ACE);
        Card card2 = Card.from(Suit.CLOVER, Denomination.ACE);
        cards.receiveCard(card1);
        cards.receiveCard(card2);

        assertThat(cards.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드에 Ace가 11로 되는 경우 합계 계산 테스트")
    void calculateScoreWithAceEleven() {
        cards.receiveCard(Card.from(Suit.CLOVER, Denomination.ACE));
        cards.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));

        assertThat(cards.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드에 Ace가 1로 되는 경우 합계 계산 테스트")
    void calculateScoreWithAceOne() {
        cards.receiveCard(Card.from(Suit.CLOVER, Denomination.ACE));
        cards.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));
        cards.receiveCard(Card.from(Suit.CLOVER, Denomination.EIGHT));

        assertThat(cards.calculateScore()).isEqualTo(19);
    }

    @Test
    @DisplayName("카드에 Ace가 여러개인 경우 계산 테스트")
    void calculateScoreWithAceCountThree() {
        cards.receiveCard(Card.from(Suit.CLOVER, Denomination.ACE));
        cards.receiveCard(Card.from(Suit.HEART, Denomination.ACE));
        cards.receiveCard(Card.from(Suit.DIAMOND, Denomination.ACE));
        cards.receiveCard(Card.from(Suit.SPADE, Denomination.EIGHT));

        assertThat(cards.calculateScore()).isEqualTo(21);
    }
}
