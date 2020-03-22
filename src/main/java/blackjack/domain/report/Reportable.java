package blackjack.domain.report;

import blackjack.domain.player.Gambler;

public interface Reportable {
    GameReport createReport(Gambler player);
}
