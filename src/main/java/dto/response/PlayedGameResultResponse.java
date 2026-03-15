package dto.response;

import dto.CardInfo;
import dto.PlayedGameResult;
import java.util.List;

public record PlayedGameResultResponse(NameAndCardInfos infos, int scoreSum) {

    public static PlayedGameResultResponse from(PlayedGameResult result) {
        NameAndCardInfos infos = new NameAndCardInfos(result.name(), result.cardInfos());
        return new PlayedGameResultResponse(infos, result.scoreSum());
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
