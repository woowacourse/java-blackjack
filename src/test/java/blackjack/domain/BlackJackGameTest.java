package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class BlackJackGameTest {
    final List<Card> testCards = List.of(new Card(CardShape.SPADE, CardNumber.ACE),
            new Card(CardShape.CLOVER, CardNumber.TEN),
            new Card(CardShape.CLOVER, CardNumber.NINE),
            new Card(CardShape.HEART, CardNumber.JACK),
            new Card(CardShape.HEART, CardNumber.NINE),
            new Card(CardShape.DIAMOND, CardNumber.FOUR));

    @Test
    @DisplayName("게임 초기화 테스트")
    void initGame() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립", "홍실")
                , new TestDeckGenerator(testCards));

        Assertions.assertThat(blackJackGame.getStatus().keySet())
                .contains("필립", "홍실", "딜러");
    }
}
