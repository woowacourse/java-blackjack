package participant;

import constant.WinningResult;
import game.Deck;

public class Player extends Participant {

    private final Nickname nickname;

    public Player(Nickname nickname, Deck deck) {
        super(deck);
        this.nickname = nickname;
    }

    public WinningResult compareTo(int dealerScore) {
        int playerScore = sumCardNumbers();
        return WinningResult.getWinningResult(playerScore, dealerScore);
    }

    public String getNickname() {
        return nickname.getValue();
    }
}
