package blackjack.domain.user;

import blackjack.domain.result.GameResult;
import blackjack.domain.result.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.RandomDeckGenerator;
import blackjack.domain.card.generator.TestDeckGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class UsersTest {

    final List<Card> testCards = List.of(new Card(CardShape.SPADE, CardNumber.ACE),
            new Card(CardShape.CLOVER, CardNumber.TEN),
            new Card(CardShape.CLOVER, CardNumber.NINE),
            new Card(CardShape.HEART, CardNumber.JACK),
            new Card(CardShape.HEART, CardNumber.NINE),
            new Card(CardShape.DIAMOND, CardNumber.FOUR));

    @Test
    @DisplayName("유저들의 현 패를 반환하는 기능 테스트")
    void getUsersStatus() {
        final Users users = new Users(List.of("필립", "홍실")
                , new Deck(new TestDeckGenerator(testCards)));

        List<Card> initialCards = users.getHandholdingCards("필립");
        assertThat(initialCards).containsExactlyInAnyOrderElementsOf(testCards.subList(0, 2));
    }

    @Test
    @DisplayName("유저들의 첫 패를 반환하는 기능 테스트")
    void getUsersInitialStatus() {
        final Users users = new Users(List.of("필립", "홍실")
                , new Deck(new TestDeckGenerator(testCards)));

        List<Card> initialCards = users.getInitialHoldingCards("필립");
        assertThat(initialCards).containsExactlyInAnyOrderElementsOf(testCards.subList(0, 2));
    }

    @Test
    @DisplayName("딜러의 스코어가 16이 넘는지 확인하는 기능 테스트")
    void isDealerOverDrawLimitTest() {
        final Users users = new Users(List.of("필립"), new Deck(new TestDeckGenerator(testCards)));

        boolean dealerOverDrawLimit = users.isDealerOverDrawLimit();

        assertThat(dealerOverDrawLimit).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드를 하나 추가하는 기능 테스트")
    void drawDealerTest() {
        final Deck deck = new Deck(new TestDeckGenerator(testCards));
        final Users users = new Users(List.of("필립"), deck);

        users.drawDealer(deck);
        int dealerCardCount = users.getHandholdingCards(Dealer.DEALER_NAME_CODE).size();

        assertThat(dealerCardCount).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어 리스트를 반환하는 기능 테스트")
    void getPlayersTest() {
        final Users users = new Users(List.of("필립"), new Deck(new RandomDeckGenerator()));

        final List<String> players = users.getPlayerNames();

        assertThat(players).containsExactly("필립");
    }

    /*
    필립: blackjack
    홍실: 19
    딜러: 13
     */
    @Test
    @DisplayName("플레이어들의 승리 여부 반환 테스트")
    void getWinningResultTest() {
        final Users users = new Users(List.of("필립", "홍실"), new Deck(new TestDeckGenerator(testCards)));

        Map<String, GameResult> winningResult = users.getGameResult();

        assertSoftly(softly -> {
            softly.assertThat(winningResult.get("필립")).isEqualTo(GameResult.BLACKJACK_WIN);
            softly.assertThat(winningResult.get("홍실")).isEqualTo(GameResult.NORMAL_WIN);
        });
    }

    /*
    필립: blackjack
    홍실: 19
     */
    @Test
    @DisplayName("이름으로 점수 확인 테스트")
    void getScoreTest() {
        final Users users = new Users(List.of("필립", "홍실"), new Deck(new TestDeckGenerator(testCards)));

        assertSoftly(softly -> {
            softly.assertThat(users.getScore("필립")).isEqualTo(new Score(21, 2));
            softly.assertThat(users.getScore("홍실")).isEqualTo(new Score(19, 2));
        });
    }

}
