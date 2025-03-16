package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.cardsGenerator.RandomCardsGenerator;
import domain.participant.Money;
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
        Card card1 = new Card(CardNumber.A, CardShape.CLOVER);
        Card card2 = new Card(CardNumber.TWO, CardShape.CLOVER);
        Deck deck = new Deck(new StaticCardGenerator(List.of(card1, card2)));

        // when
        Card actual = deck.pick();

        // then
        assertThat(actual).isEqualTo(card2);
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

    @Test
    void 덱에_카드가_없을_때_카드를_뽑으면_예외를_발생시킨다() {
        // given
        Deck deck = new Deck(new StaticCardGenerator());

        // when & then
        assertThatThrownBy(deck::pick).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("플레이어에게 카드를 할당할 수 있다.")
    @Test
    void 플레이어에게_카드_할당() {
        //given
        Card card = new Card(CardNumber.A, CardShape.CLOVER);
        Deck deck = new Deck(new StaticCardGenerator(List.of(card)));
        Player player = Player.init("플레이어", Money.of(100000));

        Player expected = Player.of(Hand.of(List.of(card)), "플레이어", Money.of(100000));

        //when
        deck.giveCardTo(player, 1);

        //then
        assertThat(player).isEqualTo(expected);
    }
}
