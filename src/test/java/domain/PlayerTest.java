package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("플레이어는 ")
class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = {"가비", "열_글자_입니다..", "공백도 포함합니다."})
    void 이름은_공백을_제외하고_2에서_10글자이다(String name) {
        //given

        //when

        //then
        assertDoesNotThrow(() -> Player.from(name));
    }

    @ParameterizedTest
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
    void 카드를_뽑을_수_있다() {
        //given
        Player player = Player.from("럿고");
        int beforeCount = player.getCards().size();

        //when
        player.addCard(new Card(Rank.ACE, Suit.CLUB));

        //then
        int afterCount = player.getCards().size();

        assertThat(beforeCount + 1).isEqualTo(afterCount);
    }

    @Test
    void 카드들의_합을_계산_할_수_있다() {
        //given
        Player player = Player.from("연어");
        List<Card> cards = List.of(new Card(Rank.ACE, Suit.CLUB), new Card(Rank.EIGHT, Suit.HEART));
        cards.forEach(player::addCard);

        //when
        int totalScore = player.calculateScore();

        //then
        assertThat(totalScore).isEqualTo(19);
    }

    @Test
    void 카드들의_합이_21_이하라면_더_받을_수_있다() throws Exception {
        //given
        Player player = Player.from("연어");

        //when

        //then
        assertThat(player.isBust()).isTrue();
    }

    @Test
    void 카드들의_합이_21_초과라면_더_받을_수_없다() throws Exception {
        //given
        Player player = Player.from("연어");
        List<Card> cards = List.of(new Card(Rank.FIVE, Suit.CLUB),
                new Card(Rank.TEN, Suit.HEART),
                new Card(Rank.TEN, Suit.SPACE));

        //when
        cards.forEach(player::addCard);

        //then
        assertThat(player.isBust()).isFalse();
    }
}