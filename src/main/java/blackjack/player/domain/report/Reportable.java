package blackjack.player.domain.report;

import blackjack.player.domain.Gambler;

public interface Reportable {
    GameReport createReport(Gambler player);
}
