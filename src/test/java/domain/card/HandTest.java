package domain.card;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    void 플레이어의_카드에_에이스가_있는지_확인한다() {
        Card cloverAce = Card.of(CardDenomination.ACE, CardEmblem.CLOVER);
        Card spade2 = Card.of(CardDenomination.TWO, CardEmblem.SPADE);
        Card heart3 = Card.of(CardDenomination.THREE, CardEmblem.HEART);
        List<Card> cards = List.of(cloverAce, spade2, heart3);
        Hand hand = Hand.from(cards);

        Assertions.assertThat(hand.hasAce()).isTrue();
    }

    @Test
    void 기본점수합을_계산한다() {
        Card cloverAce = Card.of(CardDenomination.ACE, CardEmblem.CLOVER);
        Card spadeJack = Card.of(CardDenomination.JACK, CardEmblem.SPADE);
        List<Card> cards = List.of(cloverAce, spadeJack);
        Hand hand = Hand.from(cards);

        int basicScore = hand.getBasicScore(); // 11

        Assertions.assertThat(basicScore).isEqualTo(11);
    }

    @Test
    void 최종점수합을_계산한다() {
        Card cloverAce = Card.of(CardDenomination.ACE, CardEmblem.CLOVER);
        Card spadeJack = Card.of(CardDenomination.JACK, CardEmblem.SPADE);
        List<Card> cards = List.of(cloverAce, spadeJack);
        Hand hand = Hand.from(cards);

        int resultScore = hand.getResultScore(); // 21

        Assertions.assertThat(resultScore).isEqualTo(21);
    }

    @Test
    void 플레이어_초기_카드_2장의_합이_21인_경우_블랙잭이다() {
        Card cloverAce = Card.of(CardDenomination.ACE, CardEmblem.CLOVER);
        Card spadeJack = Card.of(CardDenomination.JACK, CardEmblem.SPADE);
        List<Card> cards = List.of(cloverAce, spadeJack); //sum = 21
        Hand hand = Hand.from(cards);

        boolean blackjack = hand.isBlackjack();

        Assertions.assertThat(blackjack).isTrue();
    }

    @Test
    void 플레이어_초기_카드_2장의_합이_21이_아닌_경우_블랙잭이_아니다() {
        Card cloverAce = Card.of(CardDenomination.ACE, CardEmblem.CLOVER);
        Card spadeTwo = Card.of(CardDenomination.TWO, CardEmblem.SPADE);
        List<Card> cards = List.of(cloverAce, spadeTwo); //sum = 13
        Hand hand = Hand.from(cards);

        boolean blackjack = hand.isBlackjack();

        Assertions.assertThat(blackjack).isFalse();
    }

}
