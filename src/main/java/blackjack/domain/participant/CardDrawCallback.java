package blackjack.domain.participant;

import java.util.List;

public interface CardDrawCallback {

    boolean isContinuable(final String participantName);

    void onUpdate(final String participantName, final List<String> cardNames);

}
