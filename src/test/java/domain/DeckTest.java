package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.model.Card;
import domain.model.CardRank;
import domain.model.CardShape;
import domain.model.Deck;
import domain.model.DeckStatus;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 초기_카드_2장의_합이_21인_경우() {
        // given
        List<Card> cards = List.of(
                Card.of(CardRank.ACE, CardShape.CLUB),
                Card.of(CardRank.TEN, CardShape.CLUB)
        );

        // when
        Deck deck = Deck.of(cards);

        // then
        assertThat(deck.getDeckStatus()).isEqualTo(DeckStatus.BLACK_JACK);
    }

    @Test
    void 카드들의_합이_21을_넘어간_경우() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(CardRank.TEN, CardShape.CLUB));
        cards.add(Card.of(CardRank.NINE, CardShape.CLUB));
        Deck deck = Deck.of(cards);

        // when
        deck.append(Card.of(CardRank.FOUR, CardShape.HEART));

        // then
        assertThat(deck.getDeckStatus()).isEqualTo(DeckStatus.BURST);
    }


    @Test
    void 게임_진행_중_카드_총합_테스트() {
        // given
        List<Card> cards = List.of(
                Card.of(CardRank.TEN, CardShape.CLUB),
                Card.of(CardRank.FOUR, CardShape.DIAMOND)
        );
        Deck deck = Deck.of(cards);

        // when
        int sum = deck.getSum();

        // then
        assertThat(sum).isEqualTo(14);
    }

    @Test
    void 에이스_1_판단이_유리한_최종_카드_총합_테스트() {
        // given
        List<Card> cards = List.of(
                Card.of(CardRank.TEN, CardShape.CLUB),
                Card.of(CardRank.FOUR, CardShape.DIAMOND),
                Card.of(CardRank.ACE, CardShape.DIAMOND)
        );
        Deck deck = Deck.of(cards);

        // when
        int sum = deck.calculateFinalSum();

        // then
        assertThat(sum).isEqualTo(15);
    }

    @Test
    void 에이스_11_판단이_유리한_최종_카드_총합_테스트() {
        // given
        List<Card> cards = List.of(
                Card.of(CardRank.TEN, CardShape.CLUB),
                Card.of(CardRank.ACE, CardShape.DIAMOND)
        );
        Deck deck = Deck.of(cards);

        // when
        int sum = deck.calculateFinalSum();

        // then
        assertThat(sum).isEqualTo(21);
    }

    @Test
    void 카드_추가_테스트() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(CardRank.TEN, CardShape.CLUB));
        cards.add(Card.of(CardRank.NINE, CardShape.CLUB));
        Deck deck = Deck.of(cards);

        // when
        deck.append(Card.of(CardRank.TWO, CardShape.HEART));

        // then
        assertThat(deck.getSize()).isEqualTo(3);

    }

    @Test
    void 마지막_카드_반환_테스트() {
        // given
        Card firstCard = Card.of(CardRank.TEN, CardShape.CLUB);
        Card secondCard = Card.of(CardRank.FOUR, CardShape.DIAMOND);
        List<Card> cards = List.of(
                firstCard,
                secondCard
        );
        Deck deck = Deck.of(cards);

        // when
        Card lastCard = deck.getLastCard();

        // then
        assertThat(lastCard).isEqualTo(secondCard);
    }
}
