package team.blackjack.service.dto;

import java.util.Map;

public record RevenueResult(
        double dealerRevenue,
        Map<String, Double> playerRevenueMap
) { }
