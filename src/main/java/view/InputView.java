package view;

import domain.BetAmount;
import java.util.List;

public interface InputView {
    List<String> readNames();

    BetAmount readBetAmountValue();

    Boolean wantToHit();
}
