package blackjack.domain.gamer;

import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GamersTest {
    @Test
    @DisplayName("플레이어 이름들을 입력 받아 생성한다.")
    void create() {
        Gamers gamers = new Gamers(List.of("더즈", "범고래"), new Deck());
        List<Player> players = gamers.getPlayers();
        assertThat(players.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("16이하이면 그 이상이 될 떄 까지 계속 카들르 뽑는다.")
    void distributeAdditionalToDealer() {
        Gamers gamers = new Gamers(List.of(), new Deck());
        Deck deck = new Deck();
        gamers.distributeAdditionalToDealer(deck);
        Dealer dealer = gamers.getDealer();

        assertThat(dealer.getCardsNumberSum()).isGreaterThan(16);
    }

    @Test
    @DisplayName("입력 받은 이름의 플레이어가 버스트인지 확인한다.")
    void isBurst() {
        String name = "범고래";
        Gamers gamers = new Gamers(List.of(name), new Deck());
        Player player = gamers.getPlayers().get(0);
        assertThat(gamers.canDrawToPlayer(player)).isFalse();
    }

    @Test
    @DisplayName("딜러가 보유한 카드의 크기를 조회한다.")
    void getDealerCardSize() {
        Gamers gamers = new Gamers(List.of(), new Deck());
        assertThat(gamers.getDealerCardSize()).isEqualTo(2);
    }
}
