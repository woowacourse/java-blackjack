package blackjack.domain;

import java.util.ArrayList;
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
            List<Card> cards = pickTwice();
            player.addCards(cards);
        }
        List<Card> cards = pickTwice();
        Dealer dealer = participants.getDealer();
        dealer.addCards(cards);
    }

    private List<Card> pickTwice() {
        List<Card> pick = new ArrayList<>();
        pick.add(cardDeck.pick());
        pick.add(cardDeck.pick());
        return pick;
    }

    public Map<Participant, GameResult> getResult() {
        Map<Participant, GameResult> result = new LinkedHashMap<>();
        for (Player player : participants.getPlayers()) {
            result.put(player, GameResult.of(player, participants.getDealer()));
        }
        return result;
    }

    public void giveCard(Participant participant) {
        participant.addCard(cardDeck.pick());
    }
}
