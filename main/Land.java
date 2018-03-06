package main;

public class Land {
    protected Player player1;

    protected Player player2;

    protected int row;

    protected int column;

    protected String typeLand = null;

    protected Land(final String type) {

        typeLand = type;

    }

    protected Land() { }

    protected Land(final Player player, final Land land) {

        if (land.player1 == null) {

            if (player instanceof Wizard) {
                land.player1 = new Wizard();
            }

            if (player instanceof Rogue) {
                land.player1 = new Rogue();
            }

            if (player instanceof Pyromancer) {
                land.player1 = new Pyromancer();
            }

            if (player instanceof Knight) {
                land.player1 = new Knight();
            }

        } else {

            if (player instanceof Wizard) {
                land.player2 = new Wizard();
            }

            if (player instanceof Rogue) {
                land.player2 = new Rogue();
            }

            if (player instanceof Pyromancer) {
                land.player2 = new Pyromancer();
            }

            if (player instanceof Knight) {
                land.player2 = new Knight();
            }

        }

    }
}
