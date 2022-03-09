package fuelinjection.view;

import java.util.Map;

public class ResultView {
    private static final String FORMAT_FUEL_MAP = "\"%s : %d리터\"%n";

    public void printFuelMap(Map<String, Integer> fuelMap) {
        for (String carName : fuelMap.keySet()) {
            System.out.printf(FORMAT_FUEL_MAP, carName, fuelMap.get(carName));
        }
    }
}
