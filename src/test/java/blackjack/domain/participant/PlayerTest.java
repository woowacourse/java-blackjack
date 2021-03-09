package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardSuit;
import blackjack.domain.result.Result;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {
    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player("bada");
        dealer = new Dealer();
    }

    @Test
    @DisplayName("참가자가 잘 생성되는지 확인")
    void create() {
        assertThatCode(() -> new Player("bada"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어가 버스트라면 무조건 패배한다")
    void generateResultWhenPlayerIsBust() {
        player.receiveAdditionalCard(new Card(CardLetter.JACK, CardSuit.CLOVER));
        player.receiveAdditionalCard(new Card(CardLetter.QUEEN, CardSuit.CLOVER));
        player.receiveAdditionalCard(new Card(CardLetter.KING, CardSuit.CLOVER));

        dealer.receiveAdditionalCard(new Card(CardLetter.EIGHT, CardSuit.DIAMOND));
        dealer.receiveAdditionalCard(new Card(CardLetter.NINE, CardSuit.DIAMOND));

        assertThat(player.generateResult(dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("플레이어가 버스트가 아니고, 상대 버스트라면 승리한다")
    void generateResultWhenOpponentIsBust() {
        player.receiveAdditionalCard(new Card(CardLetter.JACK, CardSuit.CLOVER));
        player.receiveAdditionalCard(new Card(CardLetter.QUEEN, CardSuit.CLOVER));

        dealer.receiveAdditionalCard(new Card(CardLetter.EIGHT, CardSuit.DIAMOND));
        dealer.receiveAdditionalCard(new Card(CardLetter.NINE, CardSuit.DIAMOND));
        dealer.receiveAdditionalCard(new Card(CardLetter.TEN, CardSuit.DIAMOND));

        Assertions.assertThat(player.generateResult(dealer)).isEqualTo(Result.WIN);
    }

    @ParameterizedTest
    @CsvSource(value = {"TEN,NINE:WIN", "TEN,SEVEN:DRAW", "SIX,SEVEN:LOSE"}, delimiter = ':')
    @DisplayName("플레이어와 상대 모두 버스트가 아니라면, 점수 합계로 승무패를 가린다")
    void generateResultByScore(final String playerCardInput, final String expectedResult) {
        dealer.receiveAdditionalCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
        dealer.receiveAdditionalCard(new Card(CardLetter.SEVEN, CardSuit.CLOVER));

        final String[] playerCards = playerCardInput.split(",");
        for (final String playerCard : playerCards) {
            final CardLetter cardLetter = CardLetter.valueOf(playerCard);
            player.receiveAdditionalCard(new Card(cardLetter, CardSuit.DIAMOND));
        }
        assertThat(player.generateResult(dealer)).isEqualTo(Result.valueOf(expectedResult));
    }
}
