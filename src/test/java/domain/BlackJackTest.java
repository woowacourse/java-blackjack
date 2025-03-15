package domain;

import domain.card.*;
import domain.card.cardsGenerator.RandomCardsGenerator;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackTest {
    private static final Deck deck = new Deck(new RandomCardsGenerator());

    @DisplayName("플레이어들의 승패를 결정한다")
    @Test
    void test() {
        //given
        Cards dealerCards = Cards.of(
                List.of(
                        new Card(CardNumber.TEN, CardShape.CLOVER),
                        new Card(CardNumber.THREE, CardShape.CLOVER)
                )
        );
        Dealer dealer = new Dealer(dealerCards, deck);

        Cards player1Cards = Cards.of(
                List.of(
                        new Card(CardNumber.TEN, CardShape.CLOVER),
                        new Card(CardNumber.TWO, CardShape.CLOVER)
                )
        );
        Player player1 = Player.from(new Nickname("플레이어1"), player1Cards);

        Cards player2Cards = Cards.of(
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.FOUR, CardShape.CLOVER)
                )
        );
        Player player2 = Player.from(new Nickname("플레이어2"), player2Cards);

        Map<Player, GameResult> expected = Map.of(
                player1, GameResult.LOSE,
                player2, GameResult.WIN
        );

        BlackJack blackJack = new BlackJack(dealer, Players.withoutBetting(Set.of(player1, player2)));

        //when
        Map<Player, GameResult> actual = blackJack.getPlayersResult();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("올바른 수익을 계산할 수 있다.")
    @ParameterizedTest(name = "{0}")
    @MethodSource("testValues")
    void testRevenues(String description, Player player, Dealer dealer, final int bettingCost, final int playerRevenue) {
        // given
        Map<Player, Money> bettingPlayers = Map.of(
                player, new Money(bettingCost)
        );
        BlackJack blackJack = new BlackJack(dealer, new Players(bettingPlayers));

        // when
        Map<Player, Integer> revenueResult = blackJack.getPlayerRevenues();
        final int dealerRevenue = blackJack.getDealerRevenue();

        // then
        SoftAssertions.assertSoftly((softly) -> {
            softly.assertThat(revenueResult.get(player)).isEqualTo(playerRevenue);
            softly.assertThat(dealerRevenue).isEqualTo(-1 * playerRevenue);
        });
    }

    static Stream<Arguments> testValues() {
        return Stream.of(
                Arguments.arguments(
                        "플레이어가 블랙잭으로 승리한 경우, 수익을 구할 수 있다.",
                        Player.from(
                                new Nickname("player"),
                                Cards.of(List.of(
                                        new Card(CardNumber.A, CardShape.SPADE),
                                        new Card(CardNumber.TEN, CardShape.HEART)
                                ))
                        ),
                        new Dealer(
                                Cards.of(List.of(
                                        new Card(CardNumber.SEVEN, CardShape.DIAMOND),
                                        new Card(CardNumber.QUEEN, CardShape.HEART)
                                )),
                                deck
                        ),
                        10000, // betting
                        15000 // revenue
                ),
                Arguments.arguments(
                        "플레이어가 일반 21로 승리한 경우, 수익을 구할 수 있다.",
                        Player.from(
                                new Nickname("player"),
                                Cards.of(List.of(
                                        new Card(CardNumber.QUEEN, CardShape.SPADE),
                                        new Card(CardNumber.TEN, CardShape.HEART),
                                        new Card(CardNumber.A, CardShape.DIAMOND)
                                ))
                        ),
                        new Dealer(
                                Cards.of(List.of(
                                        new Card(CardNumber.SEVEN, CardShape.DIAMOND),
                                        new Card(CardNumber.QUEEN, CardShape.HEART)
                                )),
                                deck
                        ),
                        10000, // betting
                        10000 // revenue
                ),
                Arguments.arguments(
                        "플레이어가 블랙잭으로 비긴 경우, 수익을 구할 수 있다.",
                        Player.from(
                                new Nickname("player"),
                                Cards.of(List.of(
                                        new Card(CardNumber.A, CardShape.SPADE),
                                        new Card(CardNumber.TEN, CardShape.HEART)
                                ))
                        ),
                        new Dealer(
                                Cards.of(List.of(
                                        new Card(CardNumber.A, CardShape.DIAMOND),
                                        new Card(CardNumber.QUEEN, CardShape.HEART)
                                )),
                                deck
                        ),
                        10000, // betting
                        0 // revenue
                ),
                Arguments.arguments(
                        "플레이어가 그냥 비긴 경우, 수익을 구할 수 있다.",
                        Player.from(
                                new Nickname("player"),
                                Cards.of(List.of(
                                        new Card(CardNumber.EIGHT, CardShape.SPADE),
                                        new Card(CardNumber.TEN, CardShape.HEART)
                                ))
                        ),
                        new Dealer(
                                Cards.of(List.of(
                                        new Card(CardNumber.NINE, CardShape.DIAMOND),
                                        new Card(CardNumber.NINE, CardShape.HEART)
                                )),
                                deck
                        ),
                        10000, // betting
                        0 // revenue
                ),
                Arguments.arguments(
                        "플레이어가 버스트 되어 패배한 경우, 수익을 구할 수 있다.",
                        Player.from(
                                new Nickname("player"),
                                Cards.of(List.of(
                                        new Card(CardNumber.QUEEN, CardShape.SPADE),
                                        new Card(CardNumber.TEN, CardShape.HEART),
                                        new Card(CardNumber.EIGHT, CardShape.DIAMOND)
                                ))
                        ),
                        new Dealer(
                                Cards.of(List.of(
                                        new Card(CardNumber.SEVEN, CardShape.DIAMOND),
                                        new Card(CardNumber.QUEEN, CardShape.HEART)
                                )),
                                deck
                        ),
                        10000, // betting
                        -10000 // revenue
                ),
                Arguments.arguments(
                        "딜러가 버스트 되어 승리한 경우, 수익을 구할 수 있다.",
                        Player.from(
                                new Nickname("player"),
                                Cards.of(List.of(
                                        new Card(CardNumber.EIGHT, CardShape.SPADE),
                                        new Card(CardNumber.TEN, CardShape.HEART)
                                ))
                        ),
                        new Dealer(
                                Cards.of(List.of(
                                        new Card(CardNumber.TWO, CardShape.DIAMOND),
                                        new Card(CardNumber.QUEEN, CardShape.HEART),
                                        new Card(CardNumber.JACK, CardShape.SPADE)
                                )),
                                deck
                        ),
                        10000, // betting
                        10000 // revenue
                ),
                Arguments.arguments(
                        "플레이어의 점수가 낮아서 패배한 경우 수익을 구할 수 있다.",
                        Player.from(
                                new Nickname("player"),
                                Cards.of(List.of(
                                        new Card(CardNumber.SIX, CardShape.SPADE),
                                        new Card(CardNumber.TEN, CardShape.HEART)
                                ))
                        ),
                        new Dealer(
                                Cards.of(List.of(
                                        new Card(CardNumber.SEVEN, CardShape.DIAMOND),
                                        new Card(CardNumber.QUEEN, CardShape.HEART)
                                )),
                                deck
                        ),
                        10000, // betting
                        -10000 // revenue
                )
        );
    }

    @DisplayName("모든 플레이어의 수익을 올바르게 반환할 수 있다.")
    @Test
    void allParticipantsRevenue() {
        // given
        Dealer dealer = new Dealer(
                Cards.of(List.of(
                        new Card(CardNumber.A, CardShape.HEART),
                        new Card(CardNumber.SIX, CardShape.SPADE)
                )),
                deck
        );
        Player blackjackPlayer = Player.from(
                new Nickname("blackjack"),
                Cards.of(List.of(
                        new Card(CardNumber.A, CardShape.SPADE),
                        new Card(CardNumber.JACK, CardShape.HEART)
                ))
        );
        Player bustPlayer = Player.from(
                new Nickname("bust"),
                Cards.of(List.of(
                        new Card(CardNumber.TEN, CardShape.HEART),
                        new Card(CardNumber.EIGHT, CardShape.SPADE),
                        new Card(CardNumber.SIX, CardShape.DIAMOND)
                ))
        );
        Player normalWinPlayer = Player.from(
                new Nickname("normalWin"),
                Cards.of(List.of(
                        new Card(CardNumber.TEN, CardShape.SPADE),
                        new Card(CardNumber.QUEEN, CardShape.HEART)
                ))
        );
        Player losePlayer = Player.from(
                new Nickname("lose"),
                Cards.of(List.of(
                        new Card(CardNumber.KING, CardShape.SPADE),
                        new Card(CardNumber.SIX, CardShape.HEART)
                ))
        );
        Map<Player, Money> bettingPlayers = Map.of(
                blackjackPlayer, new Money(10000),
                bustPlayer, new Money(20000),
                normalWinPlayer, new Money(30000),
                losePlayer, new Money(5000)
        );
        BlackJack blackJack = new BlackJack(dealer, new Players(bettingPlayers));
        final int expectedDealerRevenue = (int) (10000 * 1.5) * -1 + // blackjack
                20000 + // bust
                30000 * -1 + // normalWin
                5000; // lose

        // when
        final int dealerRevenue = blackJack.getDealerRevenue();
        Map<Player, Integer> revenueResult = blackJack.getPlayerRevenues();

        // then
        SoftAssertions.assertSoftly((softly) -> {
            softly.assertThat(dealerRevenue).isEqualTo(expectedDealerRevenue);
            softly.assertThat(revenueResult.get(blackjackPlayer)).isEqualTo((int) (10000 * 1.5));
            softly.assertThat(revenueResult.get(bustPlayer)).isEqualTo(-20000);
            softly.assertThat(revenueResult.get(normalWinPlayer)).isEqualTo(30000);
            softly.assertThat(revenueResult.get(losePlayer)).isEqualTo(-5000);
        });
    }
}
