package blackjack.domain.user;

import blackjack.domain.Money;
import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {
    private final Money defaultMoney = Money.of(0);
    private Dealer dealer;
    private Players players;
    private Users users;
    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = CardDeck.createDeck();
        dealer = new Dealer();
        players = Players.of(Arrays.asList(new Player(Name.of("youngE"), defaultMoney), new Player(Name.of("kimkim"), defaultMoney)));
        users = Users.of(dealer, players);
    }

    @DisplayName("참가자들에게 카드를 두 장씩 나누어준다.")
    @Test
    void dealTwoCardsTest() {
        users.dealCards(cardDeck);
        for (User user : users.users()) {
            assertThat(user.cards.cards()).hasSize(2);
        }
    }
}