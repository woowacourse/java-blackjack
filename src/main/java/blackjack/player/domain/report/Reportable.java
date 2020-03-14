package blackjack.player.domain.report;

import blackjack.player.domain.Player;

public interface Reportable {
    GameReport createReport(Player player);
}
