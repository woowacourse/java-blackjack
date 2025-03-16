package blackjack.view;

import java.util.List;

public interface InputView {

    List<String> readParticipantsNames();

    String readOneMoreCardResponse(String name);
}
