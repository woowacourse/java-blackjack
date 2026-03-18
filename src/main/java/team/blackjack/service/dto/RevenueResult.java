package team.blackjack.service.dto;

import java.util.LinkedHashMap;

public record RevenueResult(
        double dealerRevenue,
        LinkedHashMap<String, Double> playerRevenueMap
) { }
