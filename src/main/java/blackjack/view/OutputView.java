package blackjack.view;

import blackjack.controller.dto.response.GamersResultResponse;
import blackjack.controller.dto.response.HandResponseDto;
import blackjack.controller.dto.response.HandResponseDtos;

public interface OutputView {

    void printInitialHand(HandResponseDtos handResponseDtos);

    void printHand(HandResponseDto handResponseDto);

    void printHandWithScore(HandResponseDtos handResponseDtos);

    void printResult(GamersResultResponse gamersResultResponse);

    void printDealerDrawCard();

}
