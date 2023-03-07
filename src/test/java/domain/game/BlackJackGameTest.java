package domain.game;

import domain.card.BlackJackScore;
import domain.card.CardDeck;
import domain.player.*;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static domain.fixture.CardAreaFixture.*;
import static domain.fixture.GamblerFixture.*;
import static domain.fixture.NameFixture.말랑이름;
import static domain.fixture.NameFixture.코다이름;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("BlackJackGame 은")
class BlackJackGameTest {

    private final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();

    @Test
    void 기본_세팅_시_참여자의_이름과_게임에서_사용할_카드_덱을_받아_딜러와_참여자들에게_카드를_2장씩_배분한_상태로_생성된다() {
        // given
        final List<Name> names = List.of(말랑이름(), 코다이름());
        final int before = cardDeck.cards().size();

        // when
        BlackJackGame blackJackGame = BlackJackGame.defaultSetting(cardDeck, names);

        // then
        assertThat(cardDeck.cards().size()).isEqualTo(before - (names.size() * 2 + 2));
        blackJackGame.gamblers().forEach(it -> assertThat(it.cardArea().cards().size()).isEqualTo(2));
        assertThat(blackJackGame.dealer().cardArea().cards().size()).isEqualTo(2);
    }

    @Test
    void hitForDealer_시_딜러에_hit_한다() {
        // given
        final Dealer dealer = new Dealer(under16CardArea());
        BlackJackGame blackJackGame = new BlackJackGame(List.of(코다(under21CardArea())), dealer, CardDeck.shuffledFullCardDeck());
        final BlackJackScore before = dealer.score();

        // when
        blackJackGame.hitForDealer();

        // then
        assertThat(dealer.score()).isNotEqualTo(before);
    }

    @Test
    void 게임_결과를_통계낼_수_있다() {
        // given

        // 말랑 - 20(무), 콩떡 - 30(패), 코다 - 21(승)
        // 딜러 - 20
        final Gambler 말랑 = 말랑(equal20CardArea());
        final Gambler 콩떡 = 콩떡(over21CardArea());
        final Gambler 코다 = 코다(equal21CardArea());
        final Dealer dealer = new Dealer(equal20CardArea());

        final BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 콩떡, 코다), dealer, CardDeck.shuffledFullCardDeck());

        // when
        final Map<Gambler, DealerCompeteResult> statistic = blackJackGame.statistic();

        // then
        assertThat(statistic.get(말랑)).isEqualTo(DealerCompeteResult.DRAW);
        assertThat(statistic.get(콩떡)).isEqualTo(DealerCompeteResult.WIN);
        assertThat(statistic.get(코다)).isEqualTo(DealerCompeteResult.LOSE);
    }

    @Nested
    @DisplayName("existCanHitParticipant() 테스트")
    class ExistCanHitGamblerTest {

        @Test
        void 참가자들_중_21_미만인_참가자가_있음에도_그들이_Hit_을_원하지_않는다면_false를_반환한다() {
            // given
            final Gambler 말랑 = 말랑(under21CardArea());
            final Gambler 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());
            blackJackGame.gamblers().forEach(it -> it.changeState(HitState.STAY));

            // when & then
            assertThat(blackJackGame.existCanHitParticipant()).isFalse();
        }

        @Test
        void 참가자들_중_21_미만인_참가자가_없는_경우_false_를_반환한다() {
            // given
            final Gambler 말랑 = 말랑(over21CardArea());
            final Gambler 코다 = 코다(over21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());

            // when & then
            assertThat(blackJackGame.existCanHitParticipant()).isFalse();
        }

        @Test
        void 참가자들_중_21_미만인_참가자가_있으며_Hit_을_원하는_경우_true_를_반환한다() {
            // given
            final Gambler 말랑 = 말랑(under21CardArea());
            final Gambler 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());
            말랑.changeState(HitState.HIT);

            // when & then
            assertThat(blackJackGame.existCanHitParticipant()).isTrue();
        }

        @Test
        void 참가자들_중_21_미만인_참가자가_있으며_Hit_여부를_경정하지_않은_경우_true_를_반환한다() {
            // given
            final Gambler 말랑 = 말랑(under21CardArea());
            final Gambler 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());

            // when & then
            assertThat(blackJackGame.existCanHitParticipant()).isTrue();
        }
    }

    @Nested
    @DisplayName("findCanHitParticipant() 테스트")
    class FindCanHitGamblerTest {

        @Test
        void Hit_이_가능한_임의의_참여자를_반환한다() {
            // given
            final Gambler 말랑 = 말랑(over21CardArea());
            final Gambler 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());

            // when
            Gambler canHit = blackJackGame.findCanHitParticipant();

            // then
            assertThat(canHit).isEqualTo(코다);
        }

        @Test
        void Hit_이_가능한_참여자가_없는_경우_예외를_발생한다() {
            // given
            final Gambler 말랑 = 말랑(over21CardArea());
            final Gambler 코다 = 코다(over21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());

            // when & then
            assertThatThrownBy(blackJackGame::findCanHitParticipant)
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("hitOrStayForParticipant() 테스트")
    class HitOrStayForGamblerTest {

        @Test
        void 주어진_참가자가_Hit_을_원한다면_카드를_제공한다() {
            // given
            final Gambler 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());
            final BlackJackScore before = 코다.score();

            // when
            blackJackGame.hitOrStayForParticipant(코다, HitState.HIT);

            // then
            assertThat(코다.score()).isNotEqualTo(before);
        }

        @Test
        void 주어진_참가자가_Hit_을_원하지_않는다면_카드를_제공하지_않는다() {
            // given
            final Gambler 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());
            final BlackJackScore before = 코다.score();

            // when
            blackJackGame.hitOrStayForParticipant(코다, HitState.STAY);

            // then
            assertThat(코다.score()).isEqualTo(before);
        }
    }

    @Nested
    @DisplayName("isDealerShouldMoreHit() 테스트")
    class IsDealerShouldMoreHitTest {

        @Test
        void 딜러의_카드가_16_점_이하이면_항상_Hit_을_더_해야한다() {
            // given
            final Gambler 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame1 = new BlackJackGame(List.of(코다), new Dealer(under16CardArea()), CardDeck.shuffledFullCardDeck());
            BlackJackGame blackJackGame2 = new BlackJackGame(List.of(코다), new Dealer(equal16CardArea()), CardDeck.shuffledFullCardDeck());

            // when & then
            assertThat(blackJackGame1.isDealerShouldMoreHit()).isTrue();
            assertThat(blackJackGame2.isDealerShouldMoreHit()).isTrue();
        }

        @Test
        void 딜러의_카드가_16_점_초과이면_항상_Hit_을_더_하지_않는다() {
            // given
            final Gambler 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(코다), new Dealer(over16CardArea()), CardDeck.shuffledFullCardDeck());

            // when & then
            assertThat(blackJackGame.isDealerShouldMoreHit()).isFalse();
        }
    }
}