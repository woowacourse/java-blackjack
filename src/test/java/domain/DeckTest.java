package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Deck;
import domain.card.Hand;
import domain.card.cardsGenerator.RandomCardsGenerator;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("카드를 뽑을 수 있다.")
    @Test
    void test() {
        // given
        Deck deck = new Deck(new RandomCardsGenerator());

        // when
        Card card = deck.pick();

        // then
        assertThat(card).isInstanceOf(Card.class);
    }

    @DisplayName("덱은 항상 다른 카드를 반환한다.")
    @Test
    void 덱은_항상_다른_카드_반환() {
        // given
        Deck deck = new Deck(new RandomCardsGenerator());
        List<Card> outputCards = new ArrayList<>();

        // when
        for (int i = 0; i < 52; ++i) {
            outputCards.add(deck.pick());
        }

        // then
        assertThat(outputCards).doesNotHaveDuplicates();
    }

    @DisplayName("덱은 52장의 카드를 갖고 있다.")
    @Test
    void 덱은_52장() {
        // given
        Deck deck = new Deck(new RandomCardsGenerator());
        for (int i = 0; i < 52; ++i) {
            deck.pick();
        }

        // when & then
        assertThatThrownBy(deck::pick).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("플레이어에게 카드를 할당할 수 있다.")
    @Test
    void 플레이어에게_카드_할당() {
        //given
        Card card = new Card(CardNumber.A, CardShape.CLOVER);
        Deck deck = new Deck(() -> new ArrayList<>(List.of(card)));
        Player player = Player.init("플레이어");

        //when
        deck.giveCardTo(player);
        Hand hand = player.getCards();

        //then
        assertThat(hand.getCards()).contains(card);
    }
}
