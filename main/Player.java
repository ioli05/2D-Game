package main;

public class Player {
    /*in variable hp will be stored the life points of each class type Player
     */
    protected float hp;

    /*in variable xp will be stored the experience of each class type Player
     */
    protected float xp;

    /*in variable dOvertime will be stored if we have, the damage overtime of
     * each class type Player
     */
    protected float dOvertime;

    /*in variable lvlOvertime will be stored, if we have, the number of levels
     * during each class type Player will have a damage;
     */
    protected int lvlOvertime;

    /*in variable nrLevels will be stored the current level of each class type
     *  Player
     */
    protected float nrLevels;

    /*in variable namePlayer will be stored the name of each class type Player
     */
    protected String namePlayer;

    /*in variable hp_init will be stored the maximum value of hp corresponding
     * to each class type Player
     */
    protected float hpinit;

    /*in variable spell will be stored the number of rounds during which the
     * player is forced to stay in the same place
     */
    protected float spell;

    protected int row;
    protected int column;

    protected float damagePower1;
    protected float damagePower2;
    protected float hpLevel;
    public Player() { }


    public Player(final float hp, final String namePlayer, final float hpinit,
            final float hpLevel) {

        this.hp = hp;
        this.namePlayer = namePlayer;
        this.hpinit = hpinit;
        this.hpLevel = hpLevel;
    }
/*
 *
 */
    public float power1(final Player player, final Land land) {
        return 0;
    }
/*
 *
 */
    public float power2(final Player player, final Land land) {
        return 0;
    }
/*lala
 *
 */
    protected float max(final float x, final float y) {
        if (x > y) {
            return x;
        }
        return y;
    }
    /*lala
    *
    */

    protected float min(final float x, final float y) {
        if (x < y) {
            return x;
        }
        return y;
    }
    /*
     *
     */
    protected void xpWinner(final Player player) {
        final int const1 = 200;
        final int const2 = 40;

        xp = xp + max(0, const1 - (nrLevels - player.nrLevels) * const2);

    }
/*
 *
 */
    protected void hpNew(final Player player) {
        player.hp = player.hpinit + player.hpLevel * player.nrLevels;
    }
    /*
     *
     */
    protected float levelUP() {
        final int const1 = 250;
        final int const2 = 50;
        return const1 + nrLevels * const2;
    }

    protected final float calculateLevel() {
        float x = levelUP();
        float nrLevel = 0;
        if (xp > x) {
            float aux = xp;
            final float xpLevel0 = 250;
            final float xpUp = 50;
            while (aux >= xpLevel0) {
                aux = aux - xpUp;
                nrLevel++;
            }
        }
        return nrLevel;

    }
/*
 *
 */
    protected float calculateAmplifyLand(final Land land) {

        float amplifyLand = 0;

        if (land.typeLand.equals("V")
                && namePlayer.equals("P")) {
            final float valueAmplifier = 0.25f;
            amplifyLand = valueAmplifier;
        }

        if (land.typeLand.equals("L")
                && namePlayer.equals("K")) {
            final float valueAmplifier = 0.15f;
            amplifyLand = valueAmplifier;
        }

        if (land.typeLand.equals("D")
                && namePlayer.equals("W")) {
            final float valueAmplifier = 0.1f;
            amplifyLand = valueAmplifier;
        }

        if (land.typeLand.equals("W")
                && namePlayer.equals("R")) {
            final float valueAmplifier = 0.15f;
            amplifyLand = valueAmplifier;
        }


        return amplifyLand;
    }
/*
 *
 *
 */
    protected float calculateAmplifyRace1(final Player player) {
        float amplifyRace = 0;

        if (namePlayer.equals("P")) {
            if (player.namePlayer.equals("R")) {
                final float valueAmplifier = -0.2f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("K")) {
                final float valueAmplifier = 0.2f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("P")) {
                final float valueAmplifier = -0.1f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("W")) {
                final float valueAmplifier = 0.05f;
                amplifyRace = valueAmplifier;
            }
        }

        if (namePlayer.equals("W")) {

            if (player.namePlayer.equals("R")) {
                final float valueAmplifier = -0.2f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("K")) {
                final float valueAmplifier = 0.2f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("P")) {
                final float valueAmplifier = -0.1f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("W")) {

                final float valueAmplifier = 0.05f;
                amplifyRace = valueAmplifier;
                return amplifyRace;
            }
        }

        if (namePlayer.equals("K")) {
            if (player.namePlayer.equals("R")) {
                final float valueAmplifier = 0.15f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("K")) {
                final float valueAmplifier = 0f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("P")) {
                final float valueAmplifier = 0.1f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("W")) {
                final float valueAmplifier = -0.2f;
                amplifyRace = valueAmplifier;
            }
        }

        if (namePlayer.equals("R")) {
            if (player.namePlayer.equals("R")) {
                final float valueAmplifier = 0.2f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("K")) {
                final float valueAmplifier = -0.1f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("P")) {
                final float valueAmplifier = 0.25f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("W")) {
                final float valueAmplifier = 0.25f;
                amplifyRace = valueAmplifier;
            }
        }

        return amplifyRace;
    }
/*
 *
 */
    protected float calculateAmplifyRace2(final Player player) {
        float amplifyRace = 0;

        if (namePlayer.equals("P")) {
            if (player.namePlayer.equals("R")) {
                final float valueAmplifier = -0.2f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("K")) {
                final float valueAmplifier = 0.2f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("P")) {
                final float valueAmplifier = -0.1f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("W")) {
                final float valueAmplifier = 0.05f;
                amplifyRace = valueAmplifier;
            }
        }

        if (namePlayer.equals("W")) {
            if (player.namePlayer.equals("R")) {
                final float valueAmplifier = 0.2f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("K")) {
                final float valueAmplifier = 0.4f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("P")) {
                final float valueAmplifier = 0.3f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("W")) {
                final float valueAmplifier = 0f;
                amplifyRace = valueAmplifier;
            }
        }

        if (namePlayer.equals("K")) {
            if (player.namePlayer.equals("R")) {
                final float valueAmplifier = -0.2f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("K")) {
                final float valueAmplifier = 0.2f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("P")) {
                final float valueAmplifier = -0.1f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("W")) {
                final float valueAmplifier = 0.05f;
                amplifyRace = valueAmplifier;
            }
        }

        if (namePlayer.equals("R")) {
            if (player.namePlayer.equals("R")) {
                final float valueAmplifier = -0.1f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("K")) {
                final float valueAmplifier = -0.2f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("P")) {
                final float valueAmplifier = 0.2f;
                amplifyRace = valueAmplifier;
            }

            if (player.namePlayer.equals("W")) {
                final float valueAmplifier = 0.25f;
                amplifyRace = valueAmplifier;
            }
        }

        return amplifyRace;
    }

}
