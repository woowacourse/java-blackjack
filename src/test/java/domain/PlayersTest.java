package domain;

import domain.card.Deck;
import domain.card.DeckGenerator;
import domain.player.Dealer;
import domain.player.Players;
import domain.player.User;
import domain.player.Users;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayersTest {
    
    @Test
    void 참여자_모두에게_카드를_2장씩_분배한다() {
        // given
        Dealer dealer = new Dealer();
        Users users = new Users(List.of(
                new User("시소"),
                new User("헤일러"),
                new User("부기"),
                new User("사나")
        ));
        Players players = new Players(dealer, users);
        Deck deck = DeckGenerator.generateDeck();

        // when
        players.distributeInitialCards(deck);

        // then
        Assertions.assertThat(players.getPlayers().stream()
                .filter(player -> player.getCards().size() == 2)
                .count()).isEqualTo(5);
    }
}