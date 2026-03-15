package dto.response;

import dto.CardInfo;
import dto.PlayedGameResult;
import java.util.List;

public record NameAndCardsResponse(String name, List<String> cardInfos) {

    public static NameAndCardsResponse from(PlayedGameResult infos) {
        return new NameAndCardsResponse(infos.name(), parse(infos.cardInfos()));
    }

    private static List<String> parse(List<CardInfo> infos) {
        return infos.stream()
                .map(NameAndCardsResponse::parseCardInfo)
                .toList();
    }

    private static String parseCardInfo(CardInfo info) {
        return info.cardLabel() + info.cardMark();
    }
}
