package de.ole.Staubsauger.Simulation;

public class Roboter {
    double posX, posY, rotation;
    Status status = Status.IDLE;

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getRotation() {
        return rotation;
    }

    public Roboter(double posX, double posY, double rotation) {
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;
    }



    public void berechne(RaumManager manager) {
        if (status == Status.FAHREN) {
            if(kollision(manager)){
                status = Status.IDLE;
            }else{
                posY++;
            }

        }

    }
    public void setStatus(Status status) {
        this.status = status;
    }

    boolean kollision(RaumManager manager){
        for (Hinderniss hinderniss : manager.getHindernisse()) {
            if (hinderniss.getPosX() < posX + 20 &&
                    hinderniss.getPosX() + hinderniss.getBreite() >  posX &&
                    hinderniss.getPosY() <  posY + 20 &&
                    hinderniss.getPosY() + hinderniss.getHoehe() > posY) {
                return true;
            }
        }
        return false;
    }
}
