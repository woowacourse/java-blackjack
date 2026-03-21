package blackjack.view.input;

import java.util.List;

public interface InputView {

    List<String> readPlayersName();

    Integer readBettingAmount(String playerName);

    boolean readMoreCard(String playerName);

}
