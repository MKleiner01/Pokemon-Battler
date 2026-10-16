public class Attack {

    private String name;
    private int baseDamage;
    private int accuracy;
    private Type type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank() && !name.contains(",") && name.length() <= 20) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Not a valid attack name");
        }
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        if (baseDamage >= 1 && baseDamage <= 500) {
            this.baseDamage = baseDamage;
        } else {
            throw new IllegalArgumentException("Damage must be between 1 and 500");
        }
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        if (accuracy >= 1 && accuracy <= 100) {
            this.accuracy = accuracy;
        } else {
            throw new IllegalArgumentException("Accuracy must be between 1 and 100");
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        if (type != null) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Not a valid attack type");
        }
    }

    public Attack(String name, int baseDamage, int accuracy, Type type) {
        setName(name);
        setBaseDamage(baseDamage);
        setAccuracy(accuracy);
        setType(type);
    }

    @Override
    public String toString() {
        return "Attack name: " + name +
                " Type: " + type +
                " Damage: " + baseDamage +
                " Accuracy: " + accuracy +
                "\n";
    }


}
