package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements Participant {

    private final String name;
    private ParticipantCards participantCards;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름에 빈 값이 올 수 없습니다.");
        }
    }

    public void receiveInitCards(List<Card> cards) {
        participantCards = new ParticipantCards(new ArrayList<>(cards));
    }

    public void receiveCard(Card card) {
        participantCards.addCard(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(participantCards.getCards());
    }

}
