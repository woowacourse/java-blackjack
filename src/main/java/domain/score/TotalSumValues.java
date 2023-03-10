package domain.score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Denomination;

public class TotalSumValues {

    private final List<Integer> values;

    public TotalSumValues(final List<Integer> values) {
        this.values = new ArrayList<>(values);
    }

    public TotalSumValues addValuesToAllElement(TotalSumValues totalSumValues, List<Integer> addValues) {
        for (Integer addValue : addValues) {
            totalSumValues = getTotalSumValuesByAceExistence(totalSumValues, addValue);
        }
        return totalSumValues;
    }

    private TotalSumValues getTotalSumValuesByAceExistence(TotalSumValues totalSumValues, Integer addValue) {
        if (addValue == Denomination.ACE_VALUE) {
            TotalSumValues totalSumValuesWithAceValue = addValueToAllElement(totalSumValues, Denomination.ACE_VALUE);
            TotalSumValues totalSumValuesWithAnotherAceValue = addValueToAllElement(totalSumValues, Denomination.ANOTHER_ACE_VALUE);
            totalSumValues = totalSumValuesWithAceValue.merge(totalSumValuesWithAnotherAceValue);

        }
        if (addValue != Denomination.ACE_VALUE) {
            totalSumValues = addValueToAllElement(totalSumValues, addValue);
        }
        return totalSumValues;
    }

    private TotalSumValues addValueToAllElement(TotalSumValues totalSumValues, Integer addValue) {
        return new TotalSumValues(totalSumValues.values.stream()
                .map(value -> value + addValue)
                .collect(Collectors.toUnmodifiableList())
        );
    }

    private TotalSumValues merge(TotalSumValues toMergeTotalSumValues) {
        values.addAll(toMergeTotalSumValues.values);
        return new TotalSumValues(values);
    }

    public List<Integer> getValues() {
        return values;
    }
}
