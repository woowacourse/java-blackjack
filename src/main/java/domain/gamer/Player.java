package domain.gamer;

import domain.card.Card;
import domain.card.CardBundle;
import domain.gamer.dto.PlayerHandDto;

import java.util.List;

public class Player extends Gamer{

    private Player(PlayerName name) {
        super(name);
    }

    public static Player from(PlayerName name) {
        return new Player(name);
    }

    public CardBundle addCardBundle(CardBundle newCardBundle) {
        cardBundle = cardBundle.add(newCardBundle);
        return cardBundle;
    }

    public List<String> disPlayMyCardBundle() {
        return cardBundle.toDisplay();
    }

    public PlayerHandDto getMyHands() {
        return PlayerHandDto.of(this);
    }

}
