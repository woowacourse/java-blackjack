package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.constant.TrumpSuit;
import blackjack.constant.TrumpRank;
import blackjack.gambler.Dealer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DealerTest {

    @Test
    void 초기_카드_두장을_받아_딜러를_생성한다() {
        // given
        List<Card> initialCards = new ArrayList<>();
        initialCards.add(new Card(TrumpRank.SEVEN, TrumpSuit.CLOVER));
        initialCards.add(new Card(TrumpRank.SIX, TrumpSuit.HEART));
        Cards cards = new Cards(initialCards);

        // when // then
        assertDoesNotThrow(() -> new Dealer(cards));
    }

    @Test
    void 초기_카드_세장을_받으면_예외를_발생시킨다() {
        // given
        List<Card> initialCards = makeCards(TrumpRank.THREE, TrumpRank.FOUR);
        initialCards.add(new Card(TrumpRank.FIVE, TrumpSuit.HEART));
        Cards cards = new Cards(initialCards);

        // when // then
        assertThatThrownBy(() -> new Dealer(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 초기 카드는 2장을 받아야 합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ACE, EIGHT, ACE, false",
            "EIGHT, SEVEN, SIX, false",
            "KING, KING, TWO, true",
            "QUEEN, JACK, KING, true"
    })
    void 카드를_한장_받았을_때_21이_넘는지_확인한다(TrumpRank rank1, TrumpRank rank2, TrumpRank rank3, boolean expected) {
        // given
        List<Card> initialCards = makeCards(rank1, rank2);
        Cards cards = new Cards(initialCards);
        Card card = new Card(rank3, TrumpSuit.SPADE);
        Dealer dealer = new Dealer(cards);
        dealer.addOneCard(card);

        // when
        boolean isOverBustStandard = dealer.isBust();

        // then
        assertThat(isOverBustStandard).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ACE, ACE, KING, 12",
            "ACE, THREE, FOUR, 18",
            "ACE, THREE, KING, 14",
    })
    void 카드들의_합을_구한다(TrumpRank rank1, TrumpRank rank2, TrumpRank rank3, int expected) {
        // given
        List<Card> initialCards = makeCards(rank1, rank2);
        Cards cards = new Cards(initialCards);
        Dealer dealer = new Dealer(cards);
        dealer.addOneCard(new Card(rank3, TrumpSuit.HEART));

        // when
        int sumCards = dealer.sumCardScores();

        // then
        assertThat(sumCards).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ACE, TWO, true",
            "ACE, FIVE, true",
            "ACE, SIX, false",
            "ACE, KING, false"
    })
    void 딜러의_카드_총합이_16이하인지_확인한다(TrumpRank rank1, TrumpRank rank2, boolean expected) {
        // given
        List<Card> initialCards = makeCards(rank1, rank2);
        Cards cards = new Cards(initialCards);
        Dealer dealer = new Dealer(cards);

        // when
        boolean sumUnderThreshold = dealer.isSumUnderThreshold();

        // then
        assertThat(sumUnderThreshold).isEqualTo(expected);
    }

    @Test
    void 초기_딜러의_카드중_작은_숫자_한장을_오픈한다() {
        // given
        List<Card> initialCards = makeCards(TrumpRank.ACE, TrumpRank.KING);
        Cards cards = new Cards(initialCards);
        Dealer dealer = new Dealer(cards);

        // when
        Card card = dealer.openInitialCard();

        // then
        assertThat(card.getRank()).isEqualTo(TrumpRank.KING);
        assertThat(card.getSuit()).isEqualTo(TrumpSuit.HEART);
    }

    private List<Card> makeCards(TrumpRank rank1, TrumpRank rank2) {
        List<Card> initialCards = new ArrayList<>();
        initialCards.add(new Card(rank1, TrumpSuit.DIAMOND));
        initialCards.add(new Card(rank2, TrumpSuit.HEART));
        return initialCards;
    }
}
