package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.constant.TrumpRank;
import blackjack.constant.TrumpSuit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardsTest {

    @Test
    void 카드를_한장_더한다() {
        // given
        Cards cards = createCards(
                createCard(TrumpRank.ACE, TrumpSuit.HEART)
        );
        Card card = createCard(TrumpRank.SIX, TrumpSuit.SPADE);

        // when
        cards.addCard(card);

        // then
        assertThat(cards.getCards()).hasSize(2);
    }

    @Test
    void 카드를_뒤에서_한장_뽑는다() {
        // given
        Cards cards = createCards(
                createCard(TrumpRank.ACE, TrumpSuit.HEART),
                createCard(TrumpRank.SIX, TrumpSuit.SPADE)
        );

        // when
        Card card = cards.drawCard();

        // then
        assertThat(card.getRank()).isEqualTo(TrumpRank.SIX);
        assertThat(card.getSuit()).isEqualTo(TrumpSuit.SPADE);
    }

    @Test
    void 남은_카드가_없을_시_카드를_뽑으면_에러가_발생한다() {
        // given
        Cards cards = createCards();

        // when // then
        assertThatThrownBy(() -> cards.drawCard())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 남은 카드가 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ACE, ACE, KING, 12",
            "ACE, THREE, FOUR, 18",
            "ACE, THREE, KING, 14",
    })
    void 카드들의_총합을_계산한다(TrumpRank rank1, TrumpRank rank2, TrumpRank rank3, int expected) {
        // given
        Cards cards = createCards(
                createCard(rank1, TrumpSuit.DIAMOND),
                createCard(rank2, TrumpSuit.CLOVER),
                createCard(rank3, TrumpSuit.HEART)
        );

        // when
        int sumCards = cards.sumCardScores();

        // then
        assertThat(sumCards).isEqualTo(expected);
    }

    @Test
    void 갬블러들에게_배부할_초기_카드를_뽑는다() {
        // given
        Cards cards = createCards(
                createCard(TrumpRank.ACE, TrumpSuit.DIAMOND),
                createCard(TrumpRank.TWO, TrumpSuit.SPADE),
                createCard(TrumpRank.THREE, TrumpSuit.CLOVER),
                createCard(TrumpRank.FOUR, TrumpSuit.HEART)
        );
        // when
        List<Card> initialCards = cards.drawInitialCards().getCards();

        // then
        assertThat(initialCards).hasSize(2);
    }

    @Test
    void 딜러의_초기카드를_오픈한다() {
        // given
        Cards cards = createCards(
                createCard(TrumpRank.ACE, TrumpSuit.DIAMOND),
                createCard(TrumpRank.TWO, TrumpSuit.SPADE)
        );
        // when
        List<Card> initialCards = cards.openDealerInitialCards();

        // then
        assertThat(initialCards).hasSize(1);
        assertThat(initialCards.getFirst().getRank()).isEqualTo(TrumpRank.ACE);
        assertThat(initialCards.getFirst().getSuit()).isEqualTo(TrumpSuit.DIAMOND);
    }

    @Test
    void 플레이어의_초기카드를_오픈한다() {
        // given
        Cards cards = createCards(
                createCard(TrumpRank.ACE, TrumpSuit.DIAMOND),
                createCard(TrumpRank.TWO, TrumpSuit.SPADE)
        );
        // when
        List<Card> initialCards = cards.drawInitialCards().getCards();

        // then
        assertThat(initialCards).hasSize(2);
        assertThat(initialCards.getFirst().getRank()).isEqualTo(TrumpRank.TWO);
        assertThat(initialCards.getFirst().getSuit()).isEqualTo(TrumpSuit.SPADE);
    }

    @Test
    void 카드가_블랙잭인지_판별한다() {
        // given
        Cards cards = createCards(
                createCard(TrumpRank.ACE, TrumpSuit.DIAMOND),
                createCard(TrumpRank.KING, TrumpSuit.SPADE)
        );

        // when
        boolean blackjack = cards.isBlackjack();

        // then
        assertThat(blackjack).isTrue();
    }

    private Cards createCards(Card... cards) {
        return Arrays.stream(cards)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Cards::new));
    }

    private Card createCard(TrumpRank rank, TrumpSuit suit) {
        return new Card(rank, suit);
    }
}
