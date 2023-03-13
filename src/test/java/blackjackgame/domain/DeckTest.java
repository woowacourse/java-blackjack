package blackjackgame.domain;

class DeckTest {
    /*@DisplayName("덱에 넣은 순서대로 카드를 뽑는지 확인한다.")
    @Test
    void Should_RemoveFirstCard_When_PickOneCard() {
        Card five = Card.of(Symbol.SPADE, CardValue.FIVE);
        Card eight = Card.of(Symbol.CLOVER, CardValue.EIGHT);
        List<Card> sampleCards = List.of(five, eight);
        Deck deck = new Deck(sampleCards);

        assertThat(deck.pickOne()).isEqualTo(five);
        assertThat(deck.pickOne()).isEqualTo(eight);
    }

    @DisplayName("덱이 참여자(딜러와 게스트 모두)들에게 카드를 두장씩 배분하는지 확인한다.")
    @Test
    void Should_PlayersHaveTwoCard_When_FirstPickCards() {
        Deck deck = new Deck();
        Guest guest1 = new Guest(new Name("name1"), deck.firstPickCards());
        Guest guest2 = new Guest(new Name("name2"), deck.firstPickCards());
        Guests guests = new Guests(List.of(guest1, guest2));
        Dealer dealer = new Dealer(deck.firstPickCards());

        assertThat(dealer.getSize()).isEqualTo(2);
        for (Guest guest : guests.getGuests()) {
            assertThat(guest.getSize()).isEqualTo(2);
        }
    }

    @DisplayName("덱이 플레이어에게 카드를 한 장 배분하는지 확인한다.")
    @Test
    void Should_PlayerGetOneCard_When_PickOne() {
        Deck deck = new Deck();
        Guest guest = new Guest(new Name("name1"), deck.firstPickCards());
        guest.addCard(deck.pickOne());

        assertThat(guest.getSize()).isEqualTo(3);
    }

    @DisplayName("카드를 전부 뽑으면 다시 카드를 생성하는지 확인한다.")
    @Test
    void Should_ReInitializeCards_When_pickAllCards() {
        Deck deck = new Deck();

        assertDoesNotThrow(() -> {
            for (int i = 0; i < 100; i++) {
                deck.firstPickCards();
                deck.pickOne();
            }
        });
    }*/
}
