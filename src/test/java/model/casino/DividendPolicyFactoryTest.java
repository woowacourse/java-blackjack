package model.casino;

import static org.assertj.core.api.Assertions.assertThat;

import model.card.Card;
import model.card.Emblem;
import model.card.Number;
import model.money.DividendPolicy;
import model.participant.Dealer;
import model.participant.Name;
import model.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DividendPolicyFactoryTest {

    @Nested
    @DisplayName("게임 진행 중에 미리 판별되는 승패에 따른 배당정책을 만든다.")
    class findPolicyInProceed_ShouldGenerateDividendPolicy_WhenGameProceed {

        @Test
        @DisplayName("플레이어는 초기 뽑은 두 장의 카드가 블랙잭일 경우 DividendPolicy.BLACKJACK을 반환한다.")
        void findPolicy_ShouldGenerateInitBlackjackPolicy_WhenPlayerHasInitBlackjack() {
            Dealer dealer = new Dealer();

            Player player = new Player(new Name("프람"));
            player.hitCard(Card.from(Number.ACE, Emblem.HEART));
            player.hitCard(Card.from(Number.TEN, Emblem.CLUB));

            assertThat(DividendPolicyFactory.findPolicy(dealer, player))
                    .isSameAs(DividendPolicy.INIT_BLACKJACK);
        }


        @Test
        @DisplayName("플레이어가 게임 중 버스트 된다면 DividendPolicy.NORMAL_LOSE를 반환한다.")
        void findPolicy_ShouldGenerateNormalLosePolicy_WhenPlayerIsBust() {
            Dealer dealer = new Dealer();
            Player player = new Player(new Name("프람"));
            player.hitCard(Card.from(Number.TEN, Emblem.HEART));
            player.hitCard(Card.from(Number.TEN, Emblem.CLUB));
            player.hitCard(Card.from(Number.THREE, Emblem.DIAMOND));

            assertThat(DividendPolicyFactory.findPolicy(dealer, player))
                    .isSameAs(DividendPolicy.LOSE);
        }


        @Nested
        @DisplayName("게임 진행 종료 후에 판별되는 승패에 따른 배당정책을 만든다.")
        class findPolicy_ShouldGenerateDividendPolicy_WhenAfterGameProceed {
            @Test
            @DisplayName("딜러와 플레이어가 모두 버스트가 아닌 경우에서,플레이어 핸드 > 딜러 핸드일 경우 플레이어 NORMAL_WIN")
            void findPolicyAfterProceed_ShouldGenerateDividendPolicy_WhenBothNotBustAndPlayerGreaterThenDealer() {
                Dealer dealer = new Dealer();
                dealer.hitCard(Card.from(Number.TEN, Emblem.DIAMOND));
                dealer.hitCard(Card.from(Number.TWO, Emblem.DIAMOND));

                Player player = new Player(new Name("프람"));
                player.hitCard(Card.from(Number.TEN, Emblem.HEART));
                player.hitCard(Card.from(Number.THREE, Emblem.DIAMOND));

                assertThat(DividendPolicyFactory.findPolicy(dealer, player))
                        .isSameAs(DividendPolicy.WIN);
            }

            @Test
            @DisplayName("딜러와 플레이어가 모두 버스트가 아닌 경우에서,플레이어 핸드 < 딜러 핸드일 경우 플레이어 NORMAL_LOSE")
            void findPolicy_ShouldGenerateDividendPolicy_WhenBothNotBustAndPlayerLessThenDealer() {
                Dealer dealer = new Dealer();
                dealer.hitCard(Card.from(Number.TEN, Emblem.DIAMOND));
                dealer.hitCard(Card.from(Number.THREE, Emblem.DIAMOND));

                Player player = new Player(new Name("프람"));
                player.hitCard(Card.from(Number.TEN, Emblem.HEART));
                player.hitCard(Card.from(Number.TWO, Emblem.DIAMOND));

                assertThat(DividendPolicyFactory.findPolicy(dealer, player))
                        .isSameAs(DividendPolicy.LOSE);
            }

            @Test
            @DisplayName("딜러와 플레이어가 모두 버스트가 아닌 경우에서,플레이어 핸드 = 딜러 핸드일 경우 플레이어 NORMAL_WIN")
            void findPolicy_ShouldGenerateDividendPolicy_WhenBothNotBustAndPlayerEqualThenDealer() {
                Dealer dealer = new Dealer();
                dealer.hitCard(Card.from(Number.TEN, Emblem.DIAMOND));
                dealer.hitCard(Card.from(Number.THREE, Emblem.DIAMOND));

                Player player = new Player(new Name("프람"));
                player.hitCard(Card.from(Number.TEN, Emblem.HEART));
                player.hitCard(Card.from(Number.THREE, Emblem.DIAMOND));

                assertThat(DividendPolicyFactory.findPolicy(dealer, player))
                        .isSameAs(DividendPolicy.DRAW);
            }

            @Test
            @DisplayName("딜러는 버스트이고 플레이어는 아닐 경우, DividendPolicy.NORMAL_WIN을 반환한다.")
            void findPolicy_ShouldGenerateDividendPolicy_WhenDealerBustPayerNotBust() {
                Dealer dealer = new Dealer();
                dealer.hitCard(Card.from(Number.TEN, Emblem.DIAMOND));
                dealer.hitCard(Card.from(Number.TEN, Emblem.DIAMOND));
                dealer.hitCard(Card.from(Number.THREE, Emblem.CLUB));

                Player player = new Player(new Name("프람"));
                player.hitCard(Card.from(Number.TEN, Emblem.HEART));
                player.hitCard(Card.from(Number.THREE, Emblem.DIAMOND));

                assertThat(DividendPolicyFactory.findPolicy(dealer, player))
                        .isSameAs(DividendPolicy.WIN);
            }

            @Test
            @DisplayName("플레이어의 경우 초기 블랙잭이 아닌 블랙잭일 경우 그리고 딜러 역시 블랙잭일 경우 Dividend.NORMAL_DRAW를 반환한다.")
            void findPolicy_ShouldGenerateDividendPolicy_WhenBothBlackjackExceptInitBlackjack() {
                Dealer dealer = new Dealer();
                dealer.hitCard(Card.from(Number.TEN, Emblem.DIAMOND));
                dealer.hitCard(Card.from(Number.ACE, Emblem.DIAMOND));

                Player player = new Player(new Name("프람"));
                player.hitCard(Card.from(Number.TEN, Emblem.HEART));
                player.hitCard(Card.from(Number.THREE, Emblem.DIAMOND));
                player.hitCard(Card.from(Number.EIGHT, Emblem.CLUB));

                assertThat(DividendPolicyFactory.findPolicy(dealer, player))
                        .isSameAs(DividendPolicy.DRAW);
            }
        }

    }
}
