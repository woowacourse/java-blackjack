package domain.participant;

import domain.Deck;
import domain.GameResults;
import domain.PlayingCard;
import domain.PlayingCardValue;
import domain.constant.GameResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static domain.PlayingCardShape.DIAMOND;
import static domain.PlayingCardValue.*;
import static domain.constant.GameResult.LOSE;
import static domain.constant.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @DisplayName("딜러 인스턴스를 생성한다.")
    @Test
    void createDealerTest() {
        // When
        Dealer dealer = Dealer.init();

        // Then
        assertThat(dealer).isNotNull();
    }

    @DisplayName("딜러의 손패 합이 16이하이면 true를 반환한다.")
    @Test
    void isDrawableTest() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(DIAMOND, EIGHT));
        Hand hand = Hand.init();
        playingCards.forEach(hand::addCard);
        Dealer dealer = new Dealer(hand);

        // When
        boolean isDrawable = dealer.isDrawable();

        // Then
        assertThat(isDrawable).isTrue();
    }

    @DisplayName("딜러는 덱으로부터 카드를 한 장 뽑는다.")
    @Test
    void drawTest() {
        // Given
        Deck deck = Deck.init();
        Hand initHand = Hand.init();
        int initCardNumberSum = initHand.getCardsNumberSum();
        Dealer dealer = new Dealer(initHand);

        // When
        dealer.draw(deck);

        // Then
        assertThat(initCardNumberSum).isNotEqualTo(initHand.getCardsNumberSum());
    }

    @DisplayName("딜러와 플레이어의 게임 결과를 반환한다.")
    @ParameterizedTest
    @MethodSource("getGameResultsTestParameters")
    void getGameResultsTest(PlayingCardValue playingCardValue, List<GameResult> dealerGameResult) {
        // Given
        Hand playerHand = Hand.init();
        List.of(new PlayingCard(DIAMOND, NINE)).forEach(playerHand::addCard);
        Hand dealerHand = Hand.init();
        List.of(new PlayingCard(DIAMOND, playingCardValue)).forEach(dealerHand::addCard);
        List<Player> players = List.of(new Player(new PlayerName("kelly"), playerHand));
        Dealer dealer = new Dealer(dealerHand);

        // When
        GameResults gameResults = dealer.getGameResults(players);

        // Then
        Assertions.assertThat(gameResults.dealerGameResult()).isEqualTo(dealerGameResult);
    }

    private static Stream<Arguments> getGameResultsTestParameters() {
        return Stream.of(
                Arguments.of(EIGHT, List.of(LOSE)),
                Arguments.of(TEN, List.of(WIN))
        );
    }
}
