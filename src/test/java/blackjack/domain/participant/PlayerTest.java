package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.testutil.CardDrawer;
import blackjack.testutil.CustomDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private static Player createPlayer(List<Number> numbers, List<Shape> shapes, String name) {
        Deck deck = new CustomDeck(numbers, shapes);
        return new Player(new Name(name), deck);
    }

    @DisplayName("플레이어는 처음에 두 장의 카드를 오픈한다.")
    @Test
    void playerInitialOpenedCards() {
        //given
        List<Number> numbers = List.of(Number.ACE, Number.EIGHT);
        List<Shape> shapes = List.of(Shape.SPADE, Shape.CLOVER);
        Player player = createPlayer(numbers, shapes, "mason");

        //when
        List<String> cardSignatures = player.getInitialOpenedCards()
                .stream()
                .map(card -> card.getSymbol() + card.getShape())
                .toList();

        //then
        assertThat(cardSignatures).containsExactly("A스페이드", "8클로버");
    }

    @DisplayName("패가 블랙잭인 플레이어가 Hit을 할 수 있는지 판단한다.")
    @Test
    void isBlackjackPlayerCanHit() {
        //given
        List<Number> numbers = List.of(Number.ACE, Number.JACK);
        Deck deck = new CustomDeck(numbers);
        Player player = new Player(new Name("pobi"), deck);

        //when & then
        assertThat(player.canHit()).isEqualTo(false);
    }

    @DisplayName("패가 Bust인 플레이어가 Hit을 할 수 있는지 판단한다.")
    @Test
    void isBustPlayerCanHit() {
        //given
        List<Number> numbers = List.of(Number.EIGHT, Number.NINE, Number.QUEEN);
        Deck deck = new CustomDeck(numbers);
        Player player = new Player(new Name("gamza"), deck);
        CardDrawer.addAllCards(deck, player);

        //when & then
        assertThat(player.canHit()).isEqualTo(false);
    }

    @DisplayName("패가 21 미만인 플레이어가 Hit을 할 수 있는지 판단한다.")
    @Test
    void isUnderPlayerCanHit() {
        //given
        List<Number> numbers = List.of(Number.EIGHT, Number.NINE);
        Deck deck = new CustomDeck(numbers);
        Player player = new Player(new Name("mason"), deck);
        CardDrawer.addAllCards(deck, player);

        //when & then
        assertThat(player.canHit()).isEqualTo(true);
    }
}
