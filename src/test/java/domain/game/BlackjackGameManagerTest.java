package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.BetAmount;
import dto.BlackjackResultDto;
import dto.BlackjackStatisticsDto;
import dto.ParticipantDto;
import factory.BlackjackControllerFactory;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackGameManagerTest {

    private final BlackjackControllerFactory blackjackControllerFactory = new BlackjackControllerFactory();
    private final BlackjackGameManager blackjackGameManager = blackjackControllerFactory.blackjackGameManager();

    @Nested
    class DrawDealerCardTest {

        @Nested
        class Success {

            @Test
            void 딜러_점수가_16_이하면_카드를_추가로_뽑아야_한다() {

                // given
                BlackjackGameManager blackjackGameManager = new FixedDeckBlackjackGameManager(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                        card(Rank.NINE, Suit.CLOVER)
                ));
                blackjackGameManager.createParticipants(List.of("jacob", "seoye"));
                blackjackGameManager.drawInitialCards();

                // when
                boolean actual = blackjackGameManager.drawDealerCard();

                // then
                BlackjackResultDto result = blackjackGameManager.getBlackjackResult();
                assertThat(actual).isTrue();
                assertThat(result.playerResultDtoList().getFirst().hand()).hasSize(2);
            }

            @Test
            void 딜러_점수가_17_이상이면_카드를_추가로_뽑지_않아야_한다() {

                // given
                BlackjackGameManager blackjackGameManager = new FixedDeckBlackjackGameManager(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SEVEN, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                        card(Rank.NINE, Suit.CLOVER)
                ));
                blackjackGameManager.createParticipants(List.of("jacob", "seoye"));
                blackjackGameManager.drawInitialCards();

                // when
                boolean actual = blackjackGameManager.drawDealerCard();

                // then
                BlackjackResultDto result = blackjackGameManager.getBlackjackResult();
                assertThat(actual).isFalse();
                assertThat(result.playerResultDtoList().getFirst().hand()).hasSize(2);
            }

        }
    }

    @Nested
    class UpdatePlayerTest {

        @Nested
        class Success {

            @Test
            void 플레이어_카드를_추가하고_업데이트된_Dto를_반환한다() {

                // given
                BlackjackGameManager blackjackGameManager = new FixedDeckBlackjackGameManager(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                        card(Rank.ACE, Suit.CLOVER)
                ));
                blackjackGameManager.createParticipants(List.of("jacob", "seoye"));
                blackjackGameManager.drawInitialCards();

                // when
                ParticipantDto actual = blackjackGameManager.updatePlayer("jacob");

                // then
                assertThat(actual.name()).isEqualTo("jacob");
                assertThat(actual.hand()).hasSize(3);
            }
        }
    }

    @Nested
    class GenerateInitialDealerDtoTest {

        @Nested
        class Success {

            @Test
            void 딜러의_초기_공개_Dto를_생성해야_한다() {

                // given
                BlackjackGameManager blackjackGameManager = new FixedDeckBlackjackGameManager(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE)
                ));
                blackjackGameManager.createParticipants(List.of("jacob", "seoye"));
                blackjackGameManager.drawInitialCards();

                // when
                ParticipantDto actual = blackjackGameManager.generateInitialDealerDto();

                // then
                assertThat(actual.name()).isEqualTo("딜러");
                assertThat(actual.hand()).hasSize(1);
            }
        }
    }

    @Nested
    class GenerateDealerDtoTest {

        @Nested
        class Success {

            @Test
            void 딜러의_최종_Dto를_생성해야_한다() {

                // given
                BlackjackGameManager blackjackGameManager = new FixedDeckBlackjackGameManager(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE)
                ));
                blackjackGameManager.createParticipants(List.of("jacob", "seoye"));
                blackjackGameManager.drawInitialCards();

                // when
                ParticipantDto actual = blackjackGameManager.generateDealerDto();

                // then
                assertThat(actual.name()).isEqualTo("딜러");
                assertThat(actual.hand()).hasSize(2);
            }
        }
    }

    @Nested
    class GeneratePlayersDtoTest {

        @Nested
        class Success {

            @Test
            void 플레이어_Dto_목록을_생성해야_한다() {

                // given
                BlackjackGameManager blackjackGameManager = new FixedDeckBlackjackGameManager(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE)
                ));
                blackjackGameManager.createParticipants(List.of("jacob", "seoye"));
                blackjackGameManager.drawInitialCards();

                // when
                List<ParticipantDto> actual = blackjackGameManager.generatePlayerDtoList();

                // then
                assertThat(actual).hasSize(2);
                assertThat(actual.get(0).name()).isEqualTo("jacob");
                assertThat(actual.get(1).name()).isEqualTo("seoye");
            }
        }
    }

    @Nested
    class getBlackjackResultTest {

        @Nested
        class Success {

            @Test
            void 플레이어가_카드를_추가로_받으면_최종_결과에_반영되어야_한다() {

                // given
                BlackjackGameManager blackjackGameManager = new FixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                    card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                    card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                    card(Rank.ACE, Suit.CLOVER)
                ));
                blackjackGameManager.createParticipants(List.of("jacob", "seoye"));
                blackjackGameManager.drawInitialCards();
                blackjackGameManager.updatePlayer("jacob");

                // when
                BlackjackResultDto actual = blackjackGameManager.getBlackjackResult();

                // then
                assertThat(actual.playerResultDtoList().get(0).name()).isEqualTo("jacob");
                assertThat(actual.playerResultDtoList().get(0).hand()).hasSize(3);
                assertThat(actual.playerResultDtoList().get(0).hand()).contains("A클로버");
                assertThat(actual.playerResultDtoList().get(1).name()).isEqualTo("seoye");
                assertThat(actual.playerResultDtoList().get(1).hand()).hasSize(2);
            }
        }
    }

    @Nested
    class GetBlackjackStatisticsTest {

        @Nested
        class Success {

            @Test
            void 블랙잭_승리와_패배를_반영해_수익을_계산한다() {

                // given
                BlackjackGameManager blackjackGameManager = new FixedDeckBlackjackGameManager(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.NINE, Suit.SPADE),
                        card(Rank.ACE, Suit.CLOVER), card(Rank.K, Suit.DIAMOND),
                        card(Rank.TEN, Suit.CLOVER), card(Rank.SEVEN, Suit.DIAMOND)
                ));
                blackjackGameManager.createParticipants(List.of("jacob", "seoye"));
                blackjackGameManager.drawInitialCards();
                blackjackGameManager.setBetAmount("jacob", new BetAmount("1000"));
                blackjackGameManager.setBetAmount("seoye", new BetAmount("500"));

                // when
                BlackjackStatisticsDto actual = blackjackGameManager.getBlackjackStatistics();

                // then
                assertThat(actual.dealerProfit()).isEqualTo(-1000);
                assertThat(actual.playerStatisticDtoList()).hasSize(2);
                assertThat(actual.playerStatisticDtoList().get(0).name()).isEqualTo("jacob");
                assertThat(actual.playerStatisticDtoList().get(0).profit()).isEqualTo(1500);
                assertThat(actual.playerStatisticDtoList().get(1).name()).isEqualTo("seoye");
                assertThat(actual.playerStatisticDtoList().get(1).profit()).isEqualTo(-500);
            }

            @Test
            void 무승부일_때_플레이어_수익은_0이고_딜러_수익에_반영되지_않는다() {

                // given
                BlackjackGameManager blackjackGameManager = new FixedDeckBlackjackGameManager(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.EIGHT, Suit.SPADE),
                        card(Rank.NINE, Suit.CLOVER), card(Rank.NINE, Suit.DIAMOND),
                        card(Rank.TEN, Suit.CLOVER), card(Rank.SIX, Suit.DIAMOND)
                ));
                blackjackGameManager.createParticipants(List.of("jacob", "seoye"));
                blackjackGameManager.drawInitialCards();
                blackjackGameManager.setBetAmount("jacob", new BetAmount("500"));
                blackjackGameManager.setBetAmount("seoye", new BetAmount("700"));

                // when
                BlackjackStatisticsDto actual = blackjackGameManager.getBlackjackStatistics();

                // then
                assertThat(actual.dealerProfit()).isEqualTo(700);
                assertThat(actual.playerStatisticDtoList().get(0).profit()).isEqualTo(0);
                assertThat(actual.playerStatisticDtoList().get(1).profit()).isEqualTo(-700);
            }

            @Test
            void 플레이어가_버스트면_딜러가_버스트여도_패배로_처리한다() {

                // given
                BlackjackGameManager blackjackGameManager = new FixedDeckBlackjackGameManager(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TEN, Suit.CLOVER), card(Rank.EIGHT, Suit.DIAMOND),
                        card(Rank.NINE, Suit.HEART), card(Rank.SEVEN, Suit.CLOVER),
                        card(Rank.K, Suit.SPADE), card(Rank.Q, Suit.DIAMOND)
                ));
                blackjackGameManager.createParticipants(List.of("jacob", "seoye"));
                blackjackGameManager.drawInitialCards();
                blackjackGameManager.setBetAmount("jacob", new BetAmount("1000"));
                blackjackGameManager.setBetAmount("seoye", new BetAmount("500"));
                blackjackGameManager.updatePlayer("seoye");
                blackjackGameManager.drawDealerCard();

                // when
                BlackjackStatisticsDto actual = blackjackGameManager.getBlackjackStatistics();

                // then
                assertThat(actual.dealerProfit()).isEqualTo(-500);
                assertThat(actual.playerStatisticDtoList().get(0).profit()).isEqualTo(1000);
                assertThat(actual.playerStatisticDtoList().get(1).profit()).isEqualTo(-500);
            }
        }
    }

    @Nested
    class IsHitTest {

        @Nested
        class Success {

            @Test
            void y를_입력하면_true_를_반환한다() {
                // given
                HitOrStand hitOrStand = HitOrStand.from(" y ");

                // when
                boolean actual = blackjackGameManager.isHit(hitOrStand);

                // then
                assertThat(actual).isTrue();
            }

            @Test
            void n을_입력하면_false를_반환한다() {
                // given
                HitOrStand hitOrStand = HitOrStand.from(" n ");

                // when
                boolean actual = blackjackGameManager.isHit(hitOrStand);

                // then
                assertThat(actual).isFalse();
            }
        }
    }

    @Nested
    class IsStandTest {

        @Nested
        class Success {

            @Test
            void n을_입력하면_true를_반환한다() {
                // given
                HitOrStand hitOrStand = HitOrStand.from(" n ");

                // when
                boolean actual = blackjackGameManager.isStand(hitOrStand);

                // then
                assertThat(actual).isTrue();
            }

            @Test
            void y를_입력하면_false를_반환한다() {
                // given
                HitOrStand hitOrStand = HitOrStand.from(" y ");

                // when
                boolean actual = blackjackGameManager.isStand(hitOrStand);

                // then
                assertThat(actual).isFalse();
            }
        }
    }

    private static Card card(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }

    private static class FixedDeckBlackjackGameManager extends BlackjackGameManager {

        private final Deque<Card> cards;

        private FixedDeckBlackjackGameManager(List<Card> cards) {
            this.cards = new ArrayDeque<>(cards);
        }

        @Override
        public Card drawCard() {
            if (cards.isEmpty()) {
                return null;
            }
            return cards.removeFirst();
        }
    }
}
