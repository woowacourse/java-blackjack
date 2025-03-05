package domain;

import java.util.List;
import java.util.Objects;

public class BlackJackManager {

    private final List<Participant> participants;
    private final CardDeck cardDeck;

    public BlackJackManager(List<Participant> participants, CardDeck cardDeck) {
        this.participants = participants;
        this.cardDeck = cardDeck;
    }

    public List<Participant> getPlayers() {
        return participants;
    }

    // 게임이 시작되면 모든 플레이어에게 카드를 2장씩 나눠준다.
    public void start() {
        for (Participant participant : participants) {
            participant.addCard(cardDeck.getAndRemoveFrontCard());
            participant.addCard(cardDeck.getAndRemoveFrontCard());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BlackJackManager that = (BlackJackManager) o;
        return Objects.equals(participants, that.participants) && Objects.equals(
            cardDeck, that.cardDeck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participants, cardDeck);
    }
}
