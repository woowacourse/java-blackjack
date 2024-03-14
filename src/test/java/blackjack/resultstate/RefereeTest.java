package blackjack.resultstate;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import blackjack.player.Dealer;
import blackjack.player.Hand;
import blackjack.player.Participant;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RefereeTest {

    private Participant createParticipant(List<Card> cards) {
        return new Participant("aru", new Hand(cards));
    }

    private Dealer createDealer(List<Card> cards) {
        return new Dealer(new Hand(cards));
    }

    private List<Card> blackJackCards() {
        return List.of(
                Card.of(Shape.DIAMOND, Number.ACE),
                Card.of(Shape.HEART, Number.KING)
        );
    }

    private List<Card> bustCards() {
        return List.of(
                Card.of(Shape.DIAMOND, Number.KING),
                Card.of(Shape.HEART, Number.QUEEN),
                Card.of(Shape.SPADE, Number.JACK)
        );
    }

    private List<Card> standLowerCards() {
        return List.of(
                Card.of(Shape.DIAMOND, Number.TEN),
                Card.of(Shape.HEART, Number.SEVEN)
        );
    }

    private List<Card> standHigherCards() {
        return List.of(
                Card.of(Shape.DIAMOND, Number.TEN),
                Card.of(Shape.HEART, Number.EIGHT)
        );
    }

    @Nested
    @DisplayName("플레이어가 블랙잭일 때")
    class PlayerBlackJack {

        Participant participant = createParticipant(blackJackCards());

        @Test
        @DisplayName("딜러가 블랙잭이라면 무승부이다.")
        void drawOnDealerBlackJack() {
            // given
            Dealer dealer = createDealer(blackJackCards());
            // when
            MatchResult result = Referee.judge(participant, dealer);
            // then
            assertThat(result).isEqualTo(MatchResult.TIE);
        }

        @Test
        @DisplayName("딜러가 버스트라면 플레이어의 블랙잭 승리이다.")
        void blackJackWinOnDealerBust() {
            // given
            Dealer dealer = createDealer(bustCards());
            // when
            MatchResult result = Referee.judge(participant, dealer);
            // then
            assertThat(result).isEqualTo(MatchResult.PARTICIPANT_BLACKJACK_WIN);
        }

        @Test
        @DisplayName("딜러가 스탠드라면 플레이어의 블랙잭 승리이다.")
        void blackJackWinOnDealerStand() {
            // given
            Dealer dealer = createDealer(standLowerCards());
            // when
            MatchResult result = Referee.judge(participant, dealer);
            // then
            assertThat(result).isEqualTo(MatchResult.PARTICIPANT_BLACKJACK_WIN);
        }
    }

    @Nested
    @DisplayName("플레이어가 버스트일 때")
    class PlayerBust {

        Participant participant = createParticipant(bustCards());

        @Test
        @DisplayName("딜러가 블랙잭이라면 플레이어의 패배이다.")
        void lostOnDealerBlackJack() {
            // given
            Dealer dealer = createDealer(blackJackCards());
            // when
            MatchResult result = Referee.judge(participant, dealer);
            // then
            assertThat(result).isEqualTo(MatchResult.DEALER_WIN);
        }

        @Test
        @DisplayName("딜러가 버스트라면 플레이어의 패배이다.")
        void lostOnDealerBust() {
            // given
            Dealer dealer = createDealer(bustCards());
            // when
            MatchResult result = Referee.judge(participant, dealer);
            // then
            assertThat(result).isEqualTo(MatchResult.DEALER_WIN);
        }

        @Test
        @DisplayName("딜러가 스탠드라면 플레이어의 패배이다.")
        void lostOnDealerStand() {
            // given
            Dealer dealer = createDealer(standLowerCards());
            // when
            MatchResult result = Referee.judge(participant, dealer);
            // then
            assertThat(result).isEqualTo(MatchResult.DEALER_WIN);
        }
    }

    @Nested
    @DisplayName("플레이어가 스탠드일 때")
    class PlayerStand {

        Participant lowerScoreParticipant = createParticipant(standLowerCards());
        Participant higherScoreParticipant = createParticipant(standHigherCards());

        @Test
        @DisplayName("딜러가 블랙잭이라면 플레이어의 패배이다.")
        void lostOnDealerBlackJack() {
            // given
            Dealer dealer = createDealer(blackJackCards());
            // when
            MatchResult result = Referee.judge(lowerScoreParticipant, dealer);
            // then
            assertThat(result).isEqualTo(MatchResult.DEALER_WIN);
        }

        @Test
        @DisplayName("딜러가 버스트라면 플레이어의 승리이다.")
        void winOnDealerBust() {
            // given
            Dealer dealer = createDealer(bustCards());
            // when
            MatchResult result = Referee.judge(lowerScoreParticipant, dealer);
            // then
            assertThat(result).isEqualTo(MatchResult.PARTICIPANT_WIN);
        }

        @Nested
        @DisplayName("딜러가 스탠드일 때")
        class DealerStand {

            Dealer lowerScoreDealer = createDealer(standLowerCards());
            Dealer higherScoreDealer = createDealer(standHigherCards());

            @Test
            @DisplayName("플레이어의 점수가 더 높다면 플레이어의 승리이다.")
            void winOnComparingScore() {
                // when
                MatchResult result = Referee.judge(higherScoreParticipant, lowerScoreDealer);
                // then
                assertThat(result).isEqualTo(MatchResult.PARTICIPANT_WIN);
            }

            @Test
            @DisplayName("딜러의 점수가 더 높다면 플레이어의 패배이다.")
            void loseOnComparingScore() {
                // when
                MatchResult result = Referee.judge(lowerScoreParticipant, higherScoreDealer);
                // then
                assertThat(result).isEqualTo(MatchResult.DEALER_WIN);
            }

            @Test
            @DisplayName("점수가 같다면 무승부이다.")
            void tieOnSameScore() {
                // when
                MatchResult result = Referee.judge(lowerScoreParticipant, lowerScoreDealer);
                // then
                assertThat(result).isEqualTo(MatchResult.TIE);
            }
        }
    }
}
