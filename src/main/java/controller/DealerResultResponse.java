package controller;

import model.user.Result;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class DealerResultResponse {

    private final Map<String, Long> dealerResults;
    private final List<String> printOrders;

    public DealerResultResponse(final Map<Result, Long> dealerResults, final Result[] printOrders) {
        this.dealerResults = createDealerResults(dealerResults);
        this.printOrders = createPrintOrder(printOrders);
    }

    private static Map<String, Long> createDealerResults(final Map<Result, Long> dealerResults) {
        return dealerResults.keySet().stream()
                .collect(toMap(Result::getName, dealerResults::get));
    }

    private List<String> createPrintOrder(final Result[] printOrders) {
        return Arrays.stream(printOrders)
                .map(Result::getName)
                .collect(toList());
    }

    public Map<String, Long> getDealerResults() {
        return new HashMap<>(dealerResults);
    }

    public List<String> getPrintOrder() {
        return Collections.unmodifiableList(printOrders);
    }
}
