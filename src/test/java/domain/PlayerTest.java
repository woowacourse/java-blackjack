package domain;

import domain.card.Card;
import domain.card.Cards;
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
        assertDoesNotThrow(() -> Player.of(name, new Cards()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "abcdqwertyuiopdfghjk"})
    void 이름은_공백을_제외하고_2에서_10글자가_아니면_예외가_발생한다(String name) {
        //given

        //when

        //then
        assertThatThrownBy(() -> Player.of(name, new Cards()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 이름은 2 ~ 10 글자여야 합니다.");
    }

    @Test
    void 카드를_뽑을_수_있다() {
        //given
        Player player = Player.of("럿고", new Cards());
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
        Player player = Player.of("연어", new Cards());
        List<Card> cards = List.of(new Card(Rank.ACE, Suit.CLUB), new Card(Rank.EIGHT, Suit.HEART));
        cards.forEach(player::addCard);

        //when
        int totalScore = player.calculateScore();

        //then
        assertThat(totalScore).isEqualTo(19);
    }
}