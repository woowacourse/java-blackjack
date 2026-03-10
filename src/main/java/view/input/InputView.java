package view.input;

import dto.AgreementRequestDto;
import dto.NameRequestDto;

public interface InputView {
    NameRequestDto askGamblerNames();
    AgreementRequestDto askHitOrStand(String name);
}
