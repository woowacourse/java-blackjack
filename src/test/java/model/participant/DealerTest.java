package model.participant;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import card.*;
import game.BlackJack;
import participant.Dealer;
import participant.Participant;
import participant.Player;
import participant.Players;
import score.MatchResultType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static util.TestCardDistributor.divideCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DealerTest {

    private final CardDeck deck = new CardDeck(new ShuffledDeckGenerator().generateDeck());

    @Test
    @DisplayName("딜러 객체가 잘 생성되는 지")
    void newDealer() {

        // given
        final Participant dealer = new Dealer();
        String expected = "딜러";

        // when
        String nickname = dealer.getNickname();

        // then
        assertThat(nickname).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드 추가 기능이 잘 작동하는 지")
    void addCardsSuccess() {

        // given
        final Card card = deck.pickCard();

        // when
        Dealer dealer = new Dealer();
        dealer.addCard(card);

        // then
        assertThat(dealer.getCards()).contains(card);
    }

    @Test
    @DisplayName("딜러의 핸즈에 있는 카드 랭크의 합을 잘 구하는 지")
    void sum() {
        // given
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, NormalRank.FIVE),
                new Card(Suit.CLUBS, NormalRank.FOUR)
        );

        int expected = cards.stream()
                .mapToInt(card -> card.getRank().getScore())
                .sum();

        Dealer dealer = new Dealer();
        divideCard(cards,dealer);

        // when
        int sum = dealer.getScore().getValue();

        // then
        assertThat(sum).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러 카드 합산이 조건에 부합하는 지 판별: true")
    void isNotUpTrue() {

        // given
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, NormalRank.JACK),
                new Card(Suit.CLUBS, NormalRank.FOUR)
        );

        Dealer dealer = new Dealer();
        divideCard(cards, dealer);

        // when
        // then
        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("딜러 카드 합산이 조건에 부합하는 지 판별: false")
    void isNotUpFalse() {

        // given
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, NormalRank.JACK),
                new Card(Suit.CLUBS, NormalRank.KING)
        );

        Dealer dealer = new Dealer();
        divideCard(cards, dealer);

        // when
        // then
        assertThat(dealer.canHit()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("createCards")
    @DisplayName("딜러의 게임 결과가 제대로 생성되는지")
    void dealerGameResult(List<Card> playerCards, List<Card> dealerCards,
                          MatchResultType playerMatchResultType, MatchResultType dealerMatchResultType) {
        Dealer dealer = new Dealer();
        divideCard(dealerCards, dealer);

        Players players = Players.from(List.of("hippo"));
        Player player = players.getPlayers().getFirst();
        divideCard(playerCards, player);

        BlackJack blackJack = new BlackJack(players, dealer, deck);
        Map<MatchResultType, Integer> dealerMatchResult = blackJack.calculateMatchResult();

        assertAll(
                () -> assertThat(player.getMatchType()).isEqualTo(playerMatchResultType),
                () -> assertThat(dealerMatchResult.get(dealerMatchResultType)).isEqualTo(1)
        );
    }

    private static Stream<Arguments> createCards() {
        return Stream.of(
                Arguments.arguments(
                        List.of(
                                new Card(Suit.HEARTS, NormalRank.JACK),
                                new Card(Suit.CLUBS, NormalRank.KING)
                        ),
                        List.of(
                                new Card(Suit.HEARTS, NormalRank.TWO),
                                new Card(Suit.CLUBS, NormalRank.KING)
                        ),
                        MatchResultType.WIN,
                        MatchResultType.LOSE
                ),
                Arguments.arguments(
                        List.of(
                                new Card(Suit.HEARTS, NormalRank.THREE),
                                new Card(Suit.CLUBS, NormalRank.FIVE)
                        ),
                        List.of(
                                new Card(Suit.HEARTS, NormalRank.TWO),
                                new Card(Suit.CLUBS, AceRank.SOFT_ACE)
                        ),
                        MatchResultType.LOSE,
                        MatchResultType.WIN
                ),
                Arguments.arguments(
                        List.of(
                                new Card(Suit.HEARTS, NormalRank.THREE),
                                new Card(Suit.CLUBS, NormalRank.TEN)
                        ),
                        List.of(
                                new Card(Suit.HEARTS, NormalRank.TWO),
                                new Card(Suit.CLUBS, AceRank.SOFT_ACE)
                        ),
                        MatchResultType.DRAW,
                        MatchResultType.DRAW
                )
        );
    }

}
