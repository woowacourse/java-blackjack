package domain.participant;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("Player는 ")
class PlayerTest {

    @ParameterizedTest(name = "이름은 공백을 제거하고 2~10글자이다 name = {0}")
    @ValueSource(strings = {"가비", "열_글자_입니다..", "공백도 포함합니다."})
    void 이름은_공백을_제외하고_2에서_10글자이다(String name) {
        //given

        //when

        //then
        assertDoesNotThrow(() -> Player.from(name));
    }

    @ParameterizedTest(name = "이름은 공백을 제거하고 2~10글자가 아니면 예외가 발생한다 name = {0}")
    @ValueSource(strings = {"", "1", "abcdqwertyuiopdfghjk"})
    void 이름은_공백을_제외하고_2에서_10글자가_아니면_예외가_발생한다(String name) {
        //given

        //when

        //then
        assertThatThrownBy(() -> Player.from(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 이름은 2 ~ 10 글자여야 합니다.");
    }

    @Test
    void 딜러를_이름으로_가질_수_없다() {
        //given
        String name = "딜러";

        //when

        //then
        assertThatThrownBy(() -> Player.from(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 이름은 '딜러'일 수 없습니다.");
    }

    @Test
    void 카드를_뽑을_수_있다() {
        //given
        Player player = Player.from("럿고");
        int beforeCount = player.getCards().size();

        //when
        player.addCard(new Card(Denomination.ACE, Suit.CLUB));

        //then
        int afterCount = player.getCards().size();

        assertThat(beforeCount + 1).isEqualTo(afterCount);
    }

    @Test
    void 카드들의_합을_계산_할_수_있다() {
        //given
        Player player = Player.from("연어");
        List<Card> cards = List.of(new Card(Denomination.ACE, Suit.CLUB), new Card(Denomination.EIGHT, Suit.HEART));
        cards.forEach(player::addCard);

        //when
        int totalScore = player.calculateScore();

        //then
        assertThat(totalScore).isEqualTo(19);
    }

    @Test
    void 카드들의_합이_21_이하라면_더_받을_수_있다() {
        //given
        Player player = Player.from("연어");

        //when

        //then
        assertThat(player.isBust()).isFalse();
    }

    @Test
    void 카드들의_합이_21_초과라면_더_받을_수_없다() {
        //given
        Player player = Player.from("연어");
        List<Card> cards = List.of(new Card(Denomination.FIVE, Suit.CLUB),
                new Card(Denomination.TEN, Suit.HEART),
                new Card(Denomination.TEN, Suit.SPADE));

        //when
        cards.forEach(player::addCard);

        //then
        assertThat(player.isBust()).isTrue();
    }
}
