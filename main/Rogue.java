package main;

public class Rogue extends Player {

    public static final float HP_INIT = 600;
    public static final int HP_LEVEL = 40;

    protected int nrHits = 0;

    protected static final String PLAYERNAME = new String("R");

    protected Rogue() {
        super(HP_INIT, PLAYERNAME, HP_INIT, HP_LEVEL);
    }


    /*
     * (non-Javadoc)
     * @see Player#power1(Player, Land)
     */
    @Override
    public float power1(final Player player, final Land land) {


        final float damage = 200;
        final float dLevel = 20;

        damagePower1 = damage + nrLevels * dLevel;

        float amplifyLand = calculateAmplifyLand(land);
        float amplifyRace = calculateAmplifyRace1(player);
        float amplifyHits = 0;


        final float const1 = 3;

        if (amplifyLand != 0 && nrHits % const1 == 0) {
            final float const2 = 0.5f;
            amplifyHits = const2;

        }
        nrHits++;

        damagePower1 = damagePower1 * (1f + amplifyLand);
        float dPoints = damagePower1 * (1f + amplifyRace);

        damagePower1 = damagePower1 * (1f + amplifyHits);
        dPoints = dPoints * (1f + amplifyHits);
        //System.out.println(dPoints + "   ");

        return Math.round(dPoints);

    }


    /*
     * @Override(non-Javadoc)
     * @see Player#power2(Player, Land)
     */
    @Override
    public float power2(final Player player, final Land land) {

        final float damage = 40;
        final float dLevel = 10;

        damagePower2 = damage + nrLevels * dLevel;

        float amplifyLand = calculateAmplifyLand(land);
        float amplifyRace = calculateAmplifyRace2(player);

        damagePower2 = damagePower2 * (1f + amplifyLand);
        damagePower2 = Math.round(damagePower2);

        float dPoints = damagePower2 * (1f + amplifyRace);
        dPoints = Math.round(dPoints);

        if (amplifyLand != 0) {
            final int const1 = 6;
            player.lvlOvertime = const1;

        } else {
            final int const1 = 3;
            player.lvlOvertime = const1;
        }

        //System.out.println(dPoints + "   ");
        player.dOvertime = damage + nrLevels * dLevel;
        player.dOvertime = player.dOvertime * (1f + amplifyLand);
        player.dOvertime = Math.round(player.dOvertime);
        player.dOvertime = player.dOvertime * (1f + amplifyRace);
        player.dOvertime = Math.round(player.dOvertime);
       // System.out.println(player.dOvertime + "   ");
        player.spell = player.lvlOvertime;

        return Math.round(dPoints);

    }


}
