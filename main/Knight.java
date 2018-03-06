package main;

public class Knight extends Player {
    public static final float HP_INIT = 900;
    public static final int HP_LEVEL = 80;


    protected static final String PLAYERNAME = new String("K");

    protected Knight() {
        super(HP_INIT, PLAYERNAME, HP_INIT, HP_LEVEL);
    }
    /*
     * (non-Javadoc)
     * @see main.Player#power1(main.Player, main.Land)
     * This function returns the damage of the Execute ability
     */
    @Override
    public float power1(final Player player, final Land land) {

        final float damageInit = 200;
        final float dLevel = 30;



        final float percentageInit = 0.2f;
        final float percentLevel = 0.01f;

        float percentage = percentageInit + nrLevels * percentLevel;

        final float percentageMaximum = 0.4f;

        if (percentage > percentageMaximum) {
            percentage = percentageMaximum;
        }

        final float hpLimit = percentage * (player.hpinit + player.hpLevel * player.nrLevels);


        float amplifyLand = calculateAmplifyLand(land);
        float amplifyRace = calculateAmplifyRace1(player);
        float dPoints;

        if (player.hp < hpLimit) {
            dPoints = player.hp;
            damagePower1 = player.hp * (1f + amplifyLand);
            damagePower1 = Math.round(damagePower1);
            return Math.round(dPoints);
        }

        dPoints = damageInit + nrLevels * dLevel;
        damagePower1 = dPoints * (1f + amplifyLand);

        damagePower1 = Math.round(damagePower1);

        dPoints = damagePower1 * (1f + amplifyRace);
        return Math.round(dPoints);
    }

    /*
     * @Override(non-Javadoc)
     * @see Player#power2(Player, Land)
     */
    @Override
    public float power2(final Player player, final Land land) {

        final float damage = 100;
        final float dLevel = 40;
        damagePower2 = damage + nrLevels * dLevel;

        float amplifyLand = calculateAmplifyLand(land);

        float amplifyRace = calculateAmplifyRace2(player);

        damagePower2 = damagePower2 * (1f + amplifyLand);
        damagePower2 = Math.round(damagePower2);


        float dPoints = damagePower2 * (1f + amplifyRace);


        player.spell = 1;
        player.dOvertime = 0;
        player.lvlOvertime = 0;

        return Math.round(dPoints);
    }
}
