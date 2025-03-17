package domain.participant;

import domain.blackjackgame.TrumpCard;
import exception.BlackJackException;
import java.util.List;

public class Player extends BlackjackParticipant {

    private static final String INVALID_PLAYER_NAME = "일반 플레이어는 딜러일 수 없습니다.";

    public Player(String name, List<TrumpCard> cards, int bet) {
        super(name, new BlackjackBet(bet), cards);
        validatePlayerName(name);
    }

    public Player(String name, List<TrumpCard> cards) {
        super(name, cards);
        validatePlayerName(name);
    }

    private void validatePlayerName(String name) {
        if (name.equals(BlackjackParticipant.dealerName())) {
            throw new BlackJackException(INVALID_PLAYER_NAME);
        }
    }

    @Override
    public boolean isDrawable() {
        return !isBust();
    }
}
