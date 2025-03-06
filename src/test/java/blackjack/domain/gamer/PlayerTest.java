package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.RandomCardStrategy;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 카드 두 장을 지급 받는다")
    void playerGetCardsTest() {
        Player player = new Player("Pobi");
        player.initialize(Deck.generateFrom(new RandomCardStrategy()));

        assertThat(player.getCards()).hasSize(2);
    }

    @ParameterizedTest
    @CsvSource({
        "JACK,QUEEN,KING,false",
        "TWO,JACK,KING,false",
        "NINE,JACK,KING,false",
    })
    @DisplayName("버스트되었을 경우 카드를 추가로 지급받을 수 없다")
    void canReceiveAdditionalCards1(CardNumber cardNumber1, CardNumber cardNumber2,
        CardNumber cardNumber3, boolean expected) {
        Card card1 = new Card(CardType.CLOVER, cardNumber1);
        Card card2 = new Card(CardType.HEART, cardNumber2);
        Card card3 = new Card(CardType.DIAMOND, cardNumber3);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        assertThat(player.canReceiveAdditionalCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "ACE,ACE,true",
        "ACE,JACK,true",
        "JACK,KING,true",
    })
    @DisplayName("버스트되지 않았을 경우 카드를 추가로 지급받을 수 있다")
    void canReceiveAdditionalCards2(CardNumber cardNumber1, CardNumber cardNumber2, boolean expected) {
        Card card1 = new Card(CardType.CLOVER, cardNumber1);
        Card card2 = new Card(CardType.HEART, cardNumber2);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.canReceiveAdditionalCards()).isEqualTo(expected);
    }
}
