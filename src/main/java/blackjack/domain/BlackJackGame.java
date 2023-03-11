package blackjack.domain;

import java.util.List;

public class BlackJackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(DeckGenerator deckGenerator, List<String> playersName) {
        this.deck = deckGenerator.generate();
        this.participants = Participants.of(playersName);
    }

    public void handInitialCards() {
        participants.handInitialCards(deck);
    }

    public void handOneCard(Participant participant) {
        Card card = deck.draw();
        participant.take(card);
    }

    public Card getDealerFirstCard() {
        return getDealer().getFirstCard();
    }

    public GameResult getGameResult() {
        return participants.getGameResult();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public List<String> getPlayersName() {
        return participants.getPlayersName();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }
}
