package converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.Rank;
import constant.Result;
import constant.Suit;
import domain.Card;
import domain.Dealer;
import domain.Player;
import dto.BlackjackResultDto;
import dto.HandDto;
import dto.PlayerResultDto;
import dto.PlayersDto;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackConverterTest {

    private final BlackjackConverter blackjackConverter = new BlackjackConverter();

    @Nested
    class ConvertPlayersDtoTest {

        @Nested
        class Success {

            @Test
            void 게임_참가자_목록과_딜러를_PlayersDto로_변환해야_한다() {

                // given
                List<Player> players = List.of(
                    new Player("jacob"),
                    new Player("seoye")
                );
                Dealer dealer = new Dealer("딜러");

                // when
                PlayersDto actual = blackjackConverter.convertPlayersDto(players, dealer);

                // then
                assertThat(actual.players())
                    .hasSize(2)
                    .containsExactlyElementsOf(players);
                assertThat(actual.dealer()).isEqualTo(dealer);
            }
        }

        @Nested
        class Fail {

            @Test
            void 변환된_PlayersDto의_목록은_수정할_수_없어야_한다() {

                // given
                List<Player> players = List.of(
                    new Player("jacob"),
                    new Player("seoye")
                );
                Dealer dealer = new Dealer("딜러");
                PlayersDto actual = blackjackConverter.convertPlayersDto(players, dealer);

                // when & then
                assertThatThrownBy(() -> actual.players().add(new Player("mike")))
                    .isInstanceOf(UnsupportedOperationException.class);
            }
        }
    }

    @Nested
    class ConvertHandDtoForDealerTest {

        @Nested
        class Success {

            @Test
            void 딜러의_첫번째_카드만_HandDto로_변환해야_한다() {

                // given
                Dealer dealer = new Dealer("딜러");
                Card firstCard = new Card(Rank.TEN, Suit.HEART);
                Card secondCard = new Card(Rank.ACE, Suit.SPADE);
                dealer.addCard(firstCard);
                dealer.addCard(secondCard);

                // when
                HandDto actual = blackjackConverter.convertHandDtoForDealer(dealer);

                // then
                assertThat(actual.name()).isEqualTo("딜러");
                assertThat(actual.hand())
                    .hasSize(1)
                    .containsExactly(firstCard.getName());
            }
        }
    }

    @Nested
    class ConvertPlayerResultDtoTest {

        @Nested
        class Success {

            @Test
            void 이름과_결과를_PlayerResultDto로_변환해야_한다() {

                // given
                String name = "aa";
                Result result = Result.WIN;

                // when
                PlayerResultDto actual = blackjackConverter.convertPlayerResultDto(name, result);

                // then
                assertThat(actual.name()).isEqualTo(name);
                assertThat(actual.result()).isEqualTo(result);
            }
        }
    }

    @Nested
    class ConvertHandDtoTest {

        @Nested
        class Success {

            @Test
            void 플레이어_이름과_손패를_HandDto로_변환해야_한다() {

                // given
                Player player = new Player("aa");
                Card firstCard = new Card(Rank.TWO, Suit.DIAMOND);
                Card secondCard = new Card(Rank.K, Suit.CLOVER);
                player.addCard(firstCard);
                player.addCard(secondCard);

                // when
                HandDto actual = blackjackConverter.convertHandDto(player);

                // then
                assertThat(actual.name()).isEqualTo("aa");
                assertThat(actual.hand())
                    .hasSize(2)
                    .containsExactly(firstCard.getName(), secondCard.getName());
            }
        }
    }

    @Nested
    class ConvertBlackjackResultDtoTest {

        @Nested
        class Success {

            @Test
            void HandDto와_점수를_BlackjackResultDto로_변환해야_한다() {

                // given
                HandDto handDto = new HandDto("aa", List.of("2다이아몬드", "K스페이드"));
                int score = 12;

                // when
                BlackjackResultDto actual = blackjackConverter.convertBlackjackResultDto(handDto,
                    score);

                // then
                assertThat(actual.handDto()).isEqualTo(handDto);
                assertThat(actual.score()).isEqualTo(score);
            }
        }
    }

    @Nested
    class ConvertHandDtoListTest {

        @Nested
        class Success {

            @Test
            void 딜러와_참가자들의_손패를_순서대로_HandDto_목록으로_변환해야_한다() {

                // given
                Dealer dealer = new Dealer("딜러");
                Card dealerFirstCard = new Card(Rank.ACE, Suit.HEART);
                Card dealerSecondCard = new Card(Rank.K, Suit.SPADE);
                dealer.addCard(dealerFirstCard);
                dealer.addCard(dealerSecondCard);

                Player player1 = new Player("aa");
                Card player1FirstCard = new Card(Rank.TWO, Suit.DIAMOND);
                Card player1SecondCard = new Card(Rank.THREE, Suit.CLOVER);
                player1.addCard(player1FirstCard);
                player1.addCard(player1SecondCard);

                Player player2 = new Player("bb");
                Card player2FirstCard = new Card(Rank.TEN, Suit.HEART);
                Card player2SecondCard = new Card(Rank.J, Suit.SPADE);
                player2.addCard(player2FirstCard);
                player2.addCard(player2SecondCard);

                PlayersDto playersDto = new PlayersDto(List.of(player1, player2), dealer);

                // when
                List<HandDto> actual = blackjackConverter.convertHandDtoList(playersDto);

                // then
                assertThat(actual).hasSize(3);

                assertThat(actual.get(0).name()).isEqualTo("딜러");
                assertThat(actual.get(0).hand())
                    .hasSize(1)
                    .containsExactly(dealerFirstCard.getName());

                assertThat(actual.get(1).name()).isEqualTo("aa");
                assertThat(actual.get(1).hand())
                    .containsExactly(player1FirstCard.getName(), player1SecondCard.getName());

                assertThat(actual.get(2).name()).isEqualTo("bb");
                assertThat(actual.get(2).hand())
                    .containsExactly(player2FirstCard.getName(), player2SecondCard.getName());
            }
        }

        @Nested
        class Fail {

            @Test
            void 변환된_HandDto_목록은_수정할_수_없어야_한다() {

                // given
                Dealer dealer = new Dealer("딜러"); // TODO: 상수?
                dealer.addCard(new Card(Rank.ACE, Suit.HEART));
                dealer.addCard(new Card(Rank.K, Suit.SPADE));

                Player player = new Player("aa");
                player.addCard(new Card(Rank.TWO, Suit.DIAMOND));
                player.addCard(new Card(Rank.THREE, Suit.CLOVER));

                PlayersDto playersDto = new PlayersDto(List.of(player), dealer);
                List<HandDto> actual = blackjackConverter.convertHandDtoList(playersDto);

                // when & then
                assertThatThrownBy(() -> actual.add(new HandDto("cc", List.of("A하트"))))
                    .isInstanceOf(UnsupportedOperationException.class);
            }
        }
    }
}
