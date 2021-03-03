package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    void create() {
        Playable player = new Player("john");
        assertThat(player.getName()).isEqualTo("john");
    }

    @Test
    void create2() {
        Playable player = new Player("sarah");
        assertThat(player.getName()).isEqualTo("sarah");
    }

    @Test
    @DisplayName("플레이어가 초기에 카드 두장을 갖고 있는지 확인")
    void create3() {
        final CardDeck cardDeck = new FixedCardDeck();
        List<Card> cards = cardDeck.initCards();
        Playable player = new Player("sarah", cards);

        // 카드내역
        List<Card> playerCards = player.getCards();
        assertThat(playerCards).contains(Card.from("A클로버"), Card.from("2클로버"));
    }

    @Test
    @DisplayName("플레이어에게 카드 추가 지급")
    void add_card() {
        final CardDeck cardDeck = new FixedCardDeck();
        List<Card> cards = cardDeck.initCards();
        Playable player = new Player("sarah", cards);
        player.takeCard(cardDeck.pop());
        assertThat(player.getCards()).contains(Card.from("A클로버"), Card.from("2클로버"), Card.from("3클로버"));
    }

    @Test
    @DisplayName("플레이어에게 지급된 카드 합계")
    void sum_cards() {
        final CardDeck cardDeck = new FixedCardDeck();
        List<Card> cards = cardDeck.initCards();
        Playable player = new Player("sarah", cards);
        int score = player.sumCards();
        assertThat(score).isEqualTo(3);
    }

    @Test
    @DisplayName("결과를 위한 플레이어에게 지급된 카드 합계")
    void sum_cards_for_result() {
        List<Card> cards = Arrays.asList(Card.from("A다이아몬드"), Card.from("6다이아몬드"));
        Playable player = new Player("john", cards);
        int score = player.sumCardsForResult();
        assertThat(score).isEqualTo(17);
    }

    @Test
    @DisplayName("Ace 4장인 경우 지지않는 최대 합계")
    void sum_cards_for_result1() {
        List<Card> cards = Arrays.asList(Card.from("A다이아몬드"),
                Card.from("A다이아몬드"));
        Playable player = new Player("john", cards);
        player.takeCard(Card.from("A다이아몬드"));
        player.takeCard(Card.from("A다이아몬드"));

        int score = player.sumCardsForResult();

        assertThat(score).isEqualTo(14);
    }
}
