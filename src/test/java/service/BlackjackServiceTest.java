package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import config.ControllerConfig;
import constant.PolicyConstant;
import constant.Rank;
import constant.Result;
import constant.Suit;
import domain.Card;
import dto.BlackjackStatisticsDto;
import dto.ParticipantDto;
import dto.PlayerResultDto;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class BlackjackServiceTest {

    private final ControllerConfig controllerConfig = new ControllerConfig();
    private final BlackjackService blackjackService = controllerConfig.blackjackService();

    @Nested
    class CreatePlayersTest {

        @Nested
        class Success {

            @ParameterizedTest
            @MethodSource("successCases")
            void 게임_참가자_조건이_맞으면_정상_작동_해야한다(List<String> input) {

                // when & then
                blackjackService.createPlayers(input);
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
    class DealInitialCardsTest {

        @Nested
        class Success {

            @Test
            void 초기_카드_분배시_딜러는_1장_플레이어는_2장씩_초기_결과에_노출된다() {

                // given
                blackjackService.createPlayers(List.of("jacob", "seoye"));

                // when
                blackjackService.dealInitialCards();
                List<ParticipantDto> actual = blackjackService.generateInitialParticipantDtoList();

                // then
                assertThat(actual).hasSize(3);
                assertThat(actual.getFirst().name()).isEqualTo("딜러");
                assertThat(actual.getFirst().hand()).hasSize(1);
                assertThat(actual.get(1).hand()).hasSize(2);
                assertThat(actual.get(2).hand()).hasSize(2);
            }

            @Test
            void 초기_카드_분배시_최종_결과에는_모두_2장씩_있어야_한다() {

                // given
                blackjackService.createPlayers(List.of("jacob", "seoye"));

                // when
                blackjackService.dealInitialCards();
                List<ParticipantDto> actual = blackjackService.getBlackjackResult();

                // then
                assertThat(actual).hasSize(3);
                assertThat(actual.getFirst().hand()).hasSize(2);
                assertThat(actual.get(1).hand()).hasSize(2);
                assertThat(actual.get(2).hand()).hasSize(2);
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
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.dealInitialCards();

                // when
                ParticipantDto actual = blackjackService.generateInitialDealerDto();

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
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.dealInitialCards();

                // when
                ParticipantDto actual = blackjackService.generateDealerDto();

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
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.dealInitialCards();

                // when
                List<ParticipantDto> actual = blackjackService.generatePlayersDto();

                // then
                assertThat(actual).hasSize(2);
                assertThat(actual.get(0).name()).isEqualTo("jacob");
                assertThat(actual.get(1).name()).isEqualTo("seoye");
            }
        }
    }

    @Nested
    class validateHitOrStand {

        @Nested
        class Success {

            @ParameterizedTest
            @ValueSource(strings = {"y", "n"})
            void y_또는_n_이면_예외를_발생시키지_않는다(String input) {

                // given & when & then
                blackjackService.validateHitOrStand(input);
            }
        }

        @Nested
        class Fail {

            @Test
            void y_또는_n_가_아니면_예외를_발생시킨다() {

                // given
                String input = "a";

                // when & then
                assertThatThrownBy(() -> blackjackService.validateHitOrStand(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(PolicyConstant.ERROR_PREFIX + BlackjackService.INVALID_YES_NO_INPUT);
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
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                        card(Rank.NINE, Suit.CLOVER)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.dealInitialCards();

                // when
                boolean actual = blackjackService.drawDealerCard();

                // then
                List<ParticipantDto> result = blackjackService.getBlackjackResult();
                assertThat(actual).isTrue();
                assertThat(result.getFirst().hand()).hasSize(3);
            }

            @Test
            void 딜러_점수가_17_이상이면_카드를_추가로_뽑지_않아야_한다() {

                // given
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SEVEN, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                        card(Rank.NINE, Suit.CLOVER)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.dealInitialCards();

                // when
                boolean actual = blackjackService.drawDealerCard();

                // then
                List<ParticipantDto> result = blackjackService.getBlackjackResult();
                assertThat(actual).isFalse();
                assertThat(result.getFirst().hand()).hasSize(2);
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
                Card actual = blackjackService.drawCard();

                // then
                assertThat(actual).isNotNull();
            }

            @Test
            void 카드가_소진되기_전까지는_카드를_반환해야_한다() {

                // given
                Card actual = null;

                // when
                for (int i = 0; i < 312; i++) {
                    actual = blackjackService.drawCard();
                }

                // then
                assertThat(actual).isNotNull();
            }

            @Test
            void 카드가_모두_소진되면_null을_반환해야_한다() {

                // given
                for (int i = 0; i < 312; i++) {
                    blackjackService.drawCard();
                }

                // when
                Card actual = blackjackService.drawCard();

                // then
                assertThat(actual).isNull();
            }
        }
    }

    @Nested
    class CalculatePlayerResultsTest {

        @Nested
        class Success {

            @Test
            void 플레이어별_결과를_승무패로_계산해야_한다() {

                // given
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SEVEN, Suit.SPADE),
                    card(Rank.TEN, Suit.CLOVER), card(Rank.EIGHT, Suit.DIAMOND),
                    card(Rank.TEN, Suit.DIAMOND), card(Rank.SEVEN, Suit.HEART),
                    card(Rank.NINE, Suit.CLOVER), card(Rank.SEVEN, Suit.DIAMOND)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye", "brown"));
                blackjackService.dealInitialCards();

                // when
                List<PlayerResultDto> actual = blackjackService.calculatePlayerResults();

                // then
                assertThat(actual).containsExactly(
                    new PlayerResultDto("jacob", Result.WIN),
                    new PlayerResultDto("seoye", Result.DRAW),
                    new PlayerResultDto("brown", Result.LOSE)
                );
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
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SEVEN, Suit.SPADE),
                    card(Rank.TEN, Suit.CLOVER), card(Rank.EIGHT, Suit.DIAMOND),
                    card(Rank.TEN, Suit.DIAMOND), card(Rank.SEVEN, Suit.HEART),
                    card(Rank.NINE, Suit.CLOVER), card(Rank.SEVEN, Suit.DIAMOND)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye", "brown"));
                blackjackService.dealInitialCards();

                // when
                BlackjackStatisticsDto actual = blackjackService.getBlackjackStatistics();

                // then
                assertThat(actual.dealerResultDto().win()).isEqualTo(1);
                assertThat(actual.dealerResultDto().draw()).isEqualTo(1);
                assertThat(actual.dealerResultDto().lose()).isEqualTo(1);
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
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                    card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                    card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                    card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                    card(Rank.ACE, Suit.CLOVER)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.dealInitialCards();
                blackjackService.updatePlayer("jacob");

                // when
                List<ParticipantDto> actual = blackjackService.getBlackjackResult();

                // then
                assertThat(actual).hasSize(3);
                assertThat(actual.getFirst().name()).isEqualTo("딜러");
                assertThat(actual.get(1).name()).isEqualTo("jacob");
                assertThat(actual.get(1).hand()).hasSize(3);
                assertThat(actual.get(1).hand()).contains("A클로버");
                assertThat(actual.get(2).name()).isEqualTo("seoye");
                assertThat(actual.get(2).hand()).hasSize(2);
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
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                        card(Rank.ACE, Suit.CLOVER)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.dealInitialCards();

                // when
                ParticipantDto actual = blackjackService.updatePlayer("jacob");

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
            void y를_입력하면_true를_반환한다() {

                // when
                boolean actual = blackjackService.isHit("y");

                // then
                assertThat(actual).isTrue();
            }

            @Test
            void n을_입력하면_false를_반환한다() {

                // when
                boolean actual = blackjackService.isHit("n");

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

                // when
                boolean actual = blackjackService.isStand(" n ");

                // then
                assertThat(actual).isTrue();
            }

            @Test
            void y를_입력하면_false를_반환한다() {

                // when
                boolean actual = blackjackService.isStand("y");

                // then
                assertThat(actual).isFalse();
            }
        }
    }

    private static Card card(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }

    private static class FixedDeckBlackjackService extends BlackjackService {

        private final Deque<Card> cards;

        private FixedDeckBlackjackService(List<Card> cards) {
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
