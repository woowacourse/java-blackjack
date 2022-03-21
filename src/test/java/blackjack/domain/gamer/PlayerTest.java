package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.result.BlackJackResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("딜러와 카드 점수 결과 반환")
    void match() {
        // given
        List<Card> cards = List.of(new Card(CardShape.DIAMOND, CardNumber.THREE), new Card(CardShape.CLOVER, CardNumber.NINE));
        Dealer dealer = new Dealer(cards);
        Player pobi = new Player("pobi", cards, 1000);

        // when
        BlackJackResult value = pobi.match(dealer);

        // then
        assertThat(value).isEqualTo(BlackJackResult.DRAW);
    }

    @Test
    @DisplayName("플레이어와 이름이 같다면 같은 객체로 판단한다.")
    void equals() {
        Card card1 = new Card(CardShape.CLOVER, CardNumber.FIVE);
        Card card2 = new Card(CardShape.HEART, CardNumber.TEN);
        Player player1 = new Player("범고래", List.of(card1), 1000);
        Player player2 = new Player("범고래", List.of(card2), 2000);
        assertThat(player1).isEqualTo(player2);
    }

    @Test
    @DisplayName("딜러를 입력 받아 수익을 반환한다.")
    void getProfit() {
        // given
        List<Card> cards = List.of(new Card(CardShape.DIAMOND, CardNumber.THREE), new Card(CardShape.CLOVER, CardNumber.NINE));
        Dealer dealer = new Dealer(cards);
        Player pobi = new Player("pobi", cards, 1000);

        // when
        int value = pobi.getProfit(dealer);

        // then
        assertThat(value).isEqualTo(0);
    }
}