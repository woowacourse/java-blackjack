package domain.participant;

import domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
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
        assertDoesNotThrow(() -> Player.from(name, Deck.create(Collections::shuffle)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "abcdqwertyuiopdfghjk"})
    void 이름은_공백을_제외하고_2에서_10글자가_아니면_예외가_발생한다(String name) {
        //given

        //when

        //then
        assertThatThrownBy(() -> Player.from(name, Deck.create(Collections::shuffle)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 이름은 2 ~ 10 글자여야 합니다.");
    }

    @Test
    void 카드를_뽑을_수_있다() {
        //given
        Player player = Player.from("럿고", Deck.create(Collections::shuffle));
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
        Player player = Player.from("연어", Deck.create(notShuffle()));
        List<Card> cards = List.of(new Card(Rank.ACE, Suit.CLUB), new Card(Rank.EIGHT, Suit.HEART));
        cards.forEach(player::addCard);

        //when
        int totalScore = player.calculateScore();

        //then
        assertThat(totalScore).isEqualTo(21);
    }

    @Test
    void 카드들의_합이_21_이하라면_더_받을_수_있다() {
        //given
        Player player = Player.from("연어", Deck.create(notShuffle()));

        //when

        //then
        assertThat(player.isBust()).isFalse();
    }

    @Test
    void 카드들의_합이_21_초과라면_더_받을_수_없다() {
        //given
        Player player = Player.from("연어", Deck.create(notShuffle()));
        List<Card> cards = List.of(new Card(Rank.FIVE, Suit.CLUB),
                new Card(Rank.TEN, Suit.HEART),
                new Card(Rank.TEN, Suit.SPADE));

        //when
        cards.forEach(player::addCard);

        //then
        assertThat(player.isBust()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 10000000})
    void 베팅_금액의_범위는_1000원부터_1000만원까지이다(int bettingAmount) {
        //given
        Player player = Player.from("연어", Deck.create(notShuffle()));

        //when
        player.bet(bettingAmount);

        //then
        assertDoesNotThrow(() -> player.bet(bettingAmount));
        assertThat(player.getBettingAmount()).isEqualTo(bettingAmount);
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 10000001})
    void 베팅_금액의_범위는_1000원_1000만원이_아니면_예외가_발생한다(int bettingAmount) {
        //given
        Player player = Player.from("연어", Deck.create(notShuffle()));

        //when

        //then
        assertThatThrownBy(() -> player.bet(bettingAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 1000 ~ 10억원 까지만 가능합니다");
    }

    @Test
    void 베팅_금액은_1000원_단위이다() {
        //given
        Player player = Player.from("연어", Deck.create(notShuffle()));

        //when

        //then
        assertDoesNotThrow(() -> player.bet(2000));
    }

    @Test
    void 베팅_금액은_1000원_단위가_아니면_예외가_발생한다() {
        //given
        Player player = Player.from("연어", Deck.create(notShuffle()));

        //when

        //then
        assertThatThrownBy(() -> player.bet(2500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 1000원 단위만 가능합니다");
    }

    private ShuffleStrategy notShuffle() {
        return cards -> {
        };
    }
}
