package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BlackJackPlayerTest {

    @Test
    void 타입과_숫자를_알려주면_카드를_생성한다() {
        BlackJackCard blackJackCard = new BlackJackCard(CardType.SPADE, CardNumber.ACE);

        assertThat(blackJackCard).isEqualTo(new BlackJackCard(CardType.SPADE, CardNumber.ACE));
    }

}
