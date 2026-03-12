package view.output;

import dto.response.DealerResultDto;
import dto.response.ParticipantHandResponseDto;
import dto.response.ParticipantsGameInfoDto;
import dto.response.ParticipantsHandResponseDto;
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
