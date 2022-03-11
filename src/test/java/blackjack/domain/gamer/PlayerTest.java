package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.result.BlackJackResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("딜러와 카드 점수 결과 반환")
    void match() {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.getInstance(CardShape.DIAMOND, CardNumber.THREE));
        dealer.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.NINE));
        dealer.addCard(Card.getInstance(CardShape.DIAMOND, CardNumber.EIGHT));

        Player pobi = new Player("pobi");
        pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.TWO));
        pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.EIGHT));
        pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.ACE));

        assertThat(pobi.match(dealer)).isEqualTo(BlackJackResult.WIN);
    }

    @Test
    @DisplayName("플레이어와 이름이 같다면 True를 반환한다.")
    void isSameName() {
        Player player1 = new Player("더즈");
        assertThat(player1.isSameName("더즈")).isTrue();
    }
}