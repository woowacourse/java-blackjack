package domain.game;

import domain.participant.Gambler;

public interface ExtraCardsInteract {

    boolean listenReceives(Gambler gambler);

    void notifyReceived(Gambler gambler);
}
