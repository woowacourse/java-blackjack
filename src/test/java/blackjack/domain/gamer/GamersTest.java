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
        Deck deck = new Deck(Card.getCards());
        gamers.distributeFirstCards(deck);

        assertThat(dealer.getCardsSize()).isEqualTo(2);
        assertThat(players.size()).isEqualTo(2);
    }
}
