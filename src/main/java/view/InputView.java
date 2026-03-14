package view;

import java.util.List;

public interface InputView {
    List<String> readNames();

    String readBetAmountValue();

    Boolean wantToHit();
}
