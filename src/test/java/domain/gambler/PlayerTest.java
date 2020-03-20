package domain.gambler;

import domain.card.CardDeck;
import domain.gambler.Dealer;
import domain.gambler.Money;
import domain.gambler.Name;
import domain.gambler.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    CardDeck cardDeck = new CardDeck();
    Player player;

    @BeforeEach
    void init() {
        player = new Player(new Name("jamie"), Money.fromPositive("10000"));
    }

    @Test
    @DisplayName("드로우 테스트")
    void isAbleDrawCard() {
        Assertions.assertThat(player.canHit())
                .isTrue();
        for (int i = 0; i < 12; i++) {
            player.drawCard(cardDeck);
        }
        Assertions.assertThat(player.canHit())
                .isFalse();
    }

    @DisplayName("드로우 가능여부 - 가능")
    @Test
    void canHit() {
        player.drawCard(cardDeck);
        assertThat(player.canHit()).isTrue();

        for (int i = 0; i < 12; i++) {
            player.drawCard(cardDeck);
        }
        assertThat(player.canHit()).isFalse();
    }

    @DisplayName("딜러와 비교 후 수익금액 가져옴")
    @Test
    void getProfitByComparing() {
        Dealer dealer = new Dealer();
        dealer.drawCard(cardDeck, 10);
        player.drawCard(cardDeck);
        assertThat(player.getProfitByComparing(dealer).getMoney())
                .isEqualTo(10000);
    }
}
