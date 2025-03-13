package participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import card.Card;
import card.Cards;
import constant.TrumpEmblem;
import constant.TrumpNumber;
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
        initialCards.add(new Card(TrumpNumber.SEVEN, TrumpEmblem.CLOVER));
        initialCards.add(new Card(TrumpNumber.SIX, TrumpEmblem.HEART));
        Cards cards = new Cards(initialCards);

        // when // then
        assertDoesNotThrow(() -> new Dealer(cards));
    }

    @Test
    void 초기_카드_세장을_받으면_예외를_발생시킨다() {
        // given
        List<Card> initialCards = makeCards(TrumpNumber.THREE, TrumpNumber.FOUR);
        initialCards.add(new Card(TrumpNumber.FIVE, TrumpEmblem.HEART));
        Cards cards = new Cards(initialCards);

        // when // then
        assertThatThrownBy(() -> new Dealer(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 초기 카드는 2장을 받아야 합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ACE, EIGHT, ACE, true",
            "EIGHT, SEVEN, SIX, true",
            "KING, KING, TWO, false",
            "QUEEN, JACK, KING, false"
    })
    void 카드를_한장_받았을_때_21이_넘는지_확인한다(TrumpNumber number1, TrumpNumber number2, TrumpNumber number3, boolean expected) {
        // given
        List<Card> initialCards = makeCards(number1, number2);
        Cards cards = new Cards(initialCards);
        Card card = new Card(number3, TrumpEmblem.SPADE);
        Dealer dealer = new Dealer(cards);

        // when
        boolean isOverBustStandard = dealer.addOneCard(card);

        // then
        assertThat(isOverBustStandard).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ACE, ACE, KING, 12",
            "ACE, THREE, FOUR, 18",
            "ACE, THREE, KING, 14",
    })
    void 카드들의_합을_구한다(TrumpNumber number1, TrumpNumber number2, TrumpNumber number3, int expected) {
        // given
        List<Card> initialCards = makeCards(number1, number2);
        Cards cards = new Cards(initialCards);
        Dealer dealer = new Dealer(cards);
        dealer.addOneCard(new Card(number3, TrumpEmblem.HEART));

        // when
        int sumCards = dealer.sumCardNumbers();

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
    void 딜러의_카드_총합이_16이하인지_확인한다(TrumpNumber number1, TrumpNumber number2, boolean expected) {
        // given
        List<Card> initialCards = makeCards(number1, number2);
        Cards cards = new Cards(initialCards);
        Dealer dealer = new Dealer(cards);

        // when
        boolean shouldDealerHit = dealer.shouldDealerHit();

        // then
        assertThat(shouldDealerHit).isEqualTo(expected);
    }

    @Test
    void 초기_딜러의_카드중_작은_숫자_한장을_오픈한다() {
        // given
        List<Card> initialCards = makeCards(TrumpNumber.ACE, TrumpNumber.KING);
        Cards cards = new Cards(initialCards);
        Dealer dealer = new Dealer(cards);

        // when
        Card card = dealer.openOneCard();

        // then
        assertThat(card.getNumber()).isEqualTo(TrumpNumber.KING);
        assertThat(card.getEmblem()).isEqualTo(TrumpEmblem.HEART);
    }

    private List<Card> makeCards(TrumpNumber number1, TrumpNumber number2) {
        List<Card> initialCards = new ArrayList<>();
        initialCards.add(new Card(number1, TrumpEmblem.DIAMOND));
        initialCards.add(new Card(number2, TrumpEmblem.HEART));
        return initialCards;
    }
}
