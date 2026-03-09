package domain;

import domain.card.Card;
import domain.card.ShuffleStrategy;

import java.util.List;

public class NoShuffleStrategy implements ShuffleStrategy {

    @Override
    public void shuffle(List<Card> cards) {
        // 테스트용: 카드를 섞지 않음
    }
}
