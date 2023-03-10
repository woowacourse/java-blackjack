package blackjack.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            player.addCards(pickTwice());
        }
        Dealer dealer = participants.getDealer();
        dealer.addCards(pickTwice());
    }

    private List<Card> pickTwice() {
        List<Card> pick = new ArrayList<>();
        pick.add(cardDeck.pick());
        pick.add(cardDeck.pick());
        return pick;
    }

    public BlackjackResult getResult() {
        Map<Player, GameResult> result = participants.getPlayers().stream()
                .collect(Collectors.toMap(player -> player,
                        player -> GameResult.of(player, participants.getDealer()),
                        (o1, o2) -> o1,
                        LinkedHashMap::new));
        return new BlackjackResult(result);
    }

    public void drawCard(Participant participant) {
        participant.addCard(cardDeck.pick());
    }
}
