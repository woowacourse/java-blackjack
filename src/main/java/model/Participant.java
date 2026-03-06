package model;

import constant.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import model.dto.Card;
import model.dto.PlayerName;
import model.dto.PlayerResult;

public class Participant {
    private final PlayerName name;
    private Integer score = 0;
    private final List<Card> deck = new ArrayList<>();

    public Participant(PlayerName name) {
        this.name = name;
    }

    public PlayerResult getResult() {
        return new PlayerResult(name, List.copyOf(deck), score);
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
        if(deck.contains(card)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_CARD_IN_DECK.getMessage());
        }
    }


}
