package domain;

import java.util.List;

public record PlayedGameResult(NameAndCardInfos infos, int scoreSum) {

    private static final int BLACK_JACK_NUMBER = 21;
    private static final int INITIAL_CARD_COuNT = 2;

    public static PlayedGameResult from(String name, List<CardInfo> cardInfos, int scoreSum) {
        NameAndCardInfos infos = new NameAndCardInfos(name, cardInfos);
        return new PlayedGameResult(infos, scoreSum);
    }

    public String name() {
        return this.infos().name();
    }

    public List<CardInfo> cardInfos() {
        return this.infos().cardInfos();
    }

    public int cardCount() {
        return this.cardInfos().size();
    }

    public boolean isBlackJack() {
        return scoreSum == BLACK_JACK_NUMBER && this.cardCount() == INITIAL_CARD_COuNT;
    }

    public boolean isBusted() {
        return scoreSum > BLACK_JACK_NUMBER;
    }

    private record NameAndCardInfos(String name, List<CardInfo> cardInfos) {
    }
}
