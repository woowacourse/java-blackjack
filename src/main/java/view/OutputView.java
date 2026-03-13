package view;

import dto.GameResultDto;
import dto.GameStateDto;
import dto.ParticipantDto;

public interface OutputView {
    void printErrorMessage(Exception e);

    void printNamePrompt();

    void printInitialStates(GameStateDto gameStateDto);

    void printHitOrStandPrompt(String name);

    void printUserState(ParticipantDto participantDto);

    void printDealerAddCardNotice();

    void printGameResult(GameResultDto gameResultDto);
}
