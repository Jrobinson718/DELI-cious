package com.pluralsight;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DeliMenu {

    private static final Map<Integer, Sandwich> SIGNATURE_SANDWICHES = new HashMap<>();
    static {
        SIGNATURE_SANDWICHES.put(1, new PhillyCheeseSteak());
        SIGNATURE_SANDWICHES.put(2, new BLT());
    }

    public static Map<Integer, Sandwich> getSignatureSandwiches() {
        return Collections.unmodifiableMap(SIGNATURE_SANDWICHES);
    }
}
