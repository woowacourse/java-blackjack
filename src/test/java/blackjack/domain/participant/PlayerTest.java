package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("bada");
    }

    @Test
    @DisplayName("참가자가 잘 생성되는지 확인")
    void create() {
        assertThatCode(() -> new Player("bada"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어가 Participant에게 상속받았는지 확인")
    void extend() {
        final Participant participant = new Player("bada");
        participant.receiveOneCard(new Card(CardNumber.ACE, CardType.HEART));
        assertThat(participant.cardCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어의 초기 카드 출력이 두 장 다 되는지 확인")
    void showInitialCards() {
        final CardDeck cardDeck = new CardDeck();
        player.receiveInitialCards(cardDeck);
        Assertions.assertThat(player.showInitialCards().size()).isEqualTo(2);
    }
}
