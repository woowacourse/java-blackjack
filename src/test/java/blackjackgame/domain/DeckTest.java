package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CardValue;
import blackjackgame.domain.card.Deck;
import blackjackgame.domain.card.Symbol;
import blackjackgame.domain.player.Dealer;
import blackjackgame.domain.player.Guest;
import blackjackgame.domain.player.Guests;
import blackjackgame.domain.player.Name;

class DeckTest {
    @DisplayName("현재 인덱스가 가르키는 카드를 뽑는지 확인한다.")
    @Test
    void Should_ReturnCursorIndexCard_When_PickOneCard() {
        Card card1 = new Card(Symbol.SPADE, CardValue.FIVE);
        Card card2 = new Card(Symbol.CLOVER, CardValue.EIGHT);
        List<Card> sampleCards = List.of(card1, card2);
        Deck deck = new Deck(sampleCards);

        assertThat(deck.pickOne()).isEqualTo(card1);
        assertThat(deck.pickOne()).isEqualTo(card2);
    }

    @DisplayName("덱이 참여자들에게 카드를 두장씩 배분하는지 확인한다.")
    @Test
    void Should_PlayersHaveTwoCard_When_CardMachineInitPlayersCards() {
        Deck deck = new Deck();

        List<String> inputNames = List.of("name1", "name2");
        Guests guests = new Guests(inputNames);
        Dealer dealer = new Dealer();

        deck.initPlayersCards(guests, dealer);

        assertThat(dealer.getSize()).isEqualTo(2);

        for (Guest guest : guests.getGuests()) {
            assertThat(guest.getSize()).isEqualTo(2);
        }
    }

    @DisplayName("덱이 플레이어에게 카드를 한 장 배분하는지 확인한다.")
    @Test
    void Should_PlayerGetOneCard_When_CardMachineGiveOneCard() {
        Deck deck = new Deck();
        Guest guest = new Guest(new Name("pobi"));

        deck.distributeCard(guest);
        assertThat(guest.getSize()).isEqualTo(1);
    }
}
