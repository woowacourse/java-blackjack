package domain.gameplaying;

import dto.CardInfo;

public record Card(CardRank rank, CardMark cardMark) {

    boolean isAce() {
        return rank == CardRank.ACE;
    }

    CardInfo info() {
        return new CardInfo(rank.label(), cardMark.description());
    }

    int score() {
        return rank.score();
    }
}
