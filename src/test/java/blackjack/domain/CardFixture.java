package blackjack.domain;

class CardFixture {

    static Card heartJack() {
        return new Card(CardSuit.HEART, CardNumber.JACK);
    }

    static Card diamond3() {
        return new Card(CardSuit.DIAMOND, CardNumber.THREE);
    }

    static Card cloverAce() {
        return new Card(CardSuit.CLOVER, CardNumber.ACE);
    }
}
