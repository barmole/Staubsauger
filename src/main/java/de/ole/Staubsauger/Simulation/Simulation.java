package de.ole.Staubsauger.Simulation;

public class Simulation {
    private double posX;
    private double posY;
    private double rotation = 0;

    public Simulation(double x, double y) {
        this.posX = x;
        this.posY = y;
    }

    public void berechnePosition(){
        if(!stehtVorWand()){

        }
    }

    private boolean stehtVorWand() {
        return false;
    }
}
