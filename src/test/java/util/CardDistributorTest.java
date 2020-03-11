package util;

import domain.card.CardDeck;
import domain.user.Dealer;
import domain.user.Player;
import factory.CardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDistributorTest {
    @Test
    @DisplayName("카드 분배 확인")
    void giveOneCard() {
        final CardDeck cardDeck = new CardDeck(CardFactory.create());
        final Dealer dealer = new Dealer();
        final Player player = new Player("player");

        assertThat(dealer.getCardSize()).isEqualTo(0);
        assertThat(player.getCardSize()).isEqualTo(0);

        CardDistributor.giveOneCard(cardDeck,dealer);
        CardDistributor.giveOneCard(cardDeck,player);
        CardDistributor.giveOneCard(cardDeck,player);
        assertThat(dealer.getCardSize()).isEqualTo(1);
        assertThat(player.getCardSize()).isEqualTo(2);
    }
}
