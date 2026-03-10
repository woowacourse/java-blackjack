package view.output;

import dto.DealerResultDto;
import dto.ParticipantHandResponseDto;
import dto.ParticipantsGameInfoDto;
import dto.ParticipantsHandResponseDto;
import java.util.List;
import java.util.Map;

public interface OutputView {
    void printInitialDeal(List<String> names);
    void printParticipantsInfo(ParticipantsHandResponseDto responseDto);
    void printDealerCardIsUnder16();
    void printParticipantInfo(ParticipantHandResponseDto responseDto);
    void printParticipantsGameInfo(ParticipantsGameInfoDto responseDto);
    void printDealerResult(DealerResultDto resultDto);
    void printGamblerResult(Map<String, String> gamblersResult);
}
