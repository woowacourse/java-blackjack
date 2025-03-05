package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;
    private Card card;

    @BeforeEach
    void setUp() {
        player = new Player(new Nickname("hihi"));
        card = new Card(Rank.SIX, Shape.CLOVER);
    }

    @DisplayName("플레이어의 카드 합을 구한다.")
    @Test
    void 플레이어의_카드_합을_구한다() {

        // given

        // when & then
        assertThat(player.calculateSumOfRank()).isZero();
    }

    @DisplayName("플레이어는 카드를 한 장 받는다.")
    @Test
    void 플레이어는_카드를_한_장_받는다() {

        // given
        final Player player = new Player(new Nickname("hihi"));
        final Card card = new Card(Rank.SIX, Shape.CLOVER);

        // when
        int previousResult = player.calculateSumOfRank();
        player.hit(card);

        // then
        assertThat(player.calculateSumOfRank()).isEqualTo(previousResult + card.getScore());
    }

    @DisplayName("플레이어가 버스트면 true를 반환한다.")
    @Test
    void 플레이어가_버스트면_true를_반환한다() {

        // given
        Card card1 = new Card(Rank.KING, Shape.CLOVER);
        Card card2 = new Card(Rank.QUEEN, Shape.CLOVER);
        Card card3 = new Card(Rank.JACK, Shape.CLOVER);
        final Player player = new Player(new Nickname("hihi"));
        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        // when
        boolean actual = player.isBust();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("플레이어가 버스트면 false를 반환한다.")
    @Test
    void 플레이어가_버스트면_false를_반환한다() {

        // given
        Card card1 = new Card(Rank.KING, Shape.CLOVER);
        final Player player = new Player(new Nickname("hihi"));
        player.hit(card1);

        // when
        boolean actual = player.isBust();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("플레이어는 초기 카드 두 장을 받는다.")
    @Test
    void 플레이어는_초기_카드_두_장을_받는다() {

        // given
        Card card1 = new Card(Rank.KING, Shape.CLOVER);
        Card card2 = new Card(Rank.QUEEN, Shape.CLOVER);

        // when & then
        assertThatCode(() -> player.receiveInitialCards(List.of(card1, card2)))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어는 카드 한 장을 받는다.")
    @Test
    void 플레이어는_카드_한_장을_받는다() {

        // given
        Card card1 = new Card(Rank.KING, Shape.CLOVER);

        // when & then
        assertThatCode(() -> player.hit(card1))
                .doesNotThrowAnyException();
    }
}
