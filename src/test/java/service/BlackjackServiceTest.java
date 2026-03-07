package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.PolicyConstant;
import constant.Rank;
import constant.Result;
import constant.Suit;
import domain.Card;
import domain.Dealer;
import domain.Player;
import dto.BlackjackResultDto;
import dto.DealerResultDto;
import dto.PlayerResultDto;
import dto.PlayersDto;
import exception.ErrorMessage;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class BlackjackServiceTest {

    private final BlackjackService blackjackService = new BlackjackService();

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

        @Nested
        class Fail {

            @Test
            void 게임_참가_인원은_이름이_중복_되어선_안된다() {

                // given
                List<String> input = List.of("jacob", "jacob");

                // when & then
                assertThatThrownBy(() -> {
                    blackjackService.createPlayers(input);
                })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.PLAYER_DUPLICATED.getMessage());
            }

            @Test
            void 게임_참가_인원은_2_이상_8_이하가_아니라면_예외를_발생_시켜야_한다() {

                // given
                List<String> input = List.of("jacob");

                // when & then
                assertThatThrownBy(() -> {
                    blackjackService.createPlayers(input);
                })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE.getMessage());
            }

            @Test
            void 입력값에_공백만_입력되면_예외를_발생_시켜야_한다() {

                // given
                List<String> input = List.of("ab", "", "bc");

                // when & then
                assertThatThrownBy(() -> {
                    blackjackService.createPlayers(input);
                })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.PLAYER_NAME_BLANK.getMessage());
            }

            @ParameterizedTest
            @MethodSource("provideCases")
            void 게임_참가자_이름의_길이가_2_이상_5_이하가_아니라면_예외를_발생_시켜야_한다(List<String> input) {

                // when & then
                assertThatThrownBy(() -> {
                    blackjackService.createPlayers(input);
                })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.PLAYER_NAME_LENGTH_OUT_OF_RANGE.getMessage());
            }

            static Stream<Arguments> provideCases() {
                return Stream.of(
                    Arguments.of(List.of("j", "ja")),
                    Arguments.of(List.of("aa aaa", "bb bbb"))
                );
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
    class DealInitialCardsTest {

        @Nested
        class Success {

            @Test
            void 게임_참가자와_딜러_모두가_카드를_두장씩_받아야_한다() {

                // given
                PlayersDto playersDto = blackjackService.createPlayers(List.of("aa", "bb", "cc"));

                // when
                PlayersDto actual = blackjackService.dealInitialCards(playersDto);

                // then
                assertThat(blackjackService.generateHandDto(actual.dealer()).hand()).hasSize(2);
                assertThat(actual.players())
                    .allSatisfy(
                        player -> assertThat(blackjackService.generateHandDto(player).hand())
                            .hasSize(2));
            }
        }
    }

    @Nested
    class DrawDealerCardTest {

        @Nested
        class Success {

            @Test
            void 딜러의_카드_숫자가_17_미만_이라면_카드를_뽑는다() {

                // given
                Dealer dealer = new Dealer(PolicyConstant.DEALER_NAME);
                addCards(dealer, Rank.TEN, Rank.SIX);

                // when
                boolean actual = blackjackService.drawDealerCard(dealer);

                // then
                assertThat(actual).isTrue();
                assertThat(blackjackService.generateHandDto(dealer).hand()).hasSize(3);
            }

            @Test
            void 딜러의_카드_숫자가_17_이상_이라면_카드를_뽑지_않는다() {

                // given
                Dealer dealer = new Dealer(PolicyConstant.DEALER_NAME);
                addCards(dealer, Rank.TEN, Rank.SEVEN);

                // when
                boolean actual = blackjackService.drawDealerCard(dealer);

                // then
                assertThat(actual).isFalse();
                assertThat(blackjackService.generateHandDto(dealer).hand()).hasSize(2);
            }
        }
    }

    @Nested
    class CalculatePlayerResultsTest {

        @Nested
        class Success {

            @Test
            void 딜러가_버스트면_버스트가_아닌_참가자는_승이고_버스트인_참가자는_패다() {

                // given
                Dealer dealer = new Dealer(PolicyConstant.DEALER_NAME);
                addCards(dealer, Rank.TEN, Rank.Q, Rank.TWO);

                Player player1 = new Player("aa");
                addCards(player1, Rank.TEN, Rank.SEVEN);

                Player player2 = new Player("bb");
                addCards(player2, Rank.TEN, Rank.Q, Rank.TWO);

                PlayersDto playersDto = new PlayersDto(List.of(player1, player2), dealer);

                // when
                List<PlayerResultDto> actual = blackjackService.calculatePlayerResults(playersDto);

                // then
                assertThat(actual).hasSize(2);
                assertThat(actual.get(0).name()).isEqualTo("aa");
                assertThat(actual.get(0).result()).isEqualTo(Result.WIN);
                assertThat(actual.get(1).name()).isEqualTo("bb");
                assertThat(actual.get(1).result()).isEqualTo(Result.LOSE);
            }

            @Test
            void 딜러가_버스트가_아니면_점수_비교_규칙대로_승무패를_계산해야_한다() {

                // given
                Dealer dealer = new Dealer(PolicyConstant.DEALER_NAME);
                addCards(dealer, Rank.TEN, Rank.SEVEN);

                Player player1 = new Player("aa");
                addCards(player1, Rank.TEN, Rank.SIX);

                Player player2 = new Player("bb");
                addCards(player2, Rank.TEN, Rank.SEVEN);

                Player player3 = new Player("cc");
                addCards(player3, Rank.TEN, Rank.EIGHT);

                Player player4 = new Player("dd");
                addCards(player4, Rank.TEN, Rank.Q, Rank.TWO);

                PlayersDto playersDto = new PlayersDto(List.of(player1, player2, player3, player4),
                    dealer);

                // when
                List<PlayerResultDto> actual = blackjackService.calculatePlayerResults(playersDto);

                // then
                assertThat(actual).hasSize(4);
                assertThat(actual.get(0).result()).isEqualTo(Result.LOSE);
                assertThat(actual.get(1).result()).isEqualTo(Result.DRAW);
                assertThat(actual.get(2).result()).isEqualTo(Result.WIN);
                assertThat(actual.get(3).result()).isEqualTo(Result.LOSE);
            }
        }
    }

    @Nested
    class CalculateDealerResultTest {

        @Nested
        class Success {

            @Test
            void 딜러_승무패_집계는_참가자_결과를_기준으로_계산해야_한다() {

                // given
                Dealer dealer = new Dealer(PolicyConstant.DEALER_NAME);
                addCards(dealer, Rank.TEN, Rank.SEVEN);

                Player player1 = new Player("aa");
                addCards(player1, Rank.TEN, Rank.SIX);

                Player player2 = new Player("bb");
                addCards(player2, Rank.TEN, Rank.SEVEN);

                Player player3 = new Player("cc");
                addCards(player3, Rank.TEN, Rank.EIGHT);

                Player player4 = new Player("dd");
                addCards(player4, Rank.TEN, Rank.Q, Rank.TWO);

                PlayersDto playersDto = new PlayersDto(List.of(player1, player2, player3, player4),
                    dealer);

                // when
                DealerResultDto actual = blackjackService.calculateDealerResult(playersDto);

                // then
                assertThat(actual.win()).isEqualTo(2);
                assertThat(actual.draw()).isEqualTo(1);
                assertThat(actual.lose()).isEqualTo(1);
            }
        }
    }

    private void addCards(Player player, Rank... ranks) {
        for (Rank rank : ranks) {
            player.addCard(new Card(rank, Suit.HEART));
        }
    }

    @Nested
    class generateBlackjackResultDto {

        @Nested
        class Success {

            @Test
            void 딜러_승무패_집계는_참가자_결과를_기준으로_계산해야_한다() {

                // given
                Dealer dealer = new Dealer(PolicyConstant.DEALER_NAME);
                addCards(dealer, Rank.TEN, Rank.SEVEN);

                Player player1 = new Player("aa");
                addCards(player1, Rank.TEN, Rank.SIX);

                Player player2 = new Player("bb");
                addCards(player2, Rank.TEN, Rank.SEVEN);

                Player player3 = new Player("cc");
                addCards(player3, Rank.TEN, Rank.EIGHT);

                Player player4 = new Player("dd");
                addCards(player4, Rank.TEN, Rank.Q, Rank.TWO);

                PlayersDto playersDto = new PlayersDto(List.of(player1, player2, player3, player4),
                    dealer);

                // when
                List<BlackjackResultDto> actual = blackjackService.generateBlackjackResultDto(
                    playersDto);

                // then
                assertThat(actual).hasSize(5);

                assertThat(actual.get(0).handDto().name()).isEqualTo(PolicyConstant.DEALER_NAME);
                assertThat(actual.get(0).score()).isEqualTo(17);
                assertThat(actual.get(0).handDto().hand()).hasSize(2);

                assertThat(actual.get(1).handDto().name()).isEqualTo("aa");
                assertThat(actual.get(1).score()).isEqualTo(16);
                assertThat(actual.get(1).handDto().hand()).hasSize(2);

                assertThat(actual.get(2).handDto().name()).isEqualTo("bb");
                assertThat(actual.get(2).score()).isEqualTo(17);
                assertThat(actual.get(2).handDto().hand()).hasSize(2);

                assertThat(actual.get(3).handDto().name()).isEqualTo("cc");
                assertThat(actual.get(3).score()).isEqualTo(18);
                assertThat(actual.get(3).handDto().hand()).hasSize(2);

                assertThat(actual.get(4).handDto().name()).isEqualTo("dd");
                assertThat(actual.get(4).score()).isEqualTo(22);
                assertThat(actual.get(4).handDto().hand()).hasSize(3);
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
                    .hasMessageContaining(ErrorMessage.INVALID_YES_NO_INPUT.getMessage());
            }
        }
    }
}
