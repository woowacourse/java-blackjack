package view;

import java.util.List;

public interface InputView {
    List<String> readNames();

    int readBetAmountValue();

    Boolean wantToHit();
}
