package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.utils.CardDeck;
import blackjack.utils.FixedCardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @Test
    @DisplayName("dealer 초기에 카드 두장을 갖고 있는지 확인")
    void create() {
        final CardDeck cardDeck = new FixedCardDeck();
        List<Card> cards = cardDeck.initCards();
        Participant dealer = new Dealer(cards);

        List<Card> playerCards = dealer.getUnmodifiableCards();
        assertThat(playerCards).contains(Card.from("A클로버"), Card.from("2클로버"));
    }

    @Test
    @DisplayName("dealer 카드 추가 지급")
    void add_card() {
        final CardDeck cardDeck = new FixedCardDeck();
        List<Card> cards = cardDeck.initCards();
        Participant dealer = new Dealer(cards);

        dealer.takeCard(cardDeck.pop());
        assertThat(dealer.getUnmodifiableCards()).contains(Card.from("A클로버"), Card.from("2클로버"), Card.from("3클로버"));
    }

    @Test
    @DisplayName("dealer 지급된 카드 합계")
    void sum_cards() {
        final CardDeck cardDeck = new FixedCardDeck();
        List<Card> cards = cardDeck.initCards();
        Participant dealer = new Dealer(cards);
        int score = dealer.sumCards();
        assertThat(score).isEqualTo(3);
    }

    @Test
    @DisplayName("결과를 위한 플레이어에게 지급된 카드 합계")
    void sum_cards_for_result1() {
        List<Card> cards = Arrays.asList(Card.from("A다이아몬드"), Card.from("6다이아몬드"));
        Participant dealer = new Dealer(cards);
        int score = dealer.sumCardsForResult();
        assertThat(score).isEqualTo(17);
    }

    @Test
    @DisplayName("Dealer 16 초과한 경우")
    void sum_cards_for_result2() {
        List<Card> cards = Arrays.asList(Card.from("K다이아몬드"),
                Card.from("K다이아몬드"));
        Participant dealer = new Dealer(cards);

        assertThat(dealer.isAvailableToTake()).isEqualTo(false);
    }
}
