package domain;

import domain.card.Deck;
import domain.card.DeckGenerator;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void 딜러는_초기에_카드를_2장씩_받는다() {
        // given
        Player player = new Dealer();
        Deck deck = DeckGenerator.generateDeck();

        // when
        player.receiveInitialCards(deck);

        // then
        Assertions.assertThat(player.getCards().size())
                .isEqualTo(2);
    }

    @Test
    void 참여자는_초기에_카드를_2장씩_받는다() {
        // given
        Player player = new User("훌라");
        Deck deck = DeckGenerator.generateDeck();

        // when
        player.receiveInitialCards(deck);

        // then
        Assertions.assertThat(player.getCards().size())
                .isEqualTo(2);
    }

    @Test
    void 이름이_같다면_같은_참여자다() {
        // given
        final String targetName = "훌라";
        Player player = new User(targetName);

        // when & then
        Assertions.assertThat(player)
                .isEqualTo(new User(targetName));
    }
}
