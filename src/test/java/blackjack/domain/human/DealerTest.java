package blackjack.domain.human;

import static blackjack.domain.Fixtures.ACE;
import static blackjack.domain.Fixtures.NINE;
import static blackjack.domain.Fixtures.TEN;
import static blackjack.domain.Fixtures.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.CardDeck;
import blackjack.domain.participant.human.Dealer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 카드 추가/포인트 획득 기능 테스트")
    public void addCardToPlayerTest() {
        Dealer dealer = new Dealer(List.of(ACE, TEN));
        assertThat(dealer.getPoint()).isEqualTo(21);
    }

    @Test
    @DisplayName("딜러 히트가능 확인하는 기능 테스트")
    public void isAbleToHitTest() {
        Dealer dealer = new Dealer(List.of(NINE, TWO));
        CardDeck cardDeck = new CardDeck();
        assertThat(dealer.draw(cardDeck))
                .isTrue();
    }
}
