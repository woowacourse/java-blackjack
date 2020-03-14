package blackjack.view;

import blackjack.controller.dto.GamersResultResponse;
import blackjack.controller.dto.HandResponseDto;

import java.util.List;

public interface OutputView {

    void printInitialCard(List<HandResponseDto> handResponseDtos);

    void printHand(HandResponseDto handResponseDto);

    void printHandWithScore(List<HandResponseDto> handResponseDto);

    void printResult(GamersResultResponse gamersResultResponse);

}
