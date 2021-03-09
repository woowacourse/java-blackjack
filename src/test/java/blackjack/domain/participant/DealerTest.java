package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardSuit;
import blackjack.domain.result.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class DealerTest {
    private Dealer dealer;
    private Player player;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player = new Player("joel");
    }

    @Test
    @DisplayName("딜러가 잘 생성되었는지 확인")
    void create() {
        assertThatCode(() -> new Dealer())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러가 Participant로 부터 상속받았는지 확인")
    void extend() {
        final Participant participant = new Dealer();
        participant.receiveAdditionalCard(new Card(CardLetter.ACE, CardSuit.HEART));
        assertThat(participant.getName()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인")
    void canGetMoreCard() {
        dealer.receiveAdditionalCard(new Card(CardLetter.TWO, CardSuit.CLOVER));
        dealer.receiveAdditionalCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
        assertThat(dealer.canGetMoreCard()).isTrue();
    }

    @Test
    @DisplayName("상대가 버스트라면 무조건 딜러는 승리한다.")
    void generateResultWhenOpponentIsBust() {
        player.receiveAdditionalCard(new Card(CardLetter.JACK, CardSuit.CLOVER));
        player.receiveAdditionalCard(new Card(CardLetter.QUEEN, CardSuit.CLOVER));
        player.receiveAdditionalCard(new Card(CardLetter.KING, CardSuit.CLOVER));

        dealer.receiveAdditionalCard(new Card(CardLetter.EIGHT, CardSuit.DIAMOND));
        dealer.receiveAdditionalCard(new Card(CardLetter.NINE, CardSuit.DIAMOND));

        assertThat(dealer.generateResult(player)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("상대가 버스트가 아니고 딜러가 버스트라면 패배한다.")
    void generateResultWhenDealerIsBust() {
        player.receiveAdditionalCard(new Card(CardLetter.JACK, CardSuit.CLOVER));
        player.receiveAdditionalCard(new Card(CardLetter.QUEEN, CardSuit.CLOVER));

        dealer.receiveAdditionalCard(new Card(CardLetter.EIGHT, CardSuit.DIAMOND));
        dealer.receiveAdditionalCard(new Card(CardLetter.NINE, CardSuit.DIAMOND));
        dealer.receiveAdditionalCard(new Card(CardLetter.TEN, CardSuit.DIAMOND));

        assertThat(dealer.generateResult(player)).isEqualTo(Result.LOSE);
    }

    @ParameterizedTest
    @CsvSource(value = {"TEN,NINE:WIN", "TEN,SEVEN:DRAW", "SIX,SEVEN:LOSE"}, delimiter = ':')
    @DisplayName("상대와 딜러 모두 버스트가 아니라면, 점수 합계로 승무패를 가린다")
    void generateResultByScore(final String dealerCardInput, final String expectedResult) {
        player.receiveAdditionalCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
        player.receiveAdditionalCard(new Card(CardLetter.SEVEN, CardSuit.CLOVER));

        final String[] dealerCards = dealerCardInput.split(",");
        for (final String dealerCard : dealerCards) {
            final CardLetter cardLetter = CardLetter.valueOf(dealerCard);
            dealer.receiveAdditionalCard(new Card(cardLetter, CardSuit.DIAMOND));
        }
        assertThat(dealer.generateResult(player)).isEqualTo(Result.valueOf(expectedResult));
    }

    @Test
    @DisplayName("딜러와 플레이어 사이의 모든 게임 결과를 계산한다")
    void generateEveryResult() {
        //Given
        Player player1 = new Player("joel");
        Player player2 = new Player("bada");
        Player player3 = new Player("j.on");

        player1.receiveAdditionalCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
        player1.receiveAdditionalCard(new Card(CardLetter.JACK, CardSuit.CLOVER));

        player2.receiveAdditionalCard(new Card(CardLetter.EIGHT, CardSuit.HEART));
        player2.receiveAdditionalCard(new Card(CardLetter.NINE, CardSuit.HEART));

        player3.receiveAdditionalCard(new Card(CardLetter.TWO, CardSuit.DIAMOND));
        player3.receiveAdditionalCard(new Card(CardLetter.THREE, CardSuit.DIAMOND));

        dealer.receiveAdditionalCard(new Card(CardLetter.EIGHT, CardSuit.SPADE));
        dealer.receiveAdditionalCard(new Card(CardLetter.NINE, CardSuit.SPADE));
        //When
        final Map<Result, Integer> dealerResult =
                dealer.generateEveryResult(new Players(Arrays.asList(player1, player2, player3)));
        //Then
        assertThat(dealerResult.getOrDefault(Result.WIN, 0)).isEqualTo(1);
        assertThat(dealerResult.getOrDefault(Result.DRAW, 0)).isEqualTo(1);
        assertThat(dealerResult.getOrDefault(Result.LOSE, 0)).isEqualTo(1);
    }
}
