package user;

import card.Card;
import java.util.List;
import playerState.Hittable;
import playerState.PlayerStatus;
import playerState.dealerState.UnderSixteen;

public class Player {

    private final PlayerName playerName;
    private PlayerStatus playerStatus;

    public Player(PlayerName playerName, PlayerStatus playerStatus) {
        if (playerStatus instanceof UnderSixteen) {
            throw new IllegalArgumentException("플레이어가 가질 수 없는 상태입니다.");
        }
        this.playerName = playerName;
        this.playerStatus = playerStatus;
    }

    public void addCard(Card card) {
        playerStatus = playerStatus.addCard(card);
    }

    public boolean isNotFinish() {
        return playerStatus instanceof Hittable;
    }

    public String getName() {
        return playerName.getName();
    }

    public List<Card> getCards() {
        return playerStatus.getCards();
    }

    public int getFinalBenefit(PlayerStatus dealerState) {
        return playerStatus.getFinalState(dealerState).getGameStatus().getFinalBenefit();
    }

    public int getBetAmount() {
        return playerStatus.getBetAmount();
    }

    public int getPoint() {
        return playerStatus.getPoint();
    }
}
