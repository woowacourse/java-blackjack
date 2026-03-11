package domain.participant;

import domain.card.Card;
import domain.card.CardBundle;
import domain.card.CardDeck;
import java.util.List;

public abstract class Participant {

    protected final ParticipantName name;
    protected final CardBundle cardBundle;

    protected Participant(ParticipantName name) {
        this.cardBundle = CardBundle.empty();
        this.name = name;
    }

    public CardBundle drawCards(CardDeck cardDeck, int count) {
        return cardDeck.draw(cardBundle, count);
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

}
