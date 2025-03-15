package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {

    Player player;
    Card card1;
    Card card2;
    Card card3;
    Card card4;

    @BeforeEach
    void setUp() {
        player = new Player(new Nickname("hihi"), new Betting(1000));
        card1 = new Card(Rank.ACE, Shape.CLOVER);
        card2 = new Card(Rank.TWO, Shape.CLOVER);
        card3 = new Card(Rank.THREE, Shape.CLOVER);
        card4 = new Card(Rank.FOUR, Shape.CLOVER);
    }

    @DisplayName("플레이어의 카드 합을 구한다.")
    @Test
    void 플레이어의_카드_합을_구한다() {

        // given

        // when & then
        assertThat(player.getSumOfRank()).isZero();
    }

    @DisplayName("플레이어는 카드를 한 장 받는다.")
    @Test
    void 플레이어는_카드를_한_장_받는다() {

        // given
        final Player player = new Player(new Nickname("hihi"), new Betting(1000));
        final Card card = new Card(Rank.SIX, Shape.CLOVER);

        // when
        final int previousResult = player.getSumOfRank();
        player.hit(card);

        // then
        assertThat(player.getSumOfRank()).isEqualTo(previousResult + card.getScore());
    }

    @DisplayName("플레이어가 버스트면 true를 반환한다.")
    @Test
    void 플레이어가_버스트면_true를_반환한다() {

        // given
        final Card card1 = new Card(Rank.KING, Shape.CLOVER);
        final Card card2 = new Card(Rank.QUEEN, Shape.CLOVER);
        final Card card3 = new Card(Rank.JACK, Shape.CLOVER);
        final Player player = new Player(new Nickname("hihi"), new Betting(1000));
        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        // when
        final boolean actual = player.isBust();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("플레이어가 버스트면 false를 반환한다.")
    @Test
    void 플레이어가_버스트면_false를_반환한다() {

        // given
        final Card card1 = new Card(Rank.KING, Shape.CLOVER);
        final Player player = new Player(new Nickname("hihi"), new Betting(1000));
        player.hit(card1);

        // when
        final boolean actual = player.isBust();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("플레이어는 초기 카드 두 장을 받는다.")
    @Test
    void 플레이어는_초기_카드_두_장을_받는다() {

        // given
        final Card card1 = new Card(Rank.KING, Shape.CLOVER);
        final Card card2 = new Card(Rank.QUEEN, Shape.CLOVER);

        // when & then
        assertThatCode(() -> player.receiveInitialCards(List.of(card1, card2)))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어는 카드 한 장을 받는다.")
    @Test
    void 플레이어는_카드_한_장을_받는다() {

        // given
        final Card card1 = new Card(Rank.KING, Shape.CLOVER);

        // when & then
        assertThatCode(() -> player.hit(card1))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어의 카드 중 최고 값을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "TWO, EIGHT, ACE, 21", "ACE, ACE, ACE, 13",
            "KING, JACK, ACE, 21", "THREE, FOUR, FIVE, 12",
    })
    void 플레이어의_카드_중_최고_값을_반환한다(final Rank rank1, final Rank rank2, final Rank rank3, final int expected) {

        // given
        final Card card1 = new Card(rank1, Shape.CLOVER);
        final Card card2 = new Card(rank2, Shape.CLOVER);
        final Card card3 = new Card(rank3, Shape.CLOVER);

        player.receiveInitialCards(List.of(card1, card2));
        player.hit(card3);

        // when
        final int sumOfRank = player.getSumOfRank();

        // then
        assertThat(sumOfRank).isEqualTo(expected);
    }

    @DisplayName("플레이어의 카드 중 최고 값을 반환한다2")
    @Test
    void 플레이어의_카드_중_최고_값을_반환한다2() {

        // given
        final Card card1 = new Card(Rank.KING, Shape.CLOVER);
        final Card card2 = new Card(Rank.KING, Shape.CLOVER);
        final Card card3 = new Card(Rank.KING, Shape.CLOVER);
        final Card card4 = new Card(Rank.ACE, Shape.CLOVER);

        player.receiveInitialCards(List.of(card1, card2));
        player.hit(card3);
        player.hit(card4);

        // when
        final int sumOfRank = player.getSumOfRank();

        // then
        assertThat(sumOfRank).isEqualTo(31);
    }

    @DisplayName("블랙잭 상태면 true를 반환한다")
    @Test
    void 블랙잭_상태면_true를_반환한다() {

        // given

        // when
        player.hit(new Card(Rank.ACE, Shape.SPADE));
        player.hit(new Card(Rank.JACK, Shape.SPADE));

        // when & then
        assertThat(player.isBlackJack()).isTrue();
    }

    @DisplayName("블랙잭 상태가 아니라면 false를 반환한다")
    @Test
    void 블랙잭_상태가_아니라면_false를_반환한다() {

        // given

        // when
        player.hit(new Card(Rank.ACE, Shape.SPADE));
        player.hit(new Card(Rank.FIVE, Shape.SPADE));
        player.hit(new Card(Rank.FIVE, Shape.HEART));

        // when & then
        assertThat(player.isBlackJack()).isFalse();
    }

    @DisplayName("손에 있는 카드의 최소 합이 21이 안넘어가면 카드를 뽑을 수 있다.")
    @Test
    void 손에_있는_카드의_최소_합이_21이_안넘어가면_카드를_뽑을_수_있다() {

        // given
        player.hit(new Card(Rank.TEN, Shape.SPADE));
        player.hit(new Card(Rank.TEN, Shape.SPADE));

        // when & then
        assertThat(player.isImPossibleDrawCard()).isFalse();
    }

    @DisplayName("손에 있는 카드의 최소 합이 21이 넘어가면 카드를 뽑지 않는다.")
    @Test
    void 손에_있는_카드의_최소_합이_21이_넘어가면_카드를_뽑지_않는다() {

        // given
        player.hit(new Card(Rank.TEN, Shape.SPADE));
        player.hit(new Card(Rank.TEN, Shape.SPADE));
        player.hit(new Card(Rank.ACE, Shape.SPADE));

        // when & then
        assertThat(player.isImPossibleDrawCard()).isTrue();
    }

    @DisplayName("플레이어가 블랙잭이 아니면서 승리 시 배팅금액 만큼 수익을 얻는다.")
    @Test
    void 플레이어가_블랙잭이_아니면서_승리_시_배팅금액_만큼_수익을_얻는다() {

        // given
        final int profit = player.winBetting(0);

        // when & then
        assertThat(profit).isEqualTo(1000);
    }

    @DisplayName("플레이어가 블랙잭이면서 승리 시 배팅금액 만큼 수익을 얻는다.")
    @Test
    void 플레이어가_블랙잭이면서_승리_시_배팅금액_만큼_수익을_얻는다() {

        // given
        player.hit(new Card(Rank.ACE, Shape.SPADE));
        player.hit(new Card(Rank.JACK, Shape.SPADE));
        final int profit = player.winBetting(0);

        // when & then
        assertThat(profit).isEqualTo(1500);
    }

    @DisplayName("플레이어가 패배 시 배팅금액 만큼 수익을 잃는다.")
    @Test
    void 플레이어가_패배_시_배팅금액_만큼_수익을_잃는다() {

        // given
        player.hit(new Card(Rank.ACE, Shape.SPADE));
        player.hit(new Card(Rank.JACK, Shape.SPADE));
        final int profit = player.loseBetting(0);

        // when & then
        assertThat(profit).isEqualTo(-1000);
    }
}
