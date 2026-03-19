package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GameManagerTest {

    @Test
    void 딜러를_포함한_전체_참가자에게_초기_카드를_지급한다() {
        GameManager manager = GameManager.createWith(Deck.create());
        Dealer dealer = new Dealer();
        Player player1 = new Player(Name.from("나무"));
        Player player2 = new Player(Name.from("고래"));
        Players players = Players.of(List.of(player1, player2));
        Participants participants = Participants.of(dealer, players);

        manager.dealInitialCards(participants);

        assertThat(dealer.cardsCount()).isEqualTo(2);
        assertThat(player1.cardsCount()).isEqualTo(2);
        assertThat(player2.cardsCount()).isEqualTo(2);
    }

    @Test
    void 참가자에게_카드를_지급한다() {
        GameManager manager = GameManager.createWith(Deck.create());
        Player player = new Player(Name.from("나무"));

        manager.dealCard(player);

        assertThat(player.cardsCount()).isEqualTo(1);
    }

}
