package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
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
            () -> new Player(name)
        ).doesNotThrowAnyException();
    }

    @Test
    void 플레이어의_닉네임이_11자_이상이면_예외발생() {
        //given
        //when
        //then
        assertThatThrownBy(() -> new Player("asdfghjjklz"))
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
        assertThatThrownBy(() -> new Player(name))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private Player player;

    private final Card twoSpade = new Card(Rank.TWO, Shape.SPADE);
    private final Card threeHeart = new Card(Rank.THREE, Shape.HEART);

    @BeforeEach
    void setUp() {
        player = new Player("player");
    }

    @Test
    void 플레이어는_카드를_받을_수_있다() {
        //given
        //when
        player.takeCards(twoSpade, threeHeart);
        //then
        assertThat(player.getCards()).contains(twoSpade, threeHeart);
    }

    @Test
    void 플레이어의_카드의_점수_합을_구할_수_있다() {
        //given
        player.takeCards(twoSpade, threeHeart);

        //when
        int score = player.calculateScore();

        assertThat(score).isEqualTo(5);
    }

    @Test
    void 플레이어의_점수가_21점_이하일_때_카드를_더_받을수있다() {
        //given
        Card jSpade = new Card(Rank.JACK, Shape.SPADE);
        Card kSpade = new Card(Rank.KING, Shape.SPADE);
        player.takeCards(jSpade, kSpade);

        //when
        boolean canTakeMore = player.canTakeMoreCard();

        //then
        assertThat(canTakeMore).isTrue();
    }

    @Test
    void 플레이어의_점수가_21점을_초과하면_카드를_더_받을수없다() {
        //given
        Card jSpade = new Card(Rank.JACK, Shape.SPADE);
        Card kSpade = new Card(Rank.KING, Shape.SPADE);
        player.takeCards(jSpade, kSpade, twoSpade);

        //when
        boolean canTakeMore = player.canTakeMoreCard();

        //then
        assertThat(canTakeMore).isFalse();
    }
}
