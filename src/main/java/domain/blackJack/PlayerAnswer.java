package domain.blackJack;

import domain.participant.Player;

@FunctionalInterface
public interface PlayerAnswer {
    boolean apply(Player player);
}
