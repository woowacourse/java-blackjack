package domain.result;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.gamer.*;
import domain.result.score.Score;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackRuleTest {
    @Test
    void 점수_계산_테스트() {
        BlackJackRule blackJackRule = new BlackJackRule();

        Name name = new Name("phobi");
        Hand hand = new Hand();

        hand.drawCard(() -> Card.of(Rank.ACE, Suit.CLOVER));
        assertThat(blackJackRule.calculateScore(new Player(name, hand))).isEqualTo(Score.of(11));

        hand.drawCard(() -> Card.of(Rank.QUEEN, Suit.CLOVER));
        assertThat(blackJackRule.calculateScore(new Player(name, hand))).isEqualTo(Score.of(21));

        hand.drawCard(() -> Card.of(Rank.THREE, Suit.CLOVER));
        assertThat(blackJackRule.calculateScore(new Player(name, hand))).isEqualTo(Score.of(14));
    }

    private static Stream<Arguments> generateHandsAndMoney() {
        Money bettingMoney = new Money(1000);
        Money blackJackProfit = new Money(1500);
        Money drawProfit = new Money(0);
        Money loseProfit = new Money(-1000);

        return Stream.of(
                Arguments.of(generateHand("Ace", "K"), generateHand("2"), bettingMoney, blackJackProfit), // 블랙잭 승리
                Arguments.of(generateHand("K", "Q"), generateHand("2", "3"), bettingMoney, bettingMoney), // 그냥 승리
                Arguments.of(generateHand("K", "Q"), generateHand("K", "Q", "J"), bettingMoney, bettingMoney), // 딜러 버스트 승리
                Arguments.of(generateHand("Ace", "K"), generateHand("Ace", "J"), bettingMoney, drawProfit), // 블랙잭 무승부
                Arguments.of(generateHand("5", "6"), generateHand("4", "7"), bettingMoney, drawProfit), // 그냥 무승부
                Arguments.of(generateHand("5", "6"), generateHand("Ace", "K"), bettingMoney, loseProfit), // 그냥 패배
                Arguments.of(generateHand("K", "Q", "J"), generateHand("5", "K"), bettingMoney, loseProfit) // 버스트 패배
        );
    }

    private static Hand generateHand(String... ranks) {
        Hand resultHand = new Hand();

        Arrays.stream(ranks)
                .map(rank -> Card.of(Rank.of(rank), Suit.CLOVER))
                .forEach(card -> resultHand.drawCard(() -> card));

        return resultHand;
    }

    @ParameterizedTest
    @MethodSource("generateHandsAndMoney")
    void 플레이어_결과_계산_테스트(Hand playerHand, Hand dealerHand, Money bettingMoney, Money Profit) {
        BlackJackRule blackJackRule = new BlackJackRule();

        Player player = new Player(new Name("phobi"), playerHand, bettingMoney);
        Dealer dealer = new Dealer(dealerHand);

        assertThat(blackJackRule.derivePlayerResult(player, dealer).getProfit()).isEqualTo(Profit);
    }
}
