package dto.response;

import domain.common.PlayedGameResult;
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
        return infos.cardInfos();
    }

    private record NameAndCardInfos(String name, List<String> cardInfos) {
    }
}
