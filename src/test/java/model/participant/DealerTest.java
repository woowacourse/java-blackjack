package model.participant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import model.card.*;
import model.score.MatchType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DealerTest {

    @Test
    @DisplayName("딜러 객체가 잘 생성되는 지")
    void newDealer() {

        // given
        final Participant dealer = Dealer.from(new CardDeck());
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
        final Card card = new CardDeck().pickCard();

        // when
        Participant dealer = Dealer.from(new CardDeck());
        dealer.addCard(card);

        // then
        assertThat(dealer.getCards()).contains(card);
    }

    @Test
    @DisplayName("딜러의 핸즈에 있는 카드 랭크의 합을 잘 구하는 지")
    void sum() {
        // given
        // 총 합이 9
        List<Card> divideCards = List.of(
                new Card(Suit.HEARTS, NormalRank.FIVE),
                new Card(Suit.CLUBS, NormalRank.FOUR)
        );
        int expected = divideCards.stream()
                .mapToInt(card -> card.getRank().getScore())
                .sum();

        Participant dealer = Dealer.from(new CardDeck());
        for (Card divideCard : divideCards) {
            dealer.addCard(divideCard);
        }

        // when
        int sum = dealer.getScore();

        // then
        assertThat(sum).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러 카드 합산이 조건에 부합하는 지 판별: true")
    void isNotUpTrue() {

        // given
        List<Card> divideCards = List.of(
                new Card(Suit.HEARTS, NormalRank.JACK),
                new Card(Suit.CLUBS, NormalRank.FOUR)
        );

        Dealer dealer = Dealer.from(new CardDeck());
        for (Card divideCard : divideCards) {
            dealer.addCard(divideCard);
        }
        // when
        // then
        assertThat(dealer.isHit()).isTrue();
    }

    @Test
    @DisplayName("딜러 카드 합산이 조건에 부합하는 지 판별: false")
    void isNotUpFalse() {

        // given
        List<Card> divideCards = List.of(
                new Card(Suit.HEARTS, NormalRank.JACK),
                new Card(Suit.CLUBS, NormalRank.KING)
        );

        Dealer dealer = Dealer.from(new CardDeck());
        for (Card divideCard : divideCards) {
            dealer.addCard(divideCard);
        }
        // when
        // then
        assertThat(dealer.isHit()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("createCards")
    @DisplayName("딜러의 게임 결과가 제대로 생성되는지")
    void dealerGameResult(List<Card> playerCards, List<Card> dealerCards,
                          MatchType playerMatchType, MatchType dealerMatchType) {
        Dealer dealer = Dealer.from(new CardDeck());
        for (Card card : dealerCards) {
            dealer.addCard(card);
        }

        Players players = Players.from(List.of("hippo"));
        Player player = players.getPlayers().getFirst();
        for (Card card : playerCards) {
            player.addCard(card);
        }

        Map<MatchType, Integer> dealerMatchResult = dealer.calculateVictory(players);

        assertAll(
                () -> assertThat(player.getMatchType()).isEqualTo(playerMatchType),
                () -> assertThat(dealerMatchResult.get(dealerMatchType)).isEqualTo(1)
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
                        MatchType.WIN,
                        MatchType.LOSE
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
                        MatchType.LOSE,
                        MatchType.WIN
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
                        MatchType.DRAW,
                        MatchType.DRAW
                )
        );
    }
}
