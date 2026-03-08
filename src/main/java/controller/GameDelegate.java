package controller;

import dto.GameResultDto;
import dto.ParticipantDto;
import java.util.List;

public interface GameDelegate {

    /**
     * 사용자의 입력을 요구.
     */
    List<String> askPlayerNames();

    boolean askDrawCard(String playerName);

    /**
     * 각종 정보 출력
     */
    // 초기 카드를 보여주기
    void showInitialParticipantCards(ParticipantDto dealerDto, List<ParticipantDto> playerDtos);

    // 참가자 한명의 카드를 보여주기
    void showPlayerCards(ParticipantDto participantDto);

    // 딜러가 카드 한 장 더 받았음을 보여주기
    void showDealerOneMoreCardMessage();

    // 게임의 결과를 보여주기
    void showGameResult(GameResultDto resultDto);
}
