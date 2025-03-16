package blackjack.view;

import blackjack.constant.UserAction;
import java.util.List;

public interface InputView {

    List<String> readParticipantsNames();

    UserAction readOneMoreCardResponse(String name);

    int readParticipantsBetAmount(String playerName);
}
