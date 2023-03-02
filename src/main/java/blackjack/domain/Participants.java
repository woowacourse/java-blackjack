package blackjack.domain;

import java.util.List;

public class Participants {

    private final List<Participant> participants;

    public Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public int size() {
        return participants.size();
    }

    public void receiveSettingCards(List<Card> settingCards) {
        for (int index = 0; index < settingCards.size() - 1; index += 2) {
            distribute(settingCards, index);
        }
    }

    private void distribute(final List<Card> settingCards, final int index) {
        int firstCard = index;
        int secondCard = index + 1;

        for (Participant participant : participants) {
            participant.receiveCard(settingCards.get(firstCard));
            participant.receiveCard(settingCards.get(secondCard));
        }
    }
}
