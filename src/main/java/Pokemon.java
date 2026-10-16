import java.util.ArrayList;

public class Pokemon {

    private String name;
    private Type type;
    private int maxHp;
    private   int currentHp;
    private ArrayList<Attack> attacks = new ArrayList<>();
    private int speed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank() && !name.contains(",") && name.length() <= 20) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Not a valid Pokemon name");
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        if (type != null) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Not a valid Pokemon type");
        }
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        if (maxHp >= 1 && maxHp <= 999) {
            this.maxHp = maxHp;
        } else {
            throw new IllegalArgumentException("HP must be between 1 and 999");
        }
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        if (currentHp >= 0 && currentHp <= maxHp) {
            this.currentHp = currentHp;
        } else {
            throw new IllegalArgumentException("Current HP must be between 0 and max HP");
        }
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(ArrayList<Attack> attacks) {
        this.attacks = attacks;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        if (speed < 1 || speed > 255) {
            throw new IllegalArgumentException("Speed must be between 1 and 255");
        }
        this.speed = speed;
    }

    public Pokemon(String name, Type type, int maxHp, int speed) {
        setName(name);
        setType(type);
        setMaxHp(maxHp);
        this.currentHp = maxHp;
        setSpeed(speed);
    }
    @Override
    public String toString() {
        return "Name: " + name +
                "\nType: " + type +
                "\nHP: " + currentHp + "/" + maxHp +
                "\nSpeed: " + speed;

    }
    public void takeDamage(int damage) {
        int newHp = currentHp - damage;
        if(newHp <= 0){
            setCurrentHp(0);
        }else {
            setCurrentHp(newHp);
        }

    }
    public boolean isFainted() {
        if (currentHp < 1){
            return true;
        }
        else {
            return false;
        }
    }
    public void heal(int healAmmount) {
        if (healAmmount < 0){
            return;
        }
        int newHp = currentHp + healAmmount;
        if(newHp > maxHp){
            setCurrentHp(maxHp);
        }else {
            setCurrentHp(newHp);
        }
    }
    public Pokemon createBattleCopy() {
        Pokemon copy = new Pokemon(name, type, maxHp, speed);
        copy.getAttacks().addAll(attacks);
        return copy;
    }

}
