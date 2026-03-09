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

    @Test
    void ACE_11이_유리한_경우_테스트() {
        // given
        Player phobi = Player.of("phobi");

        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(CardRank.EIGHT, CardShape.HEART));
        cards.add(Card.of(CardRank.ACE, CardShape.HEART));

        // when
        phobi.assignDeck(Deck.of(cards));
        phobi.calculateFinalSum();

        // then
        assertThat(phobi.getDeckSum()).isEqualTo(19);

    }
}
