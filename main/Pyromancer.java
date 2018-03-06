package main;

public class Pyromancer extends Player {

    public static final int HP_INIT = 500;
    public static final int HP_LEVEL = 50;
    protected static final String PLAYERNAME = new String("P");

    protected Pyromancer() {
        super(HP_INIT, PLAYERNAME, HP_INIT, HP_LEVEL);
    }

    /*
     * (non-Javadoc)
     * @see main.Player#power1(main.Player, main.Land)
     * This function returns the damage of the Fireblast ability
     */
    @Override
    public float power1(final Player player, final Land land) {
        final float damage = 350;
        final float dLevel = 50;
        damagePower1 = damage + nrLevels * dLevel;

        float amplifyRace = 0;
        float amplifyLand = 0;

        amplifyLand = calculateAmplifyLand(land);
        amplifyRace = calculateAmplifyRace1(player);

        damagePower1 = damagePower1 * (1f + amplifyLand);
        damagePower1 = Math.round(damagePower1);


        float dPoints = damagePower1 * (1f + amplifyRace);

        return Math.round(dPoints);
    }


    /*
     * (non-Javadoc)
     * @see main.Player#power2(main.Player, main.Land)
     * This function returns the damage of the Ignite ability
     */
    @Override
    public float power2(final Player player, final Land land) {

        final float basedamage = 150;
        final float baseLevel = 20;

        final float damage = 50;
        final float damageLevel = 30;
        final int lvlDamage = 2;

        damagePower2 = basedamage + nrLevels * baseLevel;

        float amplifyRace = 0;
        float amplifyLand = 0;

        amplifyLand = calculateAmplifyLand(land);
        amplifyRace = calculateAmplifyRace2(player);

        damagePower2 = damagePower2 * (1f + amplifyLand);
        damagePower2 = Math.round(damagePower2);


        float dPoints = damagePower2 * (1f + amplifyRace);

        player.spell = 0;

        player.dOvertime = damage + nrLevels * damageLevel;
        player.dOvertime = player.dOvertime * (1f + amplifyLand);
        player.dOvertime = Math.round(player.dOvertime);
        player.dOvertime = player.dOvertime * (1f + amplifyRace);
        player.dOvertime = Math.round(player.dOvertime);

        player.lvlOvertime = lvlDamage;

        return Math.round(dPoints);
    }
}
