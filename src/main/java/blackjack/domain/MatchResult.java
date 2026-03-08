package blackjack.domain;

import java.util.Map;

public enum MatchResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String display;

    MatchResult(String display) {
        this.display = display;
    }

    public static MatchResult playerResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }

        if (dealer.isBust()) {
            return WIN;
        }

        if (player.score() > dealer.score()) {
            return WIN;
        }

        if (player.score() < dealer.score()) {
            return LOSE;
        }

        return DRAW;
    }

    public static Map<String, Long> dealerResult(Map<Player, MatchResult> playerResults) {
        return Map.of(
                "승", playerResults.values().stream().filter(r -> r == LOSE).count(),
                "패", playerResults.values().stream().filter(r -> r == WIN).count(),
                "무", playerResults.values().stream().filter(r -> r == DRAW).count()
        );
    }

    public String getDisplay() {
        return display;
    }
}
