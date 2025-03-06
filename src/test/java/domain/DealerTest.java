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
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("카드 분배 테스트")
    void hitCardsToParticipantTest() {
        // given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();
        Dealer dealer = new Dealer(cardDeck);
        //when-then
        assertThat(dealer.hitCard()).isInstanceOf(Card.class);

    }

    @Test
    @DisplayName("카드 추가 테스트")
    void addCardsTest() {
        // given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();
        Dealer dealer = new Dealer(cardDeck);
        // when-then
        assertDoesNotThrow(dealer::addCards);
    }


    @Test
    @DisplayName("카드 합계 테스트")
    void sumTest() {
        //given
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE)));
        Dealer dealer = new Dealer(cardDeck);
        dealer.addCards();
        dealer.addCards();

        //when-then
        assertThat(dealer.sum()).isEqualTo(12);
    }
}
