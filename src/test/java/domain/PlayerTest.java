package domain;

import static domain.card.Number.ACE;
import static domain.card.Shape.DIAMOND;
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

}
