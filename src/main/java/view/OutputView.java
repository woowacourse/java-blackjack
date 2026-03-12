package view;

import dto.GameResultDto;
import dto.ParticipantDto;
import java.util.List;

public interface OutputView {
    void printErrorMessage(Exception e);

    void printNamePrompt();

    void printInitialStates(ParticipantDto dealerDto, List<ParticipantDto> players);

    void printHitOrStandPrompt(String name);

    void printUserState(ParticipantDto participantDto);

    void printDealerAddCardNotice();

    void printGameResult(GameResultDto gameResultDto);
}
