package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GamersTest {
    @Test
    @DisplayName("플레이어 이름들을 입력 받아 생성한다.")
    void create() {
        Gamers gamers = new Gamers(List.of("더즈", "범고래"));
        List<Player> players = gamers.getPlayers();
        assertThat(players.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어와 딜러 각각 두 장의 카드를 뽑는다.")
    void distributeFirstCards() {
        Gamers gamers = new Gamers(List.of("더즈", "범고래"));
        Dealer dealer = gamers.getDealer();
        List<Player> players = gamers.getPlayers();
        Deck deck = new Deck();
        gamers.distributeFirstCards(deck);
        assertThat(dealer.getCardsSize()).isEqualTo(2);
        assertThat(players.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("16이하이면 그 이상이 될 떄 까지 계속 카들르 뽑는다.")
    void distributeAdditionalToDealer() {
        Gamers gamers = new Gamers(List.of());
        Deck deck = new Deck();
        gamers.distributeAdditionalToDealer(deck);
        Dealer dealer = gamers.getDealer();

        assertThat(dealer.getCardsNumberSum()).isGreaterThan(16);
    }

    @Test
    @DisplayName("플레이어 이름을 입력 받아 카드를 뽑는다.")
    void distributeCardToPlayer() {
        String name = "범고래";
        Gamers gamers = new Gamers(List.of(name));
        Deck deck = new Deck();
        Player players = gamers.findPlayerByName(name);
        gamers.distributeCardToPlayer(name, deck);
        assertThat(players.getCardsSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("입력 받은 이름의 플레이어가 버스트인지 확인한다.")
    void isBurst() {
        String name = "범고래";
        Gamers gamers = new Gamers(List.of(name));
        assertThat(gamers.isBurst(name)).isFalse();
    }

    @Test
    @DisplayName("플레이어 이름을 입력 받아 Player를 조회한다.")
    void findPlayerByName() {
        String name = "범고래";
        Gamers gamers = new Gamers(List.of(name));
        Player playerByName = gamers.findPlayerByName(name);
        assertThat(playerByName.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("딜러가 보유한 카드의 크기를 조회한다.")
    void getDealerCardSize() {
        Gamers gamers = new Gamers(List.of());
        assertThat(gamers.getDealerCardSize()).isEqualTo(0);
    }
}
