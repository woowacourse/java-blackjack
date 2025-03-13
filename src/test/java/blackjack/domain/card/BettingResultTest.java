package blackjack.domain.card;

import static blackjack.domain.GameResult.getMultiplyRatio;
import static blackjack.fixture.CardFixture.CLUB_ONE;
import static blackjack.fixture.CardFixture.DIAMOND_ACE;
import static blackjack.fixture.CardFixture.DIAMOND_FIVE;
import static blackjack.fixture.CardFixture.DIAMOND_FOUR;
import static blackjack.fixture.CardFixture.DIAMOND_NINE;
import static blackjack.fixture.CardFixture.DIAMOND_ONE;
import static blackjack.fixture.CardFixture.DIAMOND_TEN;
import static blackjack.fixture.CardFixture.DIAMOND_THREE;
import static blackjack.fixture.CardFixture.HEART_ONE;
import static blackjack.fixture.CardFixture.SPADE_ONE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BettingResultTest {
    @Test
    void 플레이어의_최종수익률을_계산할_수_있다() {
        //given
        Player pobi = new Player("pobi",
                new Cards(
                        DIAMOND_THREE,
                        DIAMOND_TEN
                ), 10000);
        Player surf = new Player("surf",
                new Cards(
                        DIAMOND_THREE,
                        DIAMOND_TEN,
                        DIAMOND_FIVE,
                        DIAMOND_THREE
                ), 10000);
        Players players = new Players(List.of(pobi, surf));
        Stack<Card> cards = new Stack<>();
        cards.addAll(List.of(
                DIAMOND_FOUR,
                DIAMOND_FIVE,
                DIAMOND_ONE,
                CLUB_ONE,
                HEART_ONE,
                SPADE_ONE
        ));
        Dealer dealer = new Dealer(players, new Deck(cards), new Cards(DIAMOND_ACE, DIAMOND_NINE));

        Map<Player, Integer> playerBettingResults =
                Map.of(pobi, 1, surf, -1);
        int dealerBettingResult = 0;

        //when
//        BettingResult bettingResult = create(dealer, players);
//
//        //then
//        assertThat(bettingResult.getPlayerGameResults()).isEqualTo(playerBettingResults);
////        assertThat(bettingResult.getDealerGameResults()).isEqualTo(dealerBettingResult);
    }

    @Nested
    class 블랙잭_없는_경우_점수에_따라_베팅금액을_계산할_수_있다 {
        @Test
        void 플레이어_점수가_21을_초과하는_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(22, 3);
            BlackjackScore dealerScore = new BlackjackScore(20, 3);

            //when
            int multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(-1);
        }

        @Test
        void 플레이어_점수가_21을_초과하지_않고_딜러가_21_초과하는_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(20, 3);
            BlackjackScore dealerScore = new BlackjackScore(25, 3);

            //when
            int multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(1);
        }

        @Test
        void 플레이어와_딜러가_모두_21을_초과하지_않고_플레이어가_점수가_높은_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(20, 3);
            BlackjackScore dealerScore = new BlackjackScore(18, 3);

            //when
            int multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(1);
        }

        @Test
        void 플레이어와_딜러가_모두_21을_초과하지_않고_플레이어가_점수가_낮은_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(17, 3);
            BlackjackScore dealerScore = new BlackjackScore(18, 3);

            //when
            int multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(-1);
        }

        @Test
        void 플레이어_점수가_21을_초과하지_않고_딜러가_21인_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(20, 3);
            BlackjackScore dealerScore = new BlackjackScore(21, 3);

            //when
            int multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(-1);
        }

        @Test
        void 플레이어_점수가_21이고_딜러가_21_초과인_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(21, 3);
            BlackjackScore dealerScore = new BlackjackScore(24, 3);

            //when
            int multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(1);
        }

        @Test
        void 플레이어_점수가_21이고_딜러가_21_미만인_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(21, 3);
            BlackjackScore dealerScore = new BlackjackScore(20, 3);

            //when
            int multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(1);
        }

        @Test
        void 플레이어와_딜러가_점수가_같은_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(21, 4);
            BlackjackScore dealerScore = new BlackjackScore(21, 3);

            //when
            int multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(0);
        }
    }

    @Nested
    class 블랙잭_포함된_경우_점수에_따라_베팅금액을_계산할_수_있다 {
        @Test
        void 플레이어가_블랙잭이고_딜러가_블랙잭이_아닌_21인_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(21, 2);
            BlackjackScore dealerScore = new BlackjackScore(21, 3);

            //when
            int multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(1);
        }
    }
}
