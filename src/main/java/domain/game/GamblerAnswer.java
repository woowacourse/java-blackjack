package domain.game;

import domain.participant.Gambler;

public interface GamblerAnswer {

    boolean isAnswerOK(Gambler gambler);

    void notifyResult(Gambler gambler);
}
