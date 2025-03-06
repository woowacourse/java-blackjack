package domain;

import static domain.card.Number.ACE;
import static domain.card.Number.JACK;
import static domain.card.Number.KING;
import static domain.card.Number.QUEEN;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import config.CardDeckFactory;
import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("카드 두 장 뽑기 테스트")
    void hitCardsTest() {
        // given
        Player player = new Player("pobi");
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();
        Dealer dealer = new Dealer(cardDeck);

        // when
        player.hitCards(dealer);

        // then
        assertDoesNotThrow(() -> player.hitCards(dealer));
    }

    @Test
    @DisplayName("카드 추가 테스트")
    void addCardTest() {
        //given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();
        Dealer dealer = new Dealer(cardDeck);
        Player player = new Player("pobi");

        //when-then
        assertDoesNotThrow(() -> player.addCard(dealer));
    }

    @Test
    @DisplayName("카드 합계 테스트")
    void sumTest() {
        //given
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE)));
        Dealer dealer = new Dealer(cardDeck);
        Player player = new Player("pobi");

        //when
        player.hitCards(dealer);

        //then
        assertThat(player.sum()).isEqualTo(12);
    }

    @Test
    @DisplayName("버스트 테스트")
    void isBustTest(){
        //given
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, QUEEN), new Card(SPADE, JACK), new Card(HEART, KING)));
        Dealer dealer = new Dealer(cardDeck);
        Player player = new Player("pobi");

        //when
        player.hitCards(dealer);
        player.addCard(dealer);

        //then
        assertThat(player.isBust()).isTrue();
    }
}
