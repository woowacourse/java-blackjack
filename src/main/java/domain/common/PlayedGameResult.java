package domain.common;

import java.util.List;

public record PlayedGameResult(NameAndCardInfos infos, int scoreSum) {

    public static PlayedGameResult from(String name, List<CardInfo> cardInfos, int scoreSum) {
        NameAndCardInfos infos = new NameAndCardInfos(name, cardInfos);
        return new PlayedGameResult(infos, scoreSum);
    }

    public String name() {
        return infos.name();
    }

    public List<String> cardInfos() {
        return infos.cardInfos().stream()
                .map(CardInfo::toString)
                .toList();
    }

    private record NameAndCardInfos(String name, List<CardInfo> cardInfos) {
    }
}
