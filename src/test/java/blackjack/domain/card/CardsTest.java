package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    private Cards cards;

    @Test
    @DisplayName("Cards 생성 테스트")
    void createValidCards() {
        assertThat(new Cards()).isNotNull();
    }

    @Test
    @DisplayName("중복된 카드를 받는 경우가 존재하지 않는지 테스트")
    void receiveDuplicatedCard() {
        cards = new Cards();
        Card card1 = Card.from(Suit.CLOVER, Denomination.ACE);
        Card card2 = Card.from(Suit.CLOVER, Denomination.ACE);
        cards.receiveCard(card1);
        cards.receiveCard(card2);

        assertThat(cards.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드에 Ace가 11로 되는 경우 합계 계산 테스트")
    void calculateScoreWithAceEleven() {
        Card card1 = Card.from(Suit.CLOVER, Denomination.ACE);
        Card card2 = Card.from(Suit.CLOVER, Denomination.JACK);
        cards = new Cards(Set.of(card1, card2));

        assertThat(cards.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드에 Ace가 1로 되는 경우 합계 계산 테스트")
    void calculateScoreWithAceOne() {
        Card card1 = Card.from(Suit.CLOVER, Denomination.ACE);
        Card card2 = Card.from(Suit.CLOVER, Denomination.JACK);
        Card card3 = Card.from(Suit.CLOVER, Denomination.EIGHT);
        cards = new Cards(Set.of(card1, card2, card3));

        assertThat(cards.calculateScore()).isEqualTo(19);
    }

    @Test
    @DisplayName("카드에 Ace가 여러개인 경우 계산 테스트")
    void calculateScoreWithAceCountThree() {
        Card card1 = Card.from(Suit.CLOVER, Denomination.ACE);
        Card card2 = Card.from(Suit.HEART, Denomination.ACE);
        Card card3 = Card.from(Suit.DIAMOND, Denomination.ACE);
        Card card4 = Card.from(Suit.SPADE, Denomination.EIGHT);
        cards = new Cards(Set.of(card1, card2, card3, card4));


        assertThat(cards.calculateScore()).isEqualTo(21);
    }
}
