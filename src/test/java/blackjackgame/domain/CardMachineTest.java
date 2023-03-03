package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardMachineTest {
    @DisplayName("카드 머신이 참여자들에게 카드를 두장씩 배분하는지 확인한다.")
    @Test
    void Should_PlayersHaveTwoCard_When_CardMachineInitPlayersCards() {
        Deck deck = new Deck();
        CardMachine cardMachine = new CardMachine(deck);

        List<String> inputNames = List.of("name1", "name2");
        Guests guests = new Guests(inputNames);
        Dealer dealer = new Dealer();

        cardMachine.initPlayersCards(guests, dealer);

        assertThat(dealer.getSize()).isEqualTo(2);

        for (Guest guest : guests.getGuests()) {
            assertThat(guest.getSize()).isEqualTo(2);
        }
    }

    @DisplayName("카드 머신이 플레이어에게 카드를 한 장 배분하는지 확인한다.")
    @Test
    void Should_PlayerGetOneCard_When_CardMachineGiveOneCard() {
        Deck deck = new Deck();
        CardMachine cardMachine = new CardMachine(deck);
        Guest guest = new Guest(new Name("pobi"));

        cardMachine.distributeCard(guest);
        assertThat(guest.getSize()).isEqualTo(1);
    }
}
