class PlayerCharacter extends GameObject {
    private int health;

    public PlayerCharacter(String name, double[] position, int health) {

        super(name, position);
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void attack(PlayerCharacter playerToAttack) {
        System.out.println(this.getName() + " attacks " + playerToAttack.getName() + ".");
        playerToAttack.setHealth(playerToAttack.getHealth() - 5);
        System.out.printf("%s's health is now %d points.%n", playerToAttack.getName(), playerToAttack.getHealth());
    }

    public static void main(String[] args) {
        PlayerCharacter wizard = new PlayerCharacter("Wizard", new double[]{4.0, -5.5}, 10);
        PlayerCharacter orc = new PlayerCharacter("Orc", new double[]{-4.0, 5.5}, 5);

        // Draw both characters on the screen
        wizard.draw();
        orc.draw();

        // Make the wizard attack the orc
        wizard.attack(orc);

        // Make the orc disappear from the screen
        orc.erase();
    }
}

class GameObject {
    private String name;
    private double[] position;

    public GameObject(String name, double[] position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public void draw() {
        System.out.printf("%s is drawn at position (%.1f, %.1f).%n", name, position[0], position[1]);
    }

    public void erase() {
        System.out.println(name + " has disappeared from the screen.");
    }
}

