package domain.gameplaying;

import domain.common.CardInfo;

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
