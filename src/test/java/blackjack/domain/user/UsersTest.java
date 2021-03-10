package blackjack.domain.user;

import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {
    private Dealer dealer;
    private Players players;
    private Users users;
    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = CardDeck.createDeck();
        dealer = new Dealer();
        players = Players.of(Arrays.asList("youngE", "kimkim"));
        users = new Users(dealer, players);
    }

    @DisplayName("참가자들에게 카드를 두 장씩 나누어준다.")
    @Test
    void dealTwoCardsTest() {
        users.dealTwoCards(cardDeck);
        for (User user : users.users()) {
            assertThat(user.cards.cards()).hasSize(2);
        }
    }
}