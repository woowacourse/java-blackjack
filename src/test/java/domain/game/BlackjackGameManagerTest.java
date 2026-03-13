package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import dto.BlackjackResultDto;
import dto.BlackjackStatisticsDto;
import dto.ParticipantDto;
import factory.BlackjackControllerFactory;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackjackGameManagerTest {

    private final BlackjackControllerFactory blackjackControllerFactory = new BlackjackControllerFactory();
    private final BlackjackGameManager blackjackGameManager = blackjackControllerFactory.blackjackService();

    @Nested
    class CreatePlayersTest {

        @Nested
        class Success {

            @ParameterizedTest
            @MethodSource("successCases")
            void 게임_참가자_조건이_맞으면_정상_작동_해야한다(List<String> input) {

                // when & then
                blackjackGameManager.createParticipants(input);
            }

            static Stream<Arguments> successCases() {
                return Stream.of(
                        Arguments.of(List.of("jacob", "seoye")),
                        Arguments.of(List.of("aa aa", "성 열"))
                );
            }
        }
    }

    @Nested
    class DrawInitialCardsTest {

        @Nested
        class Success {

            @Test
            void 초기_카드_분배시_최종_결과에는_모두_2장씩_있어야_한다() {

                // given
                blackjackGameManager.createParticipants(List.of("jacob", "seoye"));

                // when
                blackjackGameManager.drawInitialCards();
                BlackjackResultDto actual = blackjackGameManager.getBlackjackResult();

                // then
                assertThat(actual.playerResultDtoList().get(0).hand()).hasSize(2);
                assertThat(actual.playerResultDtoList().get(1).hand()).hasSize(2);
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
    class DrawCardTest {

        @Nested
        class Success {

            @Test
            void 카드를_뽑으면_null이_아닌_카드를_반환해야_한다() {

                // when
                Card actual = blackjackGameManager.drawCard();

                // then
                assertThat(actual).isNotNull();
            }

            @Test
            void 카드가_소진되기_전까지는_카드를_반환해야_한다() {

                // given
                Card actual = null;

                // when
                for (int i = 0; i < 312; i++) {
                    actual = blackjackGameManager.drawCard();
                }

                // then
                assertThat(actual).isNotNull();
            }
        }
    }

    @Nested
    class CalculateDealerResultTest {

        @Nested
        class Success {

            @Test
            void 딜러_승무패_통계를_플레이어_결과로부터_계산해야_한다() {

                // given
                BlackjackGameManager blackjackGameManager = new FixedDeckBlackjackGameManager(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SEVEN, Suit.SPADE),
                    card(Rank.TEN, Suit.CLOVER), card(Rank.EIGHT, Suit.DIAMOND),
                    card(Rank.TEN, Suit.DIAMOND), card(Rank.SEVEN, Suit.HEART),
                    card(Rank.NINE, Suit.CLOVER), card(Rank.SEVEN, Suit.DIAMOND)
                ));
                blackjackGameManager.createParticipants(List.of("jacob", "seoye", "brown"));
                blackjackGameManager.drawInitialCards();

                // when
                BlackjackStatisticsDto actual = blackjackGameManager.getBlackjackStatistics();

                // then
                assertThat(actual.dealerStatisticDto().win()).isEqualTo(1);
                assertThat(actual.dealerStatisticDto().draw()).isEqualTo(1);
                assertThat(actual.dealerStatisticDto().lose()).isEqualTo(1);
            }
        }
    }

    @Nested
    class generateBlackjackResultDto {

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
