package dto;

import domain.PlayedGameResult;
import domain.vo.CardInfo;
import domain.vo.NameAndCardInfos;
import java.util.List;

public record PlayedGameResultResponse(String name, List<String> cardInfos, int scoreSum) {

    public static PlayedGameResultResponse from(PlayedGameResult result) {
        NameAndCardInfos infos = result.infos();
        return new PlayedGameResultResponse(infos.name(), parse(infos.cardInfos()), result.scoreSum());
    }

    private static List<String> parse(List<CardInfo> infos) {
        return infos.stream()
                .map(PlayedGameResultResponse::parseCardInfo)
                .toList();
    }

    private static String parseCardInfo(CardInfo info) {
        return info.cardLabel() + info.cardMark();
    }
}
