package domain.gamer;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.card.providable.CardDeck;
import domain.gamer.action.TurnActions;
import domain.gamer.action.YesNo;
import domain.result.BlackJackRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GamerTest {
    @Test
    void 처음에_카드_뽑기_테스트() {
        Player player = new Player(new Name("phobi"));
        CardDeck cardDeck = new CardDeck();

        player.drawInitialCards(cardDeck);

        assertThat(player.getHand().size()).isEqualTo(2);
    }

    @Test
    void 턴_진행_테스트() {
        BlackJackRule blackJackRule = new BlackJackRule();
        Player player = new Player(new Name("phobi"));
        CardDeck cardDeck = new CardDeck();

        player.playTurn(cardDeck, blackJackRule, new TurnActions(gamer -> YesNo.NO, gamer -> {
        }));
        assertThat(player.getHand().size()).isEqualTo(0);

        player.playTurn(cardDeck, blackJackRule, new TurnActions(gamer -> YesNo.YES, gamer -> {
        }));
        assertThat(player.getHand().size()).isGreaterThan(1);
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

        assertThat(player.determineResult(dealer, new BlackJackRule()).getProfit()).isEqualTo(Profit);
    }
}
