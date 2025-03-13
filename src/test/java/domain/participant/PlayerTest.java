package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.CardHand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "abcdeabcde"})
    void 한자리에서_열자리사이의_닉네임으로_된_플레이어를_만들수_있다(String name) {
        //given
        //when
        //then
        assertThatCode(
            () -> new Player(name, new CardHand())
        ).doesNotThrowAnyException();
    }

    @Test
    void 플레이어의_닉네임이_11자_이상이면_예외발생() {
        //given
        //when
        //then
        assertThatThrownBy(() -> new Player("asdfghjjklz", new CardHand()))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ",})
    @NullSource
    @EmptySource
    void 플레이어의_닉네임이_공백이면_예외발생(String name) {
        //given
        //when
        //then
        assertThatThrownBy(() -> new Player(name, new CardHand()))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("player", new CardHand());
    }

    @Test
    void 플레이어는_카드를_받을_수_있다() {
        //given
        //when
        player.takeCards(Card.SPADE_2, Card.HEART_3);
        //then
        assertThat(player.getCards()).contains(Card.SPADE_2, Card.HEART_3);
    }

    @Test
    void 플레이어의_카드의_점수_합을_구할_수_있다() {
        //given
        player.takeCards(Card.SPADE_2, Card.HEART_3);

        //when
        int score = player.calculateScore();

        assertThat(score).isEqualTo(5);
    }

    @Test
    void 플레이어가_블랙잭인지_알_수_있다() {
        //given
        player.takeCards(Card.SPADE_A, Card.HEART_10);

        //when
        boolean blackJack = player.isBlackJack();

        //then
        assertThat(blackJack).isTrue();
    }

    @Test
    void 점수가_21점을_넘으면_버스트이다() {
        player.takeCards(Card.SPADE_10, Card.HEART_10, Card.SPADE_2);
        assertThat(player.isBust()).isTrue();
    }

    @Test
    void 플레이어의_점수가_21점_미만일_때_카드를_더_받을수있다() {
        //given
        player.takeCards(Card.SPADE_J, Card.SPADE_K);

        //when
        boolean canTakeMore = player.canTakeMoreCard();

        //then
        assertThat(canTakeMore).isTrue();
    }

    @Test
    void 플레이어의_점수가_21점_이상이면_카드를_더_받을수없다() {
        //given
        player.takeCards(Card.SPADE_J, Card.SPADE_A);

        //when
        boolean canTakeMore = player.canTakeMoreCard();

        //then
        assertThat(canTakeMore).isFalse();
    }
}
