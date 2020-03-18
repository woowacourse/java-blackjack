package util;

import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.User;
import factory.CardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDistributorTest {
    @Test
    @DisplayName("카드 분배 확인")
    void distributeCards() {
        final CardDeck cardDeck = new CardDeck(CardFactory.create());
        final User user = new User("user");

        CardDistributor.distributeCards(cardDeck, user);
        assertThat(user.getCardSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드 낱장 주기 확인")
    void giveOneCard() {
        final CardDeck cardDeck = new CardDeck(CardFactory.create());
        final Dealer dealer = new Dealer();
        final User user = new User("user");

        CardDistributor.giveOneCard(cardDeck, dealer);
        CardDistributor.giveOneCard(cardDeck, user);
        CardDistributor.giveOneCard(cardDeck, user);
        assertThat(dealer.getCardSize()).isEqualTo(1);
        assertThat(user.getCardSize()).isEqualTo(2);
    }
}
