package domain.user;

import domain.card.CardDeck;
import factory.CardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.CardDistributor;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @Test
    @DisplayName("Dealer 생성")
    void create() {
        assertThat(new Dealer()).isInstanceOf(Dealer.class);
    }

    @Test
    @DisplayName("카드 한 장 분배")
    void giveOneCard() {
        Player player = new Player("플레이어");
        CardDeck cardDeck = new CardDeck(CardFactory.create());
        CardDistributor.giveOneCard(cardDeck, player);
        assertThat(player.getCardSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러 점수가 16이하인지 확인")
    void shouldAddCard() {
        Dealer dealer = new Dealer();
        assertThat(dealer.shouldAddCard()).isTrue();
    }
}
