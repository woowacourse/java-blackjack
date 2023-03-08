package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final CardDeck cardDeck;
    private final Participants participants;

    public BlackjackGame(Participants participants, CardDeck cardDeck) {
        this.participants = participants;
        this.cardDeck = cardDeck;
    }

    public BlackjackGame(Participants participants) {
        this(participants, new CardDeck());
    }

    public void dealOutCard() {
        for (Player player : participants.getPlayers()) {
            List<Card> cards = cardDeck.pickTwice();
            player.addCards(cards);
        }
        dealOutCardToDealer();
    }

    private void dealOutCardToDealer() {
        Dealer dealer = participants.getDealer();
        List<Card> cards = cardDeck.pickTwice();
        dealer.addCards(cards);
    }

    public GameResult getResult() {
        Map<Player, ResultState> result = new LinkedHashMap<>();
        for (Player player : participants.getPlayers()) {
            result.put(player, ResultState.of(player, participants.getDealer()));
        }
        return new GameResult(result);
    }

    public void giveCard(Participant participant) {
        participant.addCard(cardDeck.pick());
    }
}
