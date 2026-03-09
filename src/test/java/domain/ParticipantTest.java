package domain;

import domain.player.Name;
import domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void 핸드_점수_총합이_21_초과인_경우_버스트_판정() {
        GameManager gameManager = new GameManager(Deck.create());

        // A K Q J 10 9 8 7 6
        Player player = new Player(Name.from("나무"));

        gameManager.dealCard(player); // A
        gameManager.dealCard(player); // K
        gameManager.dealCard(player); // Q
        gameManager.dealCard(player); // J

        Assertions.assertThat(player.canReceive()).isFalse();
    }

    @Test
    void 핸드_점수_총합이_21_이하인_경우() {
        GameManager gameManager = new GameManager(Deck.create());

        // A K Q J 10 9 8 7 6
        Player player = new Player(Name.from("나무"));

        gameManager.dealCard(player); // A
        gameManager.dealCard(player); // K
        gameManager.dealCard(player); // Q

        Assertions.assertThat(player.canReceive()).isTrue();
    }

    @Test
    void 딜러_핸드_점수_총합이_16_초과인_경우_히트가능() {
        GameManager gameManager = new GameManager(Deck.create());

        // A K Q J 10 9 8 7 6
        Dealer dealer = new Dealer();

        gameManager.dealCard(dealer); // A
        gameManager.dealCard(dealer); // K

        Assertions.assertThat(dealer.canReceive()).isFalse();
    }

    @Test
    void 딜러_핸드_점수_총합이_16_인_경우() {
        GameManager gameManager = new GameManager(Deck.create());

        // A K Q J 10 9 8 7 6
        Dealer dealer = new Dealer();

        gameManager.dealCard(dealer); // A
        gameManager.dealCard(dealer); // K
        gameManager.dealCard(dealer); // Q

        Assertions.assertThat(dealer.canReceive()).isFalse();
    }
}
