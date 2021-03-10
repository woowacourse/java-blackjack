package blackjack;

import blackjack.domain.GameTable;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.utils.FixedCardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    void create() {
        final GameTable gameTable = new GameTable(new FixedCardDeck());
        final Name john = new Name("john");

        Participant player = new Player(john, gameTable);
        assertThat(player.getName()).isEqualTo("john");
    }

    @Test
    void create2() {
        final GameTable gameTable = new GameTable(new FixedCardDeck());
        final Name sarah = new Name("sarah");

        Participant player = new Player(sarah, gameTable);
        assertThat(player.getName()).isEqualTo("sarah");
    }

    @Test
    @DisplayName("플레이어가 초기에 카드 두장을 갖고 있는지 확인")
    void create3() {
        final GameTable gameTable = new GameTable(new FixedCardDeck());
        List<Card> cards = gameTable.initCards();
        final Name sarah = new Name("sarah");
        Participant player = new Player(sarah, cards);

        List<Card> playerCards = player.getUnmodifiableCards();
        assertThat(playerCards)
            .contains(
                Card.from(Suits.CLOVER, Denominations.ACE),
                Card.from(Suits.CLOVER, Denominations.TWO));
    }

    @Test
    @DisplayName("플레이어에게 카드 추가 지급")
    void add_card() {
        final GameTable gameTable = new GameTable(new FixedCardDeck());
        List<Card> cards = gameTable.initCards();
        final Name sarah = new Name("sarah");
        Participant player = new Player(sarah, cards);

        gameTable.giveCard(player);
        assertThat(player.getUnmodifiableCards())
            .contains(
                Card.from(Suits.CLOVER, Denominations.ACE),
                Card.from(Suits.CLOVER, Denominations.TWO),
                Card.from(Suits.CLOVER, Denominations.THREE));
    }

    @Test
    @DisplayName("플레이어에게 지급된 카드 합계")
    void sum_cards() {
        final GameTable gameTable = new GameTable(new FixedCardDeck());
        List<Card> cards = gameTable.initCards();
        final Name sarah = new Name("sarah");
        Participant player = new Player(sarah, cards);
        int score = player.sumCards();
        assertThat(score).isEqualTo(3);
    }

    @Test
    @DisplayName("결과를 위한 플레이어에게 지급된 카드 합계")
    void sum_cards_for_result() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.DIAMOND, Denominations.SIX));
        final Name sarah = new Name("sarah");

        Participant player = new Player(sarah, cards);
        int score = player.sumCardsForResult();
        assertThat(score).isEqualTo(17);
    }

    @Test
    @DisplayName("Ace 4장인 경우 지지않는 최대 합계")
    void sum_cards_for_result1() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.DIAMOND, Denominations.ACE));
        final Name sarah = new Name("sarah");
        Participant player = new Player(sarah, cards);
        player.takeCard(Card.from(Suits.DIAMOND, Denominations.ACE));
        player.takeCard(Card.from(Suits.DIAMOND, Denominations.ACE));

        int score = player.sumCardsForResult();

        assertThat(score).isEqualTo(14);
    }
}
