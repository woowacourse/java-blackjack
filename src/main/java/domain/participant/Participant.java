package domain.participant;

import domain.card.Card;
import domain.card.CardBundle;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    protected final ParticipantName name;
    protected final CardBundle cardBundle;

    protected Participant(ParticipantName name) {
        this.cardBundle = CardBundle.empty();
        this.name = name;
    }

    public CardBundle addCardBundle(CardBundle newCardBundle) {
        return cardBundle.addUp(newCardBundle);
    }

    public boolean hasCard(Card targetCard) {
        return cardBundle.checkExist(targetCard);
    }

    public int getResultScore() {
        return cardBundle.getResultScore();
    }

    public boolean isBusted() {
        return cardBundle.isBusted();
    }

    public String toDisplayMyName() {
        return name.name();
    }

    public List<String> disPlayMyCardBundle() {
        return cardBundle.toDisplay();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name) && Objects.equals(cardBundle, that.cardBundle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cardBundle);
    }
}
