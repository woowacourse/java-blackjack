package player;

import card.Deck;
import card.DeckGenerator;
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
        Assertions.assertThat(player.getHandCards().size())
                .isEqualTo(2);
    }

    @Test
    void 참여자는_초기에_카드를_2장씩_받는다() {
        // given
        Player player = new Participant("훌라");
        Deck deck = DeckGenerator.generateDeck();

        // when
        player.receiveInitialCards(deck);

        // then
        Assertions.assertThat(player.getHandCards().size())
                .isEqualTo(2);
    }

    @Test
    void 이름이_같다면_같은_참여자다() {
        // given
        final String targetName = "훌라";
        Player player = new Participant(targetName);

        // when & then
        Assertions.assertThat(player)
                .isEqualTo(new Participant(targetName));
    }
}
