package main;

public class Wizard extends Player {

    public static final int HP_INIT = 400;
    public static final int HP_LEVEL = 30;

    protected static final String PLAYERNAME = new String("W");

    protected Wizard() {
        super(HP_INIT, PLAYERNAME, HP_INIT, HP_LEVEL);
    }


    /*
     * (non-Javadoc)
     * @see Player#power1(Player, Land)
     */



    @Override
    public float power1(final Player player, final Land land) {


        final float percentageInitial = 0.2f;
        final float percentageLevel = 0.05f;

        float percentage = percentageInitial + nrLevels * percentageLevel;

        float amplifyRace = calculateAmplifyRace1(player);
        float amplifyLand = calculateAmplifyLand(land);

        percentage = percentage * (1f + amplifyRace);

        final float const1 = 0.3f;


        float dPoints = percentage * min(const1 * (player.hpinit + player.nrLevels
                * player.hpLevel), player.hp);

        Math.round(dPoints);

        dPoints = dPoints + Math.round(amplifyLand * dPoints);

        return Math.round(dPoints);




    }
/*
 * (non-Javadoc)
 * @see Player#power2(Player, Land)
 */
    @Override
    public float power2(final Player player, final Land land) {

        if (player instanceof Wizard) {
            return 0;
        }

        final float base = 0.35f;
        final float lvlBase = 0.02f;

        float percentage = base + lvlBase * nrLevels;

        final float percentageMaximum = 0.7f;

        if (percentage > percentageMaximum) {
            percentage = percentageMaximum;
        }


        if (player.damagePower1 == 0 && player.damagePower2 == 0) {
            float dPower1 = player.power1(this, land);
            float dPower2 = player.power2(this, land);
            if (player instanceof Rogue) {
                ((Rogue) player).nrHits--;
            }
        }

        float amplifyRace = calculateAmplifyRace2(player);
        float amplifyLand = calculateAmplifyLand(land);

        float damage = player.damagePower1 + player.damagePower2;


        float dPoints = percentage * damage;

        dPoints = dPoints *  (1f + amplifyLand);
        dPoints = dPoints * (1f + amplifyRace);

        return Math.round(dPoints);
    }

}


