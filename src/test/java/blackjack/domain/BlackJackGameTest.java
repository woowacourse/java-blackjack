package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.generator.RandomDeckGenerator;
import blackjack.domain.card.generator.TestDeckGenerator;
import blackjack.domain.result.Score;
import blackjack.dto.domain.CardAndScore;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BlackJackGameTest {

    private final Card spadeAce = new Card(CardShape.SPADE, CardNumber.ACE);
    private final Card cloverTen = new Card(CardShape.CLOVER, CardNumber.TEN);
    private final Card cloverNine = new Card(CardShape.CLOVER, CardNumber.NINE);
    private final Card heartJack = new Card(CardShape.HEART, CardNumber.JACK);
    private final Card heartNine = new Card(CardShape.HEART, CardNumber.NINE);
    private final Card diamondFour = new Card(CardShape.DIAMOND, CardNumber.FOUR);
    private final Card diamondTwo = new Card(CardShape.DIAMOND, CardNumber.TWO);
    private final Card diamondSix = new Card(CardShape.DIAMOND, CardNumber.SIX);
    private final Card diamondSeven = new Card(CardShape.DIAMOND, CardNumber.SEVEN);
    private final Card diamondEight = new Card(CardShape.DIAMOND, CardNumber.EIGHT);

    private final List<Card> testCards = List.of(
            spadeAce, cloverTen, cloverNine, heartJack, heartNine, diamondFour, diamondTwo, diamondSix, diamondSeven, diamondEight);


    @Test
    @DisplayName("게임 초기화 테스트")
    void initGame() {
        assertDoesNotThrow(() -> new BlackJackGame(List.of("필립", "홍실"), new TestDeckGenerator(testCards)));
    }

    @Test
    @DisplayName("유저들의 첫 패를 반환하는 기능 테스트")
    void getUsersInitialStatus() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립", "홍실")
                , new TestDeckGenerator(testCards));

        List<Card> initialCards = blackJackGame.getInitialHoldingCards("필립");

        assertThat(initialCards).containsExactlyInAnyOrderElementsOf(testCards.subList(0, 2));
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
    @DisplayName("플레이어 턴 진행 테스트")
    void playPlayerTest() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립"), new TestDeckGenerator(testCards));

        blackJackGame.playPlayer("필립");
        List<Card> cards = blackJackGame.getHandholdingCards("필립");

        assertThat(cards).containsExactly(spadeAce, cloverTen, heartNine);
    }

    /*
    필립: blackjack
    홍실: 19
    딜러: 13
     */
    @Test
    @DisplayName("플레이어 턴 진행 가능 여부 확인 테스트")
    void isPossibleDrawTest() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립", "홍실")
                , new TestDeckGenerator(testCards));

        assertSoftly(softly -> {
            softly.assertThat(blackJackGame.isPossibleToDraw("필립")).isFalse();
            softly.assertThat(blackJackGame.isPossibleToDraw("홍실")).isTrue();
        });

    }

    @Test
    @DisplayName("점수를 포함한 상태를 반환하는 기능 테스트")
    void getCardResult() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립"), new TestDeckGenerator(testCards));

        CardAndScore philip = blackJackGame.getCardAndScore("필립");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(philip.getCards()).contains(spadeAce, cloverTen);
            softly.assertThat(philip.getScore()).isEqualTo(new Score(21, 2));
        });
    }

    /*
    필립: blackjack
    홍실: 19
    딜러: 13
     */
    @Test
    @DisplayName("플레이어들의 승리 여부 반환 테스트")
    void getWinningResultTest() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of("필립", "홍실"), new TestDeckGenerator(testCards));

        blackJackGame.bet("필립", 1000);
        blackJackGame.bet("홍실", 1000);
        blackJackGame.judgeResults();

        assertSoftly(softly -> {
            softly.assertThat(blackJackGame.getDealerProfitAmount()).isEqualTo(-2500);
            softly.assertThat(blackJackGame.getPlayerProfitAmount("필립")).isEqualTo(1500);
            softly.assertThat(blackJackGame.getPlayerProfitAmount("홍실")).isEqualTo(1000);
        });
    }
}
