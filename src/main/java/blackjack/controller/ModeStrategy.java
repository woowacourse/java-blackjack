package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.attribute.Name;

import java.util.List;

public interface ModeStrategy<T extends Player> {
    Players<T> createPlayers(List<Name> names);

    void showResult(Players<T> players, Dealer dealer);
}
