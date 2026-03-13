package view.input;

import dto.request.AgreementRequestDto;
import dto.request.BetAmountRequestDto;
import dto.request.NameRequestDto;

public interface InputView {
    NameRequestDto askGamblerNames();
    BetAmountRequestDto askGamblerBetAmount(String name);
    AgreementRequestDto askHitOrStand(String name);
}
