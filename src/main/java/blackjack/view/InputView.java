package blackjack.view;

import blackjack.controller.dto.request.NamesRequestDto;
import blackjack.domain.rule.PlayerAnswer;

public interface InputView {

    NamesRequestDto inputPlayerNames();

    PlayerAnswer inputPlayerAnswer(String name);

}
