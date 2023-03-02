package blackjack.domain;

import java.util.List;

public class BlackJackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(CardsGenerator cardsGenerator, List<String> playerNames) {
        this.deck = new Deck(cardsGenerator.generate());
        this.participants = Participants.of(playerNames);
    }

    public void handOut() {
        participants.handOut(deck);
    }
}
