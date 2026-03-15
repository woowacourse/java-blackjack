package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.BetAmount;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.PlayerName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackJudgeTest {

    private final BlackjackJudge blackjackJudge = new BlackjackJudge();

    @Nested
    class JudgePlayerResultTest {

        @Test
        void 플레이어가_버스트면_패배를_반환한다() {
            // given
            Dealer dealer = dealer(
                    card(Rank.TEN, Suit.HEART),
                    card(Rank.SIX, Suit.SPADE)
            );
            Player player = player(
                    card(Rank.K, Suit.CLOVER),
                    card(Rank.NINE, Suit.DIAMOND),
                    card(Rank.FIVE, Suit.HEART)
            );

            // when
            Result actual = blackjackJudge.judgePlayerResult(dealer, player);

            // then
            assertThat(actual).isEqualTo(Result.LOSE);
        }

        @Test
        void 플레이어와_딜러가_모두_블랙잭이면_무승부를_반환한다() {
            // given
            Dealer dealer = dealer(
                    card(Rank.ACE, Suit.HEART),
                    card(Rank.K, Suit.SPADE)
            );
            Player player = player(
                    card(Rank.ACE, Suit.CLOVER),
                    card(Rank.Q, Suit.DIAMOND)
            );

            // when
            Result actual = blackjackJudge.judgePlayerResult(dealer, player);

            // then
            assertThat(actual).isEqualTo(Result.DRAW);
        }

        @Test
        void 플레이어만_블랙잭이면_블랙잭_승리를_반환한다() {
            // given
            Dealer dealer = dealer(
                    card(Rank.TEN, Suit.HEART),
                    card(Rank.NINE, Suit.SPADE)
            );
            Player player = player(
                    card(Rank.ACE, Suit.CLOVER),
                    card(Rank.K, Suit.DIAMOND)
            );

            // when
            Result actual = blackjackJudge.judgePlayerResult(dealer, player);

            // then
            assertThat(actual).isEqualTo(Result.BLACKJACK_WIN);
        }

        @Test
        void 플레이어가_블랙잭이고_딜러가_버스트여도_블랙잭_승리를_반환한다() {
            // given
            Dealer dealer = dealer(
                    card(Rank.K, Suit.HEART),
                    card(Rank.NINE, Suit.SPADE),
                    card(Rank.FIVE, Suit.DIAMOND)
            );
            Player player = player(
                    card(Rank.ACE, Suit.CLOVER),
                    card(Rank.Q, Suit.HEART)
            );

            // when
            Result actual = blackjackJudge.judgePlayerResult(dealer, player);

            // then
            assertThat(actual).isEqualTo(Result.BLACKJACK_WIN);
        }

        @Test
        void 딜러가_버스트면_승리를_반환한다() {
            // given
            Dealer dealer = dealer(
                    card(Rank.K, Suit.HEART),
                    card(Rank.NINE, Suit.SPADE),
                    card(Rank.THREE, Suit.DIAMOND)
            );
            Player player = player(
                    card(Rank.TEN, Suit.CLOVER),
                    card(Rank.SEVEN, Suit.HEART)
            );

            // when
            Result actual = blackjackJudge.judgePlayerResult(dealer, player);

            // then
            assertThat(actual).isEqualTo(Result.WIN);
        }

        @Test
        void 플레이어_점수가_딜러보다_크면_승리를_반환한다() {
            // given
            Dealer dealer = dealer(
                    card(Rank.TEN, Suit.HEART),
                    card(Rank.EIGHT, Suit.SPADE)
            );
            Player player = player(
                    card(Rank.TEN, Suit.CLOVER),
                    card(Rank.NINE, Suit.DIAMOND)
            );

            // when
            Result actual = blackjackJudge.judgePlayerResult(dealer, player);

            // then
            assertThat(actual).isEqualTo(Result.WIN);
        }

        @Test
        void 플레이어_점수가_딜러와_같으면_무승부를_반환한다() {
            // given
            Dealer dealer = dealer(
                    card(Rank.TEN, Suit.HEART),
                    card(Rank.EIGHT, Suit.SPADE)
            );
            Player player = player(
                    card(Rank.NINE, Suit.CLOVER),
                    card(Rank.NINE, Suit.DIAMOND)
            );

            // when
            Result actual = blackjackJudge.judgePlayerResult(dealer, player);

            // then
            assertThat(actual).isEqualTo(Result.DRAW);
        }

        @Test
        void 모든_우선_조건에_해당하지_않으면_패배를_반환한다() {
            // given
            Dealer dealer = dealer(
                    card(Rank.TEN, Suit.HEART),
                    card(Rank.NINE, Suit.SPADE)
            );
            Player player = player(
                    card(Rank.NINE, Suit.CLOVER),
                    card(Rank.EIGHT, Suit.DIAMOND)
            );

            // when
            Result actual = blackjackJudge.judgePlayerResult(dealer, player);

            // then
            assertThat(actual).isEqualTo(Result.LOSE);
        }
    }

    private static Dealer dealer(Card... cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.addCard(card);
        }
        return dealer;
    }

    private static Player player(Card... cards) {
        Player player = new Player(new PlayerName("jacob"), new BetAmount("1000"));
        for (Card card : cards) {
            player.addCard(card);
        }
        return player;
    }

    private static Card card(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }
}
