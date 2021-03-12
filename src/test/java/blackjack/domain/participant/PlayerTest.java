package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("bada", 10000);
    }

    @Test
    @DisplayName("참가자가 잘 생성되는지 확인")
    void create() {
        assertThatCode(() -> new Player("bada", 10000))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어가 Participant에게 상속받았는지 확인")
    void extend() {
        final Participant participant = new Player("bada", 10000);
        participant.receiveOneCard(new Card(CardNumber.ACE, CardType.HEART));
        assertThat(participant.cardCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("금액이 음수일 때 익셉션을 잘 날리는지 확인")
    void validateMoney() {
        final int falseMoney = -10;
        assertThatThrownBy(() -> new Player("bada", falseMoney)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Player.INVALID_MONEY_ERROR);
    }

    @Test
    @DisplayName("플레이어의 초기 카드 출력이 두 장 다 되는지 확인")
    void showInitialCards() {
        final CardDeck cardDeck = new CardDeck();
        player.receiveInitialCards(cardDeck);
        assertThat(player.showInitialCards().size()).isEqualTo(2);
    }
}
