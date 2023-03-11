package blackjack.domain;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import blackjack.controller.DrawOrStay;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.RandomDeckGenerator;
import blackjack.domain.card.TestNonShuffledDeckGenerator;
import blackjack.domain.money.Money;
import blackjack.domain.result.CardResult;
import blackjack.domain.result.WinningStatus;
import blackjack.domain.user.Name;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    private static final Name TEST_PLAYER_NAME1 = new Name("필립");
    private static final Name TEST_PLAYER_NAME2 = new Name("홍실");
    private static final Name DEALER_NAME = new Name("딜러");

    private final List<Card> testCards = List.of(new Card(CardShape.SPADE, CardNumber.ACE),
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
        final BlackJackGame blackJackGame = new BlackJackGame(List.of(TEST_PLAYER_NAME1.getValue()
                , TEST_PLAYER_NAME2.getValue())
                , new TestNonShuffledDeckGenerator(testCards));

        assertThat(blackJackGame.getPlayerNames())
                .containsExactly(TEST_PLAYER_NAME1, TEST_PLAYER_NAME2);
    }

    @Test
    @DisplayName("유저들의 첫 패를 반환하는 기능 테스트")
    void getUsersInitialStatus() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of(TEST_PLAYER_NAME1.getValue()
                , TEST_PLAYER_NAME2.getValue())
                , new TestNonShuffledDeckGenerator(testCards));

        final Map<Name, CardGroup> userNameAndFirstOpenCardGroups = blackJackGame.getUserNameAndFirstOpenCardGroups();

        assertSoftly(softly -> {
            softly.assertThat(userNameAndFirstOpenCardGroups.get(TEST_PLAYER_NAME1).getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(2, 4));
            softly.assertThat(userNameAndFirstOpenCardGroups.get(TEST_PLAYER_NAME2).getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(4, 6));
            softly.assertThat(userNameAndFirstOpenCardGroups.get(DEALER_NAME).getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(0, 1));
        });
    }

    @Test
    @DisplayName("딜러의 카드를 하나 뽑는 기능 테스트")
    void playDealerTurnTest() {
        final List<Card> cards = List.of(new Card(CardShape.SPADE, CardNumber.FIVE),
                new Card(CardShape.SPADE, CardNumber.TWO),
                new Card(CardShape.SPADE, CardNumber.QUEEN));
        final BlackJackGame blackJackGame = new BlackJackGame(Collections.emptyList(),
                new TestNonShuffledDeckGenerator(cards));

        blackJackGame.drawDealer();
        final int dealerCardSize = blackJackGame.getUserNameAndCardResults()
                .get(DEALER_NAME)
                .getCards()
                .getCards()
                .size();

        assertThat(dealerCardSize).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러가 카드를 추가로 뽑아야 하는지를 반환하는 기능 테스트")
    void shouldDealerDrawTest() {
        final List<Card> cards = List.of(new Card(CardShape.SPADE, CardNumber.FIVE),
                new Card(CardShape.SPADE, CardNumber.TWO),
                new Card(CardShape.SPADE, CardNumber.QUEEN));
        final BlackJackGame blackJackGame = new BlackJackGame(Collections.emptyList(),
                new TestNonShuffledDeckGenerator(cards));

        assertThat(blackJackGame.shouldDealerDraw()).isTrue();
    }

    @Test
    @DisplayName("플레이어 이름 리스트를 반환하는 기능 테스트")
    void getPlayersTest() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of(TEST_PLAYER_NAME1.getValue()),
                new RandomDeckGenerator());

        final List<Name> players = blackJackGame.getPlayerNames();

        assertThat(players).containsExactly(TEST_PLAYER_NAME1);
    }

    @Test
    @DisplayName("플레이어 턴 진행 테스트")
    void playPlayerTest() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of(TEST_PLAYER_NAME1.getValue()),
                new TestNonShuffledDeckGenerator(testCards));

        blackJackGame.playPlayer(TEST_PLAYER_NAME1, DrawOrStay.DRAW);
        List<Card> cards = blackJackGame.getCardGroupBy(TEST_PLAYER_NAME1).getCards();

        assertThat(cards).containsExactlyInAnyOrderElementsOf(testCards.subList(2, 5));
    }

    @Test
    @DisplayName("점수를 포함한 상태를 반환하는 기능 테스트")
    void getCardResult() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of(TEST_PLAYER_NAME1.getValue()),
                new TestNonShuffledDeckGenerator(testCards));

        final CardResult philip = blackJackGame.getUserNameAndCardResults().get(TEST_PLAYER_NAME1);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(philip.getCards().getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(2, 4));
            softly.assertThat(philip.getScore().getValue()).isEqualTo(19);
        });
    }

    @Test
    @DisplayName("플레이어들의 승리 여부 반환 테스트")
    void getWinningResultTest() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of(TEST_PLAYER_NAME1.getValue(),
                TEST_PLAYER_NAME2.getValue()),
                new TestNonShuffledDeckGenerator(testCards));

        final Map<Name, WinningStatus> winningResult = blackJackGame.getPlayersWinningResults();
        final Map<Name, CardResult> userNameAndCardResults = blackJackGame.getUserNameAndCardResults();

        assertThat(userNameAndCardResults.get(DEALER_NAME).getScore().getValue()).isEqualTo(21);
        assertThat(userNameAndCardResults.get(TEST_PLAYER_NAME1).getScore().getValue()).isEqualTo(19);
        assertThat(userNameAndCardResults.get(TEST_PLAYER_NAME2).getScore().getValue()).isEqualTo(13);

        assertSoftly(softly -> {
            softly.assertThat(winningResult.get(TEST_PLAYER_NAME1)).isEqualTo(WinningStatus.LOSE);
            softly.assertThat(winningResult.get(TEST_PLAYER_NAME2)).isEqualTo(WinningStatus.LOSE);
        });
    }

    @Test
    @DisplayName("유저(플레이어+딜러)의 이름과 카드목록 점수를 반환하는 기능 테스트")
    void getUserNamesAndResultsTest() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of(TEST_PLAYER_NAME1.getValue()),
                new TestNonShuffledDeckGenerator(testCards));

        final Map<Name, CardResult> userNameAndCardResults = blackJackGame.getUserNameAndCardResults();

        assertSoftly(softly -> {
            softly.assertThat(userNameAndCardResults.get(DEALER_NAME).getCards().getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(0, 2));
            softly.assertThat(userNameAndCardResults.get(DEALER_NAME).getScore().getValue())
                    .isEqualTo(21);
            softly.assertThat(userNameAndCardResults.get(TEST_PLAYER_NAME1).getCards().getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(2, 4));
            softly.assertThat(userNameAndCardResults.get(TEST_PLAYER_NAME1).getScore().getValue())
                    .isEqualTo(19);
        });
    }

    @Test
    @DisplayName("플레이어가 돈을 배팅하는 기능 추가")
    void betPlayerTest() {
        final BlackJackGame blackJackGame = new BlackJackGame(List.of(TEST_PLAYER_NAME1.getValue()),
                new RandomDeckGenerator());

        blackJackGame.betPlayer(TEST_PLAYER_NAME1, 10000);

        assertThat(blackJackGame).extracting("deposit")
                .extracting("deposit", InstanceOfAssertFactories.map(Name.class, Money.class))
                .containsExactly(entry(TEST_PLAYER_NAME1, new Money(10000)));
    }

    @Test
    @DisplayName("플레이어의 이름과 수익금들을 반환하는 기능 추가")
    void getPlayerNameAndProfitRates() {
        final BlackJackGame blackJackGame = new BlackJackGame(
                List.of(TEST_PLAYER_NAME1.getValue(), TEST_PLAYER_NAME2.getValue()),
                new TestNonShuffledDeckGenerator(testCards));
        blackJackGame.betPlayer(TEST_PLAYER_NAME1, 1_000);
        blackJackGame.betPlayer(TEST_PLAYER_NAME2, 2_000);

        final Map<Name, Money> playerNameAndProfits = blackJackGame.getPlayerNameAndProfits();
        final Money player1Profit = playerNameAndProfits.get(TEST_PLAYER_NAME1);
        final Money player2Profit = playerNameAndProfits.get(TEST_PLAYER_NAME2);

        assertSoftly(softly -> {
            softly.assertThat(player1Profit.getValue()).isEqualTo(-1_000);
            softly.assertThat(player2Profit.getValue()).isEqualTo(-2_000);
        });
    }
}
