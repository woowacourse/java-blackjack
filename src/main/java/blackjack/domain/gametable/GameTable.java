package blackjack.domain.gametable;

import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Players;
import blackjack.domain.utils.CardDeck;
import java.util.ArrayList;
import java.util.List;

public class GameTable {
    private final Participant dealer;
    private final Players players;
    private final CardDeck cardDeck;

    public GameTable(Participant dealer, Players players, CardDeck cardDeck) {
        this.dealer = dealer;
        this.players = players;
        this.cardDeck = cardDeck;
        initCards();
    }

    private void initCards() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getUnmodifiableList());

        for (Participant participant : participants) {
            participant.takeCard(cardDeck.pop());
            participant.takeCard(cardDeck.pop());
        }

    }

    public void giveCard(Participant participant) {
        validateRestCards();
        if (!participant.isNotAbleToTake()) {
            participant.takeCard(cardDeck.pop());
        }
    }

    private void validateRestCards() {
        if (cardDeck.isEmpty()) {
            throw new IllegalArgumentException("카드가 소진되었습니다.");
        }
    }

}
