package domain;

import domain.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardCalculatorTest {

    @Test
    void 플레이어_덱_합산_테스트() {
        // given
        Player phobi = Player.of("phobi");

        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(CardRank.TWO, CardShape.HEART));
        cards.add(Card.of(CardRank.THREE, CardShape.HEART));
        cards.add(Card.of(CardRank.FOUR, CardShape.HEART));

        // when
        phobi.assignDeck(Deck.of(cards));

        // then
        assertThat(phobi.getDeckSum()).isEqualTo(9);
    }

    // TODO: ACE 예외 케이스랑 엣지케이스들 추가
    @Test
    void 플레이어덱_합산_ACE_1로_처리_테스트() {
        // given
        Player phobi = Player.of("phobi");

        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(CardRank.ACE, CardShape.HEART));
        cards.add(Card.of(CardRank.EIGHT, CardShape.HEART));
        cards.add(Card.of(CardRank.NINE, CardShape.HEART));

        // when
        phobi.assignDeck(Deck.of(cards));

        // then
        assertThat(phobi.getDeckSum()).isEqualTo(18);
    }

    @Test
    void 플레이어덱_합산_ACE_11로_처리_테스트() {
        // given
        Player phobi = Player.of("phobi");

        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(CardRank.ACE, CardShape.HEART));
        cards.add(Card.of(CardRank.TWO, CardShape.HEART));
        cards.add(Card.of(CardRank.THREE, CardShape.HEART));

        // when
        phobi.assignDeck(Deck.of(cards));
        phobi.calculateFinalSum();

        // then
        assertThat(phobi.getDeckSum()).isEqualTo(16);
    }
}
