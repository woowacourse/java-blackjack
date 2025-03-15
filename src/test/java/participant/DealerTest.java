package participant;

import constant.TrumpEmblem;
import constant.TrumpNumber;
import game.Card;
import game.Cards;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DealerTest {

    @Test
    void 초기_카드_두장을_받아_딜러를_생성한다() {
        // given
        List<Card> initialCards = makeCards(TrumpNumber.FIVE, TrumpNumber.FOUR);

        // when & then
        assertDoesNotThrow(
                () -> new Dealer(() -> new Cards(initialCards))
        );
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
        Dealer dealer = new Dealer(() -> new Cards(initialCards));
        Card card = new Card(number3, TrumpEmblem.SPADE);

        // when
        boolean isUnderBustStandard = dealer.addOneCard(card);

        // then
        assertThat(isUnderBustStandard).isEqualTo(expected);
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
        Dealer dealer = new Dealer(() -> new Cards(initialCards));
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
        Dealer dealer = new Dealer(() -> new Cards(initialCards));

        // when
        boolean shouldDealerHit = dealer.shouldDealerHit();

        // then
        assertThat(shouldDealerHit).isEqualTo(expected);
    }

    @Test
    void 초기_딜러의_카드중_작은_숫자_한장을_오픈한다() {
        // given
        List<Card> initialCards = makeCards(TrumpNumber.ACE, TrumpNumber.KING);
        Dealer dealer = new Dealer(() -> new Cards(initialCards));

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
