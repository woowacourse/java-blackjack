package blackjack;

import blackjack.domain.Score;
import blackjack.domain.card.Cards;
import blackjack.domain.gametable.GameTable;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.utils.FixedCardDeck;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    void create() {
        final Name john = new Name("john");
        final Cards cards = new Cards(Collections.emptyList());

        Participant player = new Player(john, cards);

        assertThat(player.getName()).isEqualTo("john");
    }

    @Test
    void create2() {
        final Name sarah = new Name("sarah");
        final Cards cards = new Cards(Collections.emptyList());

        Participant player = new Player(sarah, cards);
        assertThat(player.getName()).isEqualTo("sarah");
    }

    @Test
    @DisplayName("플레이어가 초기에 카드 두장을 갖고 있는지 확인")
    void create3() {
        Player player = new Player(new Name("sarah"));
        final GameTable gameTable = new GameTable(player, new FixedCardDeck());

        List<Card> playerCards = player.getUnmodifiableCards();
        assertThat(playerCards).hasSize(2);
    }

    @Test
    @DisplayName("플레이어에게 카드 추가 지급")
    void add_card() {
        Player player = new Player(new Name("sarah"));
        final FixedCardDeck fixedCardDeck = new FixedCardDeck();
        player.takeCard(fixedCardDeck.pop());
        player.takeCard(fixedCardDeck.pop());
        player.takeCard(fixedCardDeck.pop());

        assertThat(player.getUnmodifiableCards())
            .contains(
                Card.from(Suits.CLOVER, Denominations.ACE),
                Card.from(Suits.CLOVER, Denominations.TWO),
                Card.from(Suits.CLOVER, Denominations.THREE));
    }

}
