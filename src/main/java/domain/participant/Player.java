package domain.participant;

import domain.card.Card;
import domain.card.CardBundle;

import java.util.List;
import java.util.Objects;

public class Player {

    private final PlayerName name;
    private final CardBundle cardBundle;

    private Player(PlayerName name) {
        this.cardBundle = CardBundle.empty();
        this.name = name;
    }

    public static Player from(PlayerName name) {
        return new Player(name);
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
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(cardBundle, player.cardBundle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cardBundle);
    }
}
