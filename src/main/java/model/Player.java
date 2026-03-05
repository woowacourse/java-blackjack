package model;

import model.dto.Card;
import model.dto.PlayerResult;

public class Player {

    private final Participant player;

    public Player(PlayerName name) {
        validate(name);
        this.player = new Participant(name);
    }

    private void validate(PlayerName name) {
        if(name.get().equals("딜러")) {
            throw new IllegalArgumentException("플레이어는 '딜러'라는 이름을 가질 수 없습니다.");
        }
    }

    public PlayerResult getResult() {
        return player.getResult();
    }

    public void addCard(Card card) {
        player.addCard(card);
    }

    public void addScore(Integer score) {
        player.addScore(score);
    }
}
