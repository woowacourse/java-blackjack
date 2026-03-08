package domain.vo;

import domain.constant.CardMark;
import domain.constant.CardRank;

public record Card(CardRank rank, CardMark mark) {

    public boolean isAce() {
        return rank == CardRank.ACE;
    }

    public CardInfo info () {
        return new CardInfo(rank.label(), mark.description());
    }

    public int score() {
        return rank.score();
    }
}
