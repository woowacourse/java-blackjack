package domain;

import domain.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeckTest {

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
        assertThat(phobi.getFinalDeckSum()).isEqualTo(9);
    }

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
        assertThat(phobi.getFinalDeckSum()).isEqualTo(18);
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

        // then
        assertThat(phobi.getFinalDeckSum()).isEqualTo(16);
    }

    @Test
    void 블랙잭_판단_테스트() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(CardRank.ACE, CardShape.HEART));
        cards.add(Card.of(CardRank.TEN, CardShape.HEART));

        // when
        Deck deck = Deck.of(cards);

        // then
        assertThat(deck.getDeckStatus()).isEqualTo(DeckStatus.BLACKJACK);
    }
}
