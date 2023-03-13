package blackjack.domain.card;

import static blackjack.util.CardFixture.ACE_CLOVER;
import static blackjack.util.CardFixture.EIGHT_SPADE;
import static blackjack.util.CardFixture.FIVE_SPADE;
import static blackjack.util.CardFixture.FOUR_SPADE;
import static blackjack.util.CardFixture.JACK_CLOVER;
import static blackjack.util.CardFixture.KING_HEART;
import static blackjack.util.CardFixture.KING_SPADE;
import static blackjack.util.CardFixture.NINE_CLOVER;
import static blackjack.util.CardFixture.QUEEN_SPADE;
import static blackjack.util.CardFixture.SEVEN_SPADE;
import static blackjack.util.CardFixture.SIX_SPADE;
import static blackjack.util.CardFixture.THREE_SPADE;
import static blackjack.util.CardFixture.TWO_HEART;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class HandTest {

    @Test
    void 점수를_반환한다() {
        final Hand hand = new Hit()
                .draw(ACE_CLOVER)
                .draw(TWO_HEART);

        assertThat(hand.score()).isEqualTo(13);
    }

    @Test
    void 상태가_Running_인_경우_카드를_더_뽑을_수_있다() {
        final Running running = new Running(new Cards()) {
            @Override
            public Hand draw(final Card card) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Hand stay() {
                throw new UnsupportedOperationException();
            }
        };

        assertThat(running.isDrawable()).isTrue();
    }

    @Test
    void 상태가_Finished_인_경우_카드를_더_뽑을_수_있다() {
        final Finished finished = new Finished(new Cards()) {
            @Override
            public Result play(final Hand other) {
                throw new UnsupportedOperationException();
            }
        };

        assertThat(finished.isDrawable()).isFalse();
    }

    @Nested
    class 겜블러가_블랙잭인_경우 {

        private Hand gambler = new Hit()
                .draw(ACE_CLOVER)
                .draw(KING_HEART);

        @Test
        void 딜러가_블랙잭이라면_비긴다() {
            Hand dealer = new Hit()
                    .draw(ACE_CLOVER)
                    .draw(KING_HEART);

            final Result result = gambler.play(dealer);

            assertThat(result).isEqualTo(Result.PUSH);
        }

        @Test
        void 딜러가_블랙잭이_아닌_경우_이긴다() {
            Hand dealer = new Hit()
                    .draw(JACK_CLOVER)
                    .draw(QUEEN_SPADE);

            final Result result = gambler.play(dealer);

            assertThat(result).isEqualTo(Result.BLACKJACK_WIN);
        }
    }

    @Nested
    class 겜블러가_버스트인_경우 {

        private Hand gambler = new Hit()
                .draw(KING_SPADE)
                .draw(NINE_CLOVER)
                .draw(THREE_SPADE);

        @Test
        void 딜러가_어떤_핸드를_가지던_패배한다() {
            Hand dealer = new Hit()
                    .draw(TWO_HEART);

            final Result result = gambler.play(dealer);

            assertThat(result).isEqualTo(Result.LOSE);
        }
    }

    @Nested
    class 겜블러가_핸드가_STAY_인_경우 {

        private Hand gambler = new Hit()
                .draw(JACK_CLOVER)
                .draw(NINE_CLOVER)
                .stay();

        @Test
        void 딜러가_블랙잭이라면_패배한다() {
            Hand dealer = new Hit()
                    .draw(ACE_CLOVER)
                    .draw(KING_HEART);

            final Result result = gambler.play(dealer);

            assertThat(result).isEqualTo(Result.LOSE);
        }

        @Test
        void 딜러가_점수가_높을_경우_패배한다() {
            Hand dealer = new Hit()
                    .draw(JACK_CLOVER)
                    .draw(QUEEN_SPADE)
                    .stay();

            final Result result = gambler.play(dealer);

            assertThat(result).isEqualTo(Result.LOSE);
        }

        @Test
        void 딜러와_점수가_같을_경우_비긴다() {
            Hand dealer = new Hit()
                    .draw(SEVEN_SPADE)
                    .draw(FOUR_SPADE)
                    .draw(EIGHT_SPADE)
                    .stay();

            final Result result = gambler.play(dealer);

            assertThat(result).isEqualTo(Result.PUSH);
        }

        @Test
        void 딜러보다_점수가_높을_경우_이긴다() {
            Hand dealer = new Hit()
                    .draw(SEVEN_SPADE)
                    .draw(FIVE_SPADE)
                    .draw(SIX_SPADE)
                    .stay();

            final Result result = gambler.play(dealer);

            assertThat(result).isEqualTo(Result.WIN);
        }
    }
}
