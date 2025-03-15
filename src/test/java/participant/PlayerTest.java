package participant;

import constant.TrumpEmblem;
import constant.TrumpNumber;
import constant.WinningResult;
import game.Card;
import game.Cards;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayerTest {

    @Test
    void 초기_카드_두장을_받아_플레이어를_생성한다() {
        // given
        List<Card> initialCards = makeCards(TrumpNumber.ACE, TrumpNumber.EIGHT);
        Cards cards = new Cards(initialCards);

        // when & then
        assertDoesNotThrow(() -> new Player(new Nickname("pobi"), cards));
    }

    @Test
    void 초기_카드_세장을_받으면_예외를_발생시킨다() {
        // given
        List<Card> initialCards = makeCards(TrumpNumber.ACE, TrumpNumber.EIGHT);
        initialCards.add(new Card(TrumpNumber.EIGHT, TrumpEmblem.HEART));
        Cards cards = new Cards(initialCards);

        // when & then
        assertThatThrownBy(() -> new Player(new Nickname("pobi"), cards)).isInstanceOf(IllegalArgumentException.class)
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
        Card card = new Card(number3, TrumpEmblem.SPADE);
        Cards cards = new Cards(initialCards);
        Player player = new Player(new Nickname("pobi"),cards);

        // when
        boolean isOverBustStandard = player.addOneCard(card);

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
        Player player = new Player(new Nickname("pobi"), cards);
        player.addOneCard(new Card(number3, TrumpEmblem.HEART));

        // when
        int sumCards = player.sumCardNumbers();

        // then
        assertThat(sumCards).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "KING, KING, EIGHT, 28, LOSE",
            "KING, NINE, EIGHT, 28, LOSE",
            "KING, KING, EIGHT, 27, LOSE",
            "KING, THREE, FOUR, 27, WIN",
            "KING, KING, EIGHT, 18, LOSE",
            "KING, THREE, EIGHT, 18, WIN",
            "KING, THREE, FOUR, 18, LOSE",
            "KING, THREE, EIGHT, 21, DRAW"
    })
    void 딜러와의_승무패를_정한다(TrumpNumber number1, TrumpNumber number2, TrumpNumber number3, int dealerScore,
                       WinningResult expected) {
        // given
        List<Card> initialCards = makeCards(number1, number2);
        Cards cards = new Cards(initialCards);
        Player player = new Player(new Nickname("pobi"), cards);
        player.addOneCard(new Card(number3, TrumpEmblem.HEART));

        // when
        WinningResult winningResult = player.compareTo(dealerScore);

        // then
        assertThat(winningResult).isEqualTo(expected);
    }

    private List<Card> makeCards(TrumpNumber number1, TrumpNumber number2) {
        List<Card> initialCards = new ArrayList<>();
        initialCards.add(new Card(number1, TrumpEmblem.DIAMOND));
        initialCards.add(new Card(number2, TrumpEmblem.HEART));
        return initialCards;
    }
}
