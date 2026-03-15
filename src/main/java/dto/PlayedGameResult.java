package dto;

import java.util.List;

public record PlayedGameResult(NameAndCardInfos infos, int scoreSum) {

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

    private record NameAndCardInfos(String name, List<CardInfo> cardInfos) {
    }
}
