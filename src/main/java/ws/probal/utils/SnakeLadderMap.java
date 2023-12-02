package ws.probal.utils;

import java.util.HashMap;
import java.util.Map;

public class SnakeLadderMap {

    private static final Map<Integer, Integer> positionRuleMap = new HashMap<>();

    static {
        // ladders
        positionRuleMap.put(1, 38);
        positionRuleMap.put(4, 14);
        positionRuleMap.put(8, 30);
        positionRuleMap.put(21, 42);
        positionRuleMap.put(28, 76);
        positionRuleMap.put(50, 67);
        positionRuleMap.put(71, 92);
        positionRuleMap.put(80, 99);

        // snakes
        positionRuleMap.put(32, 10);
        positionRuleMap.put(36, 6);
        positionRuleMap.put(48, 26);
        positionRuleMap.put(62, 18);
        positionRuleMap.put(88, 24);
        positionRuleMap.put(95, 56);
        positionRuleMap.put(97, 78);
    }

    public static int getPosition(int dieValue) {
        return positionRuleMap.getOrDefault(dieValue, dieValue);
    }
}
