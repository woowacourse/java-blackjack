package blackjack.domain;


import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {

    @DisplayName("users 생성 확인")
    @Test
    void create() {
        Dealer dealer = new Dealer();

        Users users = new Users(dealer, Arrays.asList("test1", "test2"));

        assertThat(users.gerUsers()).hasSize(3);
    }

    @DisplayName("users 초기화 확인")
    @Test
    void initial() {
        Dealer dealer = new Dealer();

        Users users = new Users(dealer, Arrays.asList("test1", "test2"));

        users.initialHit(CardDeck.createDeck());
        assertThat(users.getDealer().getCards()).hasSize(2);
        assertThat(users.getPlayers().get(0).getCards()).hasSize(2);
        assertThat(users.getPlayers().get(1).getCards()).hasSize(2);
    }
}