package domain.player;

import domain.card.Card;
import domain.card.CardBundle;
import domain.player.dto.PlayerHandDto;

import java.util.List;

public class Player {

    private PlayerName name;
    private CardBundle cardBundle;

    private Player(PlayerName name) {
        this.cardBundle = CardBundle.empty();
        this.name = name;
    }

    public static Player from(PlayerName name) {
        return new Player(name);
    }

    public boolean hasCard(Card targetCard) {
        return cardBundle.checkExist(targetCard);
    }

    public void setUpCardBundle(CardBundle cardBundle) {
        this.cardBundle = cardBundle;
    }

    public CardBundle addCardBundle(CardBundle newCardBundle) {
        return cardBundle.addUp(newCardBundle);
    }

//    public void addCard(Card newCard) {
//        cardBundle.addUp(newCard);
//    }

    public String toDisplayMyName() {
        return name.name();
    }

    public List<String> disPlayMyCardBundle() {
        return cardBundle.toDisplay();
    }

    public PlayerHandDto getMyHands() {
        return PlayerHandDto.of(this);
    }

}
