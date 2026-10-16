public class TypeEffectiveness {

    public static double getMultiplier(Type attackType, Type defenderType) {

        if (attackType == Type.FIRE) {
            if (defenderType == Type.GRASS) {
                return 2.0;
            }
            if (defenderType == Type.FIRE || defenderType == Type.WATER) {
                return 0.5;
            }
        }
        if (attackType == Type.WATER) {
            if (defenderType == Type.FIRE) {
                return 2.0;
            }
            if (defenderType == Type.WATER || defenderType == Type.GRASS) {
                return 0.5;
            }
        }

        if (attackType == Type.GRASS) {
            if (defenderType == Type.WATER) {
                return 2.0;
            }
            if (defenderType == Type.FIRE || defenderType == Type.GRASS) {
                return 0.5;
            }
        }

        if (attackType == Type.ELECTRIC) {
            if (defenderType == Type.WATER) {
                return 2.0;
            }
            if (defenderType == Type.ELECTRIC || defenderType == Type.GRASS) {
                return 0.5;
            }
        }
        return 1.0;
    }
}