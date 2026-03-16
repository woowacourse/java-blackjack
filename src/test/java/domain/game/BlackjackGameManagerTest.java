package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardMachine;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.BetAmount;
import domain.participant.Participants;
import domain.participant.PlayerName;
import dto.BlackjackResultDto;
import dto.BlackjackStatisticsDto;
import dto.ParticipantDto;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackGameManagerTest {

    @Nested
    class DrawInitialCardsTest {

        @Test
        void 딜러와_모든_플레이어에게_초기_카드를_분배한다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                    card(Rank.ACE, Suit.CLOVER), card(Rank.K, Suit.DIAMOND),
                    card(Rank.NINE, Suit.HEART), card(Rank.SEVEN, Suit.CLOVER)
            ));

            // when
            blackjackGameManager.drawInitialCards();

            // then
            ParticipantDto dealerDto = blackjackGameManager.generateDealerDto();
            List<ParticipantDto> playerDtoList = blackjackGameManager.generatePlayerDtoList();

            assertThat(dealerDto.hand()).containsExactly("10하트", "6스페이드");
            assertThat(playerDtoList.get(0).hand()).containsExactly("A클로버", "K다이아몬드");
            assertThat(playerDtoList.get(1).hand()).containsExactly("9하트", "7클로버");
        }
    }

    @Nested
    class DrawDealerCardTest {

        @Test
        void 딜러_점수가_16_이하면_카드를_한장_추가한다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                    card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                    card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                    card(Rank.NINE, Suit.CLOVER)
            ));
            blackjackGameManager.drawInitialCards();

            // when
            boolean actual = blackjackGameManager.drawDealerCard();

            // then
            ParticipantDto dealerDto = blackjackGameManager.generateDealerDto();
            assertThat(actual).isTrue();
            assertThat(dealerDto.hand()).containsExactly("10하트", "6스페이드", "9클로버");
        }

        @Test
        void 딜러_점수가_17_이상이면_카드를_추가하지_않는다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SEVEN, Suit.SPADE),
                    card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                    card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                    card(Rank.NINE, Suit.CLOVER)
            ));
            blackjackGameManager.drawInitialCards();

            // when
            boolean actual = blackjackGameManager.drawDealerCard();

            // then
            ParticipantDto dealerDto = blackjackGameManager.generateDealerDto();
            assertThat(actual).isFalse();
            assertThat(dealerDto.hand()).containsExactly("10하트", "7스페이드");
        }
    }

    @Nested
    class UpdatePlayerTest {

        @Test
        void 플레이어에게_카드를_추가하고_업데이트된_Dto를_반환한다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                    card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                    card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                    card(Rank.ACE, Suit.CLOVER)
            ));
            blackjackGameManager.drawInitialCards();

            // when
            ParticipantDto actual = blackjackGameManager.updatePlayer("jacob");

            // then
            assertThat(actual.name()).isEqualTo("jacob");
            assertThat(actual.hand()).containsExactly("2클로버", "3다이아몬드", "A클로버");
            assertThat(actual.score()).isEqualTo(16);
        }
    }

    @Nested
    class GenerateInitialDealerDtoTest {

        @Test
        void 딜러의_초기_공개_Dto를_생성한다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                    card(Rank.ACE, Suit.CLOVER), card(Rank.K, Suit.DIAMOND),
                    card(Rank.NINE, Suit.HEART), card(Rank.SEVEN, Suit.CLOVER)
            ));
            blackjackGameManager.drawInitialCards();

            // when
            ParticipantDto actual = blackjackGameManager.generateInitialDealerDto();

            // then
            assertThat(actual.name()).isEqualTo("딜러");
            assertThat(actual.hand()).containsExactly("10하트");
            assertThat(actual.score()).isEqualTo(16);
        }
    }

    @Nested
    class GetBlackjackResultTest {

        @Test
        void 플레이어가_카드를_추가로_받으면_최종_결과에_반영된다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                    card(Rank.TEN, Suit.CLOVER), card(Rank.NINE, Suit.DIAMOND),
                    card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                    card(Rank.ACE, Suit.CLOVER)
            ));
            blackjackGameManager.drawInitialCards();
            blackjackGameManager.updatePlayer("jacob");

            // when
            BlackjackResultDto actual = blackjackGameManager.getBlackjackResult();

            // then
            assertThat(actual.dealerResultDto().hand()).containsExactly("10하트", "6스페이드");
            assertThat(actual.playerResultDtoList()).hasSize(2);
            assertThat(actual.playerResultDtoList().get(0).name()).isEqualTo("jacob");
            assertThat(actual.playerResultDtoList().get(0).hand()).containsExactly("10클로버", "9다이아몬드", "A클로버");
            assertThat(actual.playerResultDtoList().get(1).name()).isEqualTo("seoye");
            assertThat(actual.playerResultDtoList().get(1).hand()).containsExactly("4하트", "5스페이드");
        }
    }

    @Nested
    class GetBlackjackStatisticsTest {

        @Test
        void 플레이어가_버스트면_패배한다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SEVEN, Suit.SPADE),
                    card(Rank.EIGHT, Suit.CLOVER), card(Rank.EIGHT, Suit.DIAMOND),
                    card(Rank.TEN, Suit.CLOVER), card(Rank.EIGHT, Suit.DIAMOND),
                    card(Rank.K, Suit.HEART)
            ));
            blackjackGameManager.drawInitialCards();
            blackjackGameManager.updatePlayer("seoye");

            // when
            BlackjackStatisticsDto actual = blackjackGameManager.getBlackjackStatistics();

            // then
            assertThat(profitOf(actual, "jacob")).isEqualTo(-1000);
            assertThat(profitOf(actual, "seoye")).isEqualTo(-500);
            assertThat(actual.dealerProfit()).isEqualTo(1500);
        }

        @Test
        void 플레이어와_딜러가_모두_블랙잭이면_무승부다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.ACE, Suit.HEART), card(Rank.K, Suit.SPADE),
                    card(Rank.ACE, Suit.CLOVER), card(Rank.Q, Suit.DIAMOND),
                    card(Rank.TEN, Suit.CLOVER), card(Rank.NINE, Suit.DIAMOND)
            ));
            blackjackGameManager.drawInitialCards();

            // when
            BlackjackStatisticsDto actual = blackjackGameManager.getBlackjackStatistics();

            // then
            assertThat(profitOf(actual, "jacob")).isEqualTo(0);
            assertThat(profitOf(actual, "seoye")).isEqualTo(-500);
            assertThat(actual.dealerProfit()).isEqualTo(500);
        }

        @Test
        void 플레이어만_블랙잭이면_배팅_금액의_1_5배를_번다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.NINE, Suit.SPADE),
                    card(Rank.ACE, Suit.CLOVER), card(Rank.K, Suit.DIAMOND),
                    card(Rank.EIGHT, Suit.HEART), card(Rank.SEVEN, Suit.CLOVER)
            ));
            blackjackGameManager.drawInitialCards();

            // when
            BlackjackStatisticsDto actual = blackjackGameManager.getBlackjackStatistics();

            // then
            assertThat(profitOf(actual, "jacob")).isEqualTo(1500);
            assertThat(profitOf(actual, "seoye")).isEqualTo(-500);
            assertThat(actual.dealerProfit()).isEqualTo(-1000);
        }

        @Test
        void 딜러가_버스트면_살아있는_플레이어는_승리한다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                    card(Rank.TEN, Suit.CLOVER), card(Rank.SEVEN, Suit.DIAMOND),
                    card(Rank.NINE, Suit.HEART), card(Rank.EIGHT, Suit.CLOVER),
                    card(Rank.NINE, Suit.DIAMOND)
            ));
            blackjackGameManager.drawInitialCards();
            blackjackGameManager.drawDealerCard();

            // when
            BlackjackStatisticsDto actual = blackjackGameManager.getBlackjackStatistics();

            // then
            assertThat(profitOf(actual, "jacob")).isEqualTo(1000);
            assertThat(profitOf(actual, "seoye")).isEqualTo(500);
            assertThat(actual.dealerProfit()).isEqualTo(-1500);
        }

        @Test
        void 플레이어_점수가_딜러보다_크면_승리한다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.EIGHT, Suit.SPADE),
                    card(Rank.TEN, Suit.CLOVER), card(Rank.NINE, Suit.DIAMOND),
                    card(Rank.NINE, Suit.HEART), card(Rank.SEVEN, Suit.CLOVER)
            ));
            blackjackGameManager.drawInitialCards();

            // when
            BlackjackStatisticsDto actual = blackjackGameManager.getBlackjackStatistics();

            // then
            assertThat(profitOf(actual, "jacob")).isEqualTo(1000);
            assertThat(profitOf(actual, "seoye")).isEqualTo(-500);
            assertThat(actual.dealerProfit()).isEqualTo(-500);
        }

        @Test
        void 플레이어_점수가_딜러와_같으면_무승부다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.EIGHT, Suit.SPADE),
                    card(Rank.NINE, Suit.CLOVER), card(Rank.NINE, Suit.DIAMOND),
                    card(Rank.TEN, Suit.HEART), card(Rank.SEVEN, Suit.CLOVER)
            ));
            blackjackGameManager.drawInitialCards();

            // when
            BlackjackStatisticsDto actual = blackjackGameManager.getBlackjackStatistics();

            // then
            assertThat(profitOf(actual, "jacob")).isEqualTo(0);
            assertThat(profitOf(actual, "seoye")).isEqualTo(-500);
            assertThat(actual.dealerProfit()).isEqualTo(500);
        }

        @Test
        void 플레이어_점수가_딜러보다_작으면_패배한다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.EIGHT, Suit.SPADE),
                    card(Rank.NINE, Suit.CLOVER), card(Rank.EIGHT, Suit.DIAMOND),
                    card(Rank.TEN, Suit.HEART), card(Rank.SEVEN, Suit.CLOVER)
            ));
            blackjackGameManager.drawInitialCards();

            // when
            BlackjackStatisticsDto actual = blackjackGameManager.getBlackjackStatistics();

            // then
            assertThat(profitOf(actual, "jacob")).isEqualTo(-1000);
            assertThat(profitOf(actual, "seoye")).isEqualTo(-500);
            assertThat(actual.dealerProfit()).isEqualTo(1500);
        }

        @Test
        void 플레이어가_블랙잭이고_딜러가_버스트여도_블랙잭_승리다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                    card(Rank.ACE, Suit.CLOVER), card(Rank.K, Suit.DIAMOND),
                    card(Rank.NINE, Suit.HEART), card(Rank.EIGHT, Suit.CLOVER),
                    card(Rank.NINE, Suit.DIAMOND)
            ));
            blackjackGameManager.drawInitialCards();
            blackjackGameManager.drawDealerCard();

            // when
            BlackjackStatisticsDto actual = blackjackGameManager.getBlackjackStatistics();

            // then
            assertThat(profitOf(actual, "jacob")).isEqualTo(1500);
            assertThat(profitOf(actual, "seoye")).isEqualTo(500);
            assertThat(actual.dealerProfit()).isEqualTo(-2000);
        }
    }

    @Nested
    class IsPlayerBustTest {

        @Test
        void 플레이어_버스트_여부를_반환한다() {
            // given
            BlackjackGameManager blackjackGameManager = fixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                    card(Rank.TEN, Suit.CLOVER), card(Rank.NINE, Suit.DIAMOND),
                    card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                    card(Rank.K, Suit.HEART)
            ));
            blackjackGameManager.drawInitialCards();
            blackjackGameManager.updatePlayer("jacob");

            // when
            boolean actual = blackjackGameManager.isPlayerBust("jacob");

            // then
            assertThat(actual).isTrue();
        }
    }

    private static int profitOf(BlackjackStatisticsDto blackjackStatisticsDto, String name) {
        return blackjackStatisticsDto.playerStatisticDtoList().stream()
                .filter(player -> player.name().equals(name))
                .findFirst()
                .orElseThrow()
                .profit();
    }

    private static BlackjackGameManager fixedDeckBlackjackGameManager(List<Card> cards) {
        return new BlackjackGameManager(
                new FixedDeckCardMachine(cards),
                new BlackjackJudge(),
                Participants.of(playerNames(), betAmounts("1000", "500"))
        );
    }

    private static List<PlayerName> playerNames() {
        return List.of(new PlayerName("jacob"), new PlayerName("seoye"));
    }

    private static List<BetAmount> betAmounts(String first, String second) {
        return List.of(new BetAmount(first), new BetAmount(second));
    }

    private static Card card(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }

    private static class FixedDeckCardMachine extends CardMachine {

        private final Deque<Card> cards;

        private FixedDeckCardMachine(List<Card> cards) {
            this.cards = new ArrayDeque<>(cards);
        }

        @Override
        public Card drawCard() {
            return cards.removeFirst();
        }
    }
}
