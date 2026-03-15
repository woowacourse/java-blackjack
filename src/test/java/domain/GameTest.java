package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.DealerBlackjackTestCardGenerator;
import domain.card.Deck;
import domain.card.DistributeTestCardGenerator;
import domain.card.ProfitTestCardGenerator;
import domain.enums.Rank;
import domain.enums.Suit;
import domain.participant.BetAmounts;
import domain.participant.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GameTest {
    @Nested
    @DisplayName("정상 경우")
    class success {
        private Game onePlayerGame;
        private Deck deck;
        private Map<String, Integer> profits;

        @DisplayName("게임 시작 후 모든 참가자가 2장의 카드를 분배 받는다.")
        @Test
        void 게임_시작_후_모든_플레이어_2장의_카드_분배를_받는다() {
            deck = new Deck(new DistributeTestCardGenerator());
            List<String> names = List.of("피즈", "스타크");
            Game twoPlayerGame = new Game(new Players(names), new BetAmounts(names), deck);

            assertThat(twoPlayerGame.getPlayerCards("피즈").size()).isEqualTo(2);
            assertThat(twoPlayerGame.getPlayerCards("스타크").size()).isEqualTo(2);
            assertThat(twoPlayerGame.getDealerCards().size()).isEqualTo(2);
        }

        @DisplayName("플레이어의 카드 총합이 21미만이고 히트 요청 시 한장을 더 분배한다.")
        @Test
        void 플레이어_카드_합_21_미만_히트_요청_시_한장_더_분배한다() {
            deck = new Deck(new DistributeTestCardGenerator());
            List<String> names = List.of("피즈");
            onePlayerGame = new Game(new Players(names), new BetAmounts(names), deck);

            onePlayerGame.playerHit("피즈", deck, false);

            assertThat(onePlayerGame.getPlayerCards("피즈").size()).isEqualTo(3);
        }

        @DisplayName("딜러의 카드 총합이 17미만이면 한장을 더 분배한다.")
        @Test
        void 딜러의_카드_총합이_17미만이면_한장을_더_분배한다() {
            deck = new Deck(new DistributeTestCardGenerator());
            List<String> names = List.of("피즈");
            onePlayerGame = new Game(new Players(names), new BetAmounts(names), deck);

            onePlayerGame.dealerHit(deck);

            assertThat(onePlayerGame.getDealerCards().size()).isEqualTo(3);
        }

        @DisplayName("플레이어와 딜러가 분배된 카드를 알맞게 가지고 있는지 확인한다.")
        @Test
        void 플레이어_딜러_카드_정상_분배_확인한다() {
            deck = new Deck(new DistributeTestCardGenerator());
            List<String> names = List.of("피즈");
            onePlayerGame = new Game(new Players(names), new BetAmounts(names), deck);

            List<Card> expectedFizzCards = List.of(new Card(Rank.FIVE, Suit.CLOVER), new Card(Rank.SIX, Suit.CLOVER),
                    new Card(Rank.FOUR, Suit.SPADE));
            onePlayerGame.playerHit("피즈", deck, false);

            List<Card> expectedDealerCards = List.of(new Card(Rank.FIVE, Suit.HEART), new Card(Rank.SEVEN, Suit.CLOVER),
                    new Card(Rank.SEVEN, Suit.HEART));
            onePlayerGame.dealerHit(deck);

            assertThat(onePlayerGame.getPlayerCards("피즈")).isEqualTo(expectedFizzCards);
            assertThat(onePlayerGame.getDealerCards()).isEqualTo(expectedDealerCards);
        }

        @DisplayName("딜러가 처음에 받은 카드 2장으로 바로 블랙잭이 되면 카드를 추가로 더 분배 받지 않는다.")
        @Test
        void 딜러_처음_2장_블랙잭_카드_추가_분배_받지_않는다() {
            deck = new Deck(new DealerBlackjackTestCardGenerator());
            List<String> names = List.of("피즈");
            onePlayerGame = new Game(new Players(names), new BetAmounts(names), deck);

            onePlayerGame.dealerHit(deck);
            onePlayerGame.dealerHit(deck);

            assertThat(onePlayerGame.calculateDealerScore()).isEqualTo(21);
            assertThat(onePlayerGame.getDealerCards().size()).isEqualTo(2);
        }

        @DisplayName("모든 참가자의 수익을 정확히 계산한다.")
        @Test
        void 모든_참가자의_수익을_정확히_계산한다() {
            List<String> fourPlayerNames = List.of("피즈", "이삭", "러키", "쿠다");
            BetAmounts betAmounts = new BetAmounts(fourPlayerNames);
            betAmounts.addBetAmount("피즈", 10000);
            betAmounts.addBetAmount("이삭", 20000);
            betAmounts.addBetAmount("러키", 10000);
            betAmounts.addBetAmount("쿠다", 15000);

            deck = new Deck(new ProfitTestCardGenerator());
            Game fourPlayerGame = new Game(new Players(fourPlayerNames), betAmounts, deck);
            profits = fourPlayerGame.calculatePlayerProfits();

            assertThat(profits.get("피즈")).isEqualTo(-10000);
            assertThat(profits.get("이삭")).isEqualTo(20000);
            assertThat(profits.get("러키")).isEqualTo(15000);
            assertThat(profits.get("쿠다")).isEqualTo(0);
            assertThat(fourPlayerGame.calculateDealerProfit()).isEqualTo(-25000);
        }
    }
}
