package domain.game;

import domain.card.BlackJackScore;
import domain.card.CardDeck;
import domain.player.BettingMoney;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.HitState;
import domain.player.Name;
import domain.player.Participant;
import domain.player.Revenue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static domain.fixture.CardAreaFixture.*;
import static domain.fixture.GamblerFixture.*;
import static domain.fixture.NameFixture.말랑이름;
import static domain.fixture.NameFixture.코다이름;
import static domain.player.GameResult.*;
import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("BlackJackGame 은")
class BlackJackGameTest {

    private final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();

    private static Map<Name, BettingMoney> makeBattingMoneyMapFromNames(final List<Name> names) {
        return names.stream().collect(Collectors.toMap(Function.identity(), it -> BettingMoney.of(10000)));
    }

    @Test
    void 기본_세팅_시_참여자의_이름과_배팅_금액과_게임에서_사용할_카드_덱을_받아_딜러와_참여자들을_생성한다() {
        // given
        final List<Name> names = List.of(말랑이름(), 코다이름());

        // when
        BlackJackGame blackJackGame = BlackJackGame.defaultSetting(cardDeck, makeBattingMoneyMapFromNames(names));

        // then
        assertThat(blackJackGame.gamblers().size()).isEqualTo(names.size());
        assertThat(blackJackGame.dealer()).isNotNull();
    }

    @Test
    void 참가자별_수익을_낼_수_있다() {
        // given

        // 말랑 - 20(무), 콩떡 - 30(패), 코다 - 21(승)
        // 딜러 - 20
        final Gambler 말랑 = 말랑(equal20CardArea());
        final Gambler 콩떡 = 콩떡(over21CardArea());
        final Gambler 코다 = 코다(equal21CardAreaNonBlackJack());
        final Dealer dealer = new Dealer(equal20CardArea());

        final BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 콩떡, 코다), dealer, cardDeck);

        // when
        final Map<Participant, Revenue> revenue = blackJackGame.revenue();

        // then
        assertThat(revenue).contains(
                entry(말랑, 말랑.bettingMoney().revenue(DRAW)),
                entry(콩떡, 콩떡.bettingMoney().revenue(LOSE)),
                entry(코다, 코다.bettingMoney().revenue(WIN))
        );
    }

    @Nested
    @DisplayName("hitForDealerWhenShouldMoreHit() 테스트")
    class HitForDealerWhenShouldMoreHitTest {

        @Test
        void 딜러의_점수가_16점_이하가_아니라면_Hit_을_하고_true_를_반환한다() {
            // given
            final Dealer dealer = new Dealer(under16CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(코다(under21CardArea())), dealer, cardDeck);
            final BlackJackScore before = dealer.score();

            // when
            final boolean result = blackJackGame.hitForDealerWhenShouldMoreHit();

            // then
            assertThat(dealer.score()).isNotEqualTo(before);
            assertThat(result).isTrue();
        }

        @Test
        void 딜러의_점수가_16점_초과라면_Hit_을_하지_않고_false_를_반환한다() {
            // given
            final Dealer dealer = new Dealer(over16CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(코다(under21CardArea())), dealer, cardDeck);
            final BlackJackScore before = dealer.score();

            // when
            final boolean result = blackJackGame.hitForDealerWhenShouldMoreHit();

            // then
            assertThat(dealer.score()).isEqualTo(before);
            assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("existCanHitParticipant() 테스트")
    class ExistCanHitGamblerTest {

        @Test
        void 참가자들_중_21_미만인_참가자가_있음에도_그들이_Hit_을_원하지_않는다면_false를_반환한다() {
            // given
            final Gambler 말랑 = 말랑(under21CardArea());
            final Gambler 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), cardDeck);
            blackJackGame.gamblers().forEach(it -> it.changeState(HitState.STAY));

            // when & then
            assertThat(blackJackGame.existCanHitGambler()).isFalse();
        }

        @Test
        void 참가자들_중_21_미만인_참가자가_없는_경우_false_를_반환한다() {
            // given
            final Gambler 말랑 = 말랑(over21CardArea());
            final Gambler 코다 = 코다(over21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), cardDeck);

            // when & then
            assertThat(blackJackGame.existCanHitGambler()).isFalse();
        }

        @Test
        void 참가자들_중_21_미만인_참가자가_있으며_Hit_을_원하는_경우_true_를_반환한다() {
            // given
            final Gambler 말랑 = 말랑(under21CardArea());
            final Gambler 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), cardDeck);
            말랑.changeState(HitState.HIT);

            // when & then
            assertThat(blackJackGame.existCanHitGambler()).isTrue();
        }

        @Test
        void 참가자들_중_21_미만인_참가자가_있으며_Hit_여부를_경정하지_않은_경우_true_를_반환한다() {
            // given
            final Gambler 말랑 = 말랑(under21CardArea());
            final Gambler 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), cardDeck);

            // when & then
            assertThat(blackJackGame.existCanHitGambler()).isTrue();
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
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), cardDeck);

            // when
            Gambler canHit = blackJackGame.findCanHitGambler();

            // then
            assertThat(canHit).isEqualTo(코다);
        }

        @Test
        void Hit_이_가능한_참여자가_없는_경우_예외를_발생한다() {
            // given
            final Gambler 말랑 = 말랑(over21CardArea());
            final Gambler 코다 = 코다(over21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), cardDeck);

            // when & then
            assertThatThrownBy(blackJackGame::findCanHitGambler)
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
            BlackJackGame blackJackGame = new BlackJackGame(List.of(코다), new Dealer(under21CardArea()), cardDeck);
            final BlackJackScore before = 코다.score();

            // when
            blackJackGame.hitOrStayForGambler(코다, HitState.HIT);

            // then
            assertThat(코다.score()).isNotEqualTo(before);
        }

        @Test
        void 주어진_참가자가_Hit_을_원하지_않는다면_카드를_제공하지_않는다() {
            // given
            final Gambler 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(코다), new Dealer(under21CardArea()), cardDeck);
            final BlackJackScore before = 코다.score();

            // when
            blackJackGame.hitOrStayForGambler(코다, HitState.STAY);

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
            BlackJackGame blackJackGame1 = new BlackJackGame(List.of(코다), new Dealer(under16CardArea()), cardDeck);
            BlackJackGame blackJackGame2 = new BlackJackGame(List.of(코다), new Dealer(equal16CardArea()), cardDeck);

            // when & then
            assertThat(blackJackGame1.hitForDealerWhenShouldMoreHit()).isTrue();
            assertThat(blackJackGame2.hitForDealerWhenShouldMoreHit()).isTrue();
        }

        @Test
        void 딜러의_카드가_16_점_초과이면_항상_Hit_을_더_하지_않는다() {
            // given
            final Gambler 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(코다), new Dealer(over16CardArea()), cardDeck);

            // when & then
            assertThat(blackJackGame.hitForDealerWhenShouldMoreHit()).isFalse();
        }
    }
}
