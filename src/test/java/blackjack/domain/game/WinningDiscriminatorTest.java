package blackjack.domain.game;

import static blackjack.domain.card.CardType.ACE;
import static blackjack.domain.card.CardType.EIGHT;
import static blackjack.domain.card.CardType.FOUR;
import static blackjack.domain.card.CardType.KING;
import static blackjack.domain.card.CardType.NINE;
import static blackjack.domain.card.CardType.TEN;
import static blackjack.domain.card.CardType.THREE;
import static blackjack.domain.card.CardType.TWO;
import static blackjack.domain.fixture.GamblerFixture.createDealerWithCards;
import static blackjack.domain.fixture.GamblerFixture.createPlayerWithCards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Name;
import blackjack.domain.gambler.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class WinningDiscriminatorTest {
    private Name name = new Name("레오");

    @DisplayName("플레이어의_승패를_반환한다")
    @Nested
    class judgePlayerResult {
        @DisplayName("플레이어만_블랙잭일_경우_블랙잭_승리를_반환한다")
        @Test
        void judgeBlackjackWin() {
            // given
            Dealer dealer = createDealerWithCards(TEN, NINE);
            Player player = createPlayerWithCards(name, TEN, ACE);
            WinningDiscriminator winningDiscriminator = new WinningDiscriminator();

            // when
            WinningType result = winningDiscriminator.judgePlayerResult(dealer, player);

            // then
            assertThat(result).isEqualByComparingTo(WinningType.BLACKJACK_WIN);
        }

        @DisplayName("플레이어와_딜러_모두_블랙잭이면_무승부를_반환한다")
        @Test
        void judgeBlackjackDraw() {
            // given
            Dealer dealer = createDealerWithCards(KING, ACE);
            Player player = createPlayerWithCards(name, TEN, ACE);
            WinningDiscriminator winningDiscriminator = new WinningDiscriminator();

            // when
            WinningType result = winningDiscriminator.judgePlayerResult(dealer, player);

            // then
            assertThat(result).isEqualByComparingTo(WinningType.DRAW);
        }

        @DisplayName("플레이어가_딜러보다_높은_점수를_가지면_승리를_반환한다")
        @Test
        void judgeWin() {
            // given
            Dealer dealer = createDealerWithCards(TEN, NINE);
            Player player = createPlayerWithCards(name, NINE, KING, TWO);
            WinningDiscriminator winningDiscriminator = new WinningDiscriminator();

            // when
            WinningType result = winningDiscriminator.judgePlayerResult(dealer, player);

            // then
            assertThat(result).isEqualByComparingTo(WinningType.WIN);
        }

        @DisplayName("딜러만_버스트_되면_승리를_반환한다")
        @Test
        void judgeWinWhenDealerBust() {
            // given
            Dealer dealer = createDealerWithCards(TEN, NINE, THREE);
            Player player = createPlayerWithCards(name, NINE, TWO);
            WinningDiscriminator winningDiscriminator = new WinningDiscriminator();

            // when
            WinningType result = winningDiscriminator.judgePlayerResult(dealer, player);

            // then
            assertThat(result).isEqualByComparingTo(WinningType.WIN);
        }
        
        @DisplayName("플레이어와_딜러의_점수가_같으면_무승부를_반환한다")
        @Test
        void judgeDraw() {
            // given
            Dealer dealer = createDealerWithCards(TEN, EIGHT);
            Player player = createPlayerWithCards(name, NINE, NINE);
            WinningDiscriminator winningDiscriminator = new WinningDiscriminator();

            // when
            WinningType result = winningDiscriminator.judgePlayerResult(dealer, player);

            // then
            assertThat(result).isEqualByComparingTo(WinningType.DRAW);
        }
        
        @DisplayName("플레이어가_딜러보다_낮은_점수를_가지면_패배를_반환한다")
        @Test
        void judgeDefeat() {
            // given
            Dealer dealer = createDealerWithCards(TEN, EIGHT);
            Player player = createPlayerWithCards(name, NINE, FOUR);
            WinningDiscriminator winningDiscriminator = new WinningDiscriminator();

            // when
            WinningType result = winningDiscriminator.judgePlayerResult(dealer, player);

            // then
            assertThat(result).isEqualByComparingTo(WinningType.DEFEAT);
        }

        @DisplayName("플레이어만_버스트되면_패배를_반환한다")
        @Test
        void judgeDefeatWhenPlayerBust() {
            // given
            Dealer dealer = createDealerWithCards(TEN, EIGHT);
            Player player = createPlayerWithCards(name, NINE, NINE, FOUR);
            WinningDiscriminator winningDiscriminator = new WinningDiscriminator();

            // when
            WinningType result = winningDiscriminator.judgePlayerResult(dealer, player);

            // then
            assertThat(result).isEqualByComparingTo(WinningType.DEFEAT);
        }

        @DisplayName("플레이어와_딜러_모두_버스트되면_패배를_반환한다")
        @Test
        void judgeDefeatWhenPlayerAndDealerBust() {
            // given
            Dealer dealer = createDealerWithCards(NINE, NINE, FOUR);
            Player player = createPlayerWithCards(name, NINE, NINE, FOUR);
            WinningDiscriminator winningDiscriminator = new WinningDiscriminator();

            // when
            WinningType result = winningDiscriminator.judgePlayerResult(dealer, player);

            // then
            assertThat(result).isEqualByComparingTo(WinningType.DEFEAT);
        }
    }
}
