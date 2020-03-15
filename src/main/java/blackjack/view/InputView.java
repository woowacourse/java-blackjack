package blackjack.view;

import blackjack.controller.dto.request.NamesRequestDto;
import blackjack.controller.dto.request.PlayerAnswer;

public interface InputView {

    NamesRequestDto askPlayerNames();

    PlayerAnswer askPlayerAnswer(String name);

}
