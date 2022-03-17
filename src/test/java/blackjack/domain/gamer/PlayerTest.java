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
        List<Card> cards = List.of(new Card(CardShape.DIAMOND, CardNumber.THREE), new Card(CardShape.CLOVER, CardNumber.NINE));
        Dealer dealer = new Dealer(cards);

        Player pobi = new Player("pobi", cards);
        assertThat(pobi.match(dealer)).isEqualTo(BlackJackResult.DRAW);
    }

    @Test
    @DisplayName("플레이어와 이름이 같다면 True를 반환한다.")
    void isSameName() {
        List<Card> cards = List.of(new Card(CardShape.DIAMOND, CardNumber.THREE), new Card(CardShape.CLOVER, CardNumber.NINE));
        Player player1 = new Player("더즈", cards);
        assertThat(player1.isSameName("더즈")).isTrue();
    }
}