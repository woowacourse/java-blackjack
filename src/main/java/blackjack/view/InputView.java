package blackjack.view;

import blackjack.controller.dto.NamesRequestDto;
import blackjack.domain.rule.PlayerAnswer;

public interface InputView {

    NamesRequestDto inputPlayerNames();

    PlayerAnswer inputPlayerAnswer(String name);

}
