package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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

}
