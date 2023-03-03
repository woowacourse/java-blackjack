package blackjack.domain;

import blackjack.dto.CardResult;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class BlackJackGameTest {
    final List<Card> testCards = List.of(new Card(CardShape.SPADE, CardNumber.ACE),
            new Card(CardShape.CLOVER, CardNumber.TEN),
            new Card(CardShape.CLOVER, CardNumber.NINE),
            new Card(CardShape.HEART, CardNumber.JACK),
            new Card(CardShape.HEART, CardNumber.NINE),
            new Card(CardShape.DIAMOND, CardNumber.FOUR),
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

    @Test
    @DisplayName("플레이어 이름 리스트를 반환하는 기능 테스트")
    void getPlayersTest() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립"), new RandomDeckGenerator());

        final List<String> players = blackJackGame.getPlayerNames();

        assertThat(players).containsExactly("필립");
    }

    @Test
    @DisplayName("플레이어 이름으로 bust됬는지 확인하는 기능 테스트")
    void isBustTest() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립"), new RandomDeckGenerator());

        boolean isBust = blackJackGame.isBust("필립");

        assertThat(isBust).isFalse();
    }

    @Test
    @DisplayName("플레이어 턴 진행 테스트")
    void playPlayerTest() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립"), new TestDeckGenerator(testCards));

        blackJackGame.playPlayer("필립");
        List<Card> cards = blackJackGame.getStatus().get("필립");

        assertThat(cards).containsExactly(new Card(CardShape.SPADE, CardNumber.ACE), // 필립
                new Card(CardShape.CLOVER, CardNumber.TEN),
                new Card(CardShape.HEART, CardNumber.NINE));

    }

    @Test
    @DisplayName("점수를 포함한 상태를 반환하는 기능 테스트")
    void getCardResult() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립"), new TestDeckGenerator(testCards));

        CardResult philip = blackJackGame.getCardResult().get("필립");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(philip.getCards()).contains(new Card(CardShape.SPADE, CardNumber.ACE)
                    , new Card(CardShape.CLOVER, CardNumber.TEN));
            softly.assertThat(philip.getScore()).isEqualTo(21);
        });
    }

    /*
    필립: 21
    홍실: 19
    딜러: 13
     */
    @Test
    @DisplayName("플레이어들의 승리 여부 반환 테스트")
    void getWinningResultTest() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립", "홍실"), new TestDeckGenerator(testCards));

        Map<String, WinningStatus> winningResult = blackJackGame.getWinningResult();

        assertSoftly(softly -> {
            softly.assertThat(winningResult.get("필립")).isEqualTo(WinningStatus.WIN);
            softly.assertThat(winningResult.get("홍실")).isEqualTo(WinningStatus.WIN);
        });
    }
    @Test
    @DisplayName("유저의 점수가 BlackJackNumber인지 확인하는 기능 테스트")
    void isBlackJackScoreTest() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립"),
                new TestDeckGenerator(testCards));

        boolean isBlackJack = blackJackGame.isBlackJackScore("필립");

        assertThat(isBlackJack).isTrue();
    }
}
