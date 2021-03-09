package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    @DisplayName("딜러가 잘 생성되는지 확인")
    void create() {
        assertThatCode(Dealer::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러가 Participant에게 상속받았는지 확인")
    void extend() {
        final Participant participant = new Dealer();
        participant.receiveOneCard(new Card(CardNumber.ACE, CardType.HEART));
        assertThat(participant.cardCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러의 초기 카드 출력이 한 장만 되는지 확인")
    void showInitialCards() {
        final CardDeck cardDeck = new CardDeck();
        dealer.receiveInitialCards(cardDeck);
        assertThat(dealer.showInitialCards().size()).isEqualTo(1);
    }
}
