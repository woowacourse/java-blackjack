package domain.user;

import domain.card.Card;
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

    @Test
    @DisplayName("딜러 승리 여부 확인 - 플레이어와 동점일 경우 패배")
    void isWin() {
        Dealer dealer = new Dealer();
        Player user = new Player("player");

        dealer.addCard(Card.of("스페이드", "K"));
        user.addCard(Card.of("스페이드", "9"));
        assertThat(dealer.isWin(user)).isTrue();

        user.addCard(Card.of("스페이드", "10"));
        assertThat(dealer.isWin(user)).isFalse();
    }
}
