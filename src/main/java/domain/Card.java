package domain;

import domain.vo.CardInfo;

public record Card(CardRank rank, CardMark mark) {

    boolean isAce() {
        return rank == CardRank.ACE;
    }

    CardInfo info () {
        return new CardInfo(rank.label(), mark.description());
    }

    int score() {
        return rank.score();
    }
}
