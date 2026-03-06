package model;

import constant.ErrorMessage;
import model.dto.Card;
import model.dto.PlayerName;
import model.dto.PlayerResult;

public class Participant {
    private final PlayerName name;
    private Integer score = 0;
    private final Cards deck = new Cards();

    public Participant(PlayerName name) {
        this.name = name;
    }

    public PlayerResult getResult() {
        return new PlayerResult(name, deck.get(), score);
    }

    public void addCard(Card card) {
        validateCardDuplicate(card);
        deck.add(card);
    }

    /*점수 갱신(int 점수 받기)*/
    public void addScore(Integer score) {
        this.score += score;
    }


    private void validateCardDuplicate(Card card) {
        if(deck.get().contains(card)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_CARD_IN_DECK.getMessage());
        }
    }


}
