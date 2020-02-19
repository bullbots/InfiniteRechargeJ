package frc.robot.util;

import edu.wpi.first.wpilibj.Solenoid;

public class Shifter {

    private Solenoid lowGearSolenoid;
    private Solenoid highGearSolenoid;

    public enum Gear {
        LOW,
        HIGH
    }

    private Gear currentGear;

    public Shifter(int lowGearChannel, int highGearChannel) {
        lowGearSolenoid = new Solenoid(lowGearChannel);
        highGearSolenoid = new Solenoid(highGearChannel);

        setGear(Gear.LOW);
    }

    public void shiftHigh(){
        setGear(Gear.HIGH);
    }

    public void shiftLow(){
        setGear(Gear.LOW);
    }
    
    private void setGear(Gear gear){
        currentGear = gear;

        switch (gear) {
            case HIGH:
                lowGearSolenoid.set(false);
                highGearSolenoid.set(true);
                break;
            case LOW:
                lowGearSolenoid.set(true);
                highGearSolenoid.set(false);
        }
    }

    public Gear getGear(){
        return currentGear;
    }
}