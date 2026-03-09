package domain;

import domain.vo.NameAndCardInfos;

public record PlayedGameResult(NameAndCardInfos infos, int scoreSum) {
    public static PlayedGameResult from(Participant participant) {
        return new PlayedGameResult(participant.infos(), participant.scoreSum());
    }
}
