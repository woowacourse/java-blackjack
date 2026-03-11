package domain.game_playing;

import domain.common.CardInfo;

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
