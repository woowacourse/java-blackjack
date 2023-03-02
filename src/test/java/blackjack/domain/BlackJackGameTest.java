package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackGameTest {
    final List<Card> testCards = List.of(new Card(CardShape.SPADE, CardNumber.ACE), // 필립
            new Card(CardShape.CLOVER, CardNumber.TEN),  // 필립
            new Card(CardShape.CLOVER, CardNumber.NINE),    // 홍실
            new Card(CardShape.HEART, CardNumber.JACK),     // 홍실
            new Card(CardShape.HEART, CardNumber.NINE),     // 딜러
            new Card(CardShape.DIAMOND, CardNumber.FOUR),   // 딜러
            new Card(CardShape.DIAMOND, CardNumber.TWO),
            new Card(CardShape.DIAMOND, CardNumber.SIX),
            new Card(CardShape.DIAMOND, CardNumber.SEVEN),
            new Card(CardShape.DIAMOND, CardNumber.EIGHT));

    @Test
    @DisplayName("게임 초기화 테스트")
    void initGame() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립", "홍실")
                , new TestDeckGenerator(testCards));

        assertThat(blackJackGame.getStatus().keySet())
                .contains("필립", "홍실", "딜러");
    }

    @Test
    @DisplayName("유저들의 첫 패를 반환하는 기능 테스트")
    void getUsersInitialStatus() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립", "홍실")
                , new TestDeckGenerator(testCards));

        List<Card> initialCards = blackJackGame.getInitialStatus().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toUnmodifiableList());
        assertThat(initialCards).containsExactlyInAnyOrderElementsOf(testCards.subList(0, 5));
    }

    @Test
    @DisplayName("딜러의 카드 합이 17이상이 될 떄까지 카드를 뽑는 기능 테스트")
    void playDealerTurnTest() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립", "홍실")
                , new TestDeckGenerator(testCards));

        int drawCount = blackJackGame.playDealerTurn();

        assertThat(drawCount).isEqualTo(2);
    }

}
