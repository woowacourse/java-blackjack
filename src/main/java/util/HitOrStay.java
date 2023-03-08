package util;

import domain.player.Participant;

@FunctionalInterface
public interface HitOrStay {

    boolean isHit(Participant participant);
}
