package view.input;

import dto.request.AgreementRequestDto;
import dto.request.NameRequestDto;

public interface InputView {
    NameRequestDto askGamblerNames();
    AgreementRequestDto askHitOrStand(String name);
}
