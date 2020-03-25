package blackjack.view;

import blackjack.controller.dto.request.BettingDto;
import blackjack.controller.dto.request.NamesRequestDto;
import blackjack.controller.dto.request.PlayerAnswer;

import java.util.List;

public interface InputView {

    NamesRequestDto askPlayerNames();

    BettingDto askBettingMoney(List<String> names);

    PlayerAnswer askPlayerAnswer(String name);

}
