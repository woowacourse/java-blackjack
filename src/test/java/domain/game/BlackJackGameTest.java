package domain.game;

import domain.deck.CardDeck;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.Participant;
import domain.player.State;
import org.junit.jupiter.api.*;

import java.util.List;

import static domain.fixture.CardAreaFixture.*;
import static domain.fixture.NameFixture.말랑이름;
import static domain.fixture.NameFixture.코다이름;
import static domain.fixture.ParticipantFixture.말랑;
import static domain.fixture.ParticipantFixture.코다;
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
        blackJackGame.participants().forEach(it -> assertThat(it.cardArea().cards().size()).isEqualTo(2));
        assertThat(blackJackGame.dealer().cardArea().cards().size()).isEqualTo(2);
    }

    @Nested
    @DisplayName("existCanHitParticipant() 테스트")
    class ExistCanHitParticipantTest {

        @Test
        void 참가자들_중_21_미만인_참가자가_있음에도_그들이_hit_을_원하지_않는다면_false를_반환한다() {
            // given
            final Participant 말랑 = 말랑(under21CardArea());
            final Participant 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());
            blackJackGame.participants().forEach(it -> it.changeState(State.STAY));

            // when & then
            assertThat(blackJackGame.existCanHitParticipant()).isFalse();
        }

        @Test
        void 참가자들_중_21_미만인_참가자가_없는_경우_false_를_반환한다() {
            // given
            final Participant 말랑 = 말랑(over21CardArea());
            final Participant 코다 = 코다(over21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());

            // when & then
            assertThat(blackJackGame.existCanHitParticipant()).isFalse();
        }

        @Test
        void 참가자들_중_21_미만인_참가자가_있으며_HIT_을_원하는_경우_true_를_반환한다() {
            // given
            final Participant 말랑 = 말랑(under21CardArea());
            final Participant 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());
            말랑.changeState(State.HIT);

            // when & then
            assertThat(blackJackGame.existCanHitParticipant()).isTrue();
        }

        @Test
        void 참가자들_중_21_미만인_참가자가_있으며_HIT_여부를_경정하지_않은_경우_true_를_반환한다() {
            // given
            final Participant 말랑 = 말랑(under21CardArea());
            final Participant 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());

            // when & then
            assertThat(blackJackGame.existCanHitParticipant()).isTrue();
        }
    }

    @Nested
    @DisplayName("findCanHitParticipant() 테스트")
    class FindCanHitParticipantTest {

        @Test
        void Hit_이_가능한_임의의_참여자를_반환한다() {
            // given
            final Participant 말랑 = 말랑(over21CardArea());
            final Participant 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());

            // when
            Participant canHit = blackJackGame.findCanHitParticipant();

            // then
            assertThat(canHit).isEqualTo(코다);
        }

        @Test
        void Hit_이_가능한_참여자가_없는_경우_예외를_발생한다() {
            // given
            final Participant 말랑 = 말랑(over21CardArea());
            final Participant 코다 = 코다(over21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(말랑, 코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());

            // when & then
            assertThatThrownBy(blackJackGame::findCanHitParticipant)
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("hitOrStayForParticipant() 테스트")
    class HitOrStayForParticipantTest {

        @Test
        void 주어진_참가자가_Hit_을_원한다면_카드를_제공한다() {
            // given
            final Participant 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());
            코다.changeState(State.HIT);
            final int before = 코다.score();

            // when
            blackJackGame.hitOrStayForParticipant(코다);

            // then
            assertThat(코다.score()).isNotEqualTo(before);
        }

        @Test
        void 주어진_참가자가_Hit_을_원하지_않는다면_카드를_제공하지_않는다() {
            // given
            final Participant 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(코다), new Dealer(under21CardArea()), CardDeck.shuffledFullCardDeck());
            코다.changeState(State.STAY);
            final int before = 코다.score();

            // when
            blackJackGame.hitOrStayForParticipant(코다);

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
            final Participant 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame1 = new BlackJackGame(List.of(코다), new Dealer(under16CardArea()), CardDeck.shuffledFullCardDeck());
            BlackJackGame blackJackGame2 = new BlackJackGame(List.of(코다), new Dealer(equal16CardArea()), CardDeck.shuffledFullCardDeck());

            // when & then
            assertThat(blackJackGame1.isDealerShouldMoreHit()).isTrue();
            assertThat(blackJackGame2.isDealerShouldMoreHit()).isTrue();
        }

        @Test
        void 딜러의_카드가_16_점_초과이면_항상_Hit_을_더_하지_않는다() {
            // given
            final Participant 코다 = 코다(under21CardArea());
            BlackJackGame blackJackGame = new BlackJackGame(List.of(코다), new Dealer(over16CardArea()), CardDeck.shuffledFullCardDeck());

            // when & then
            assertThat(blackJackGame.isDealerShouldMoreHit()).isFalse();
        }
    }

    @Test
    void hitForDealer_시_딜러가_hit_한다() {
        // given
        final Dealer dealer = new Dealer(under16CardArea());
        BlackJackGame blackJackGame = new BlackJackGame(List.of(코다(under21CardArea())), dealer, CardDeck.shuffledFullCardDeck());
        final int before = dealer.score();

        // when
        blackJackGame.hitForDealer();

        // then
        assertThat(dealer.score()).isNotEqualTo(before);
    }
}