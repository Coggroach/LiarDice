package com.coggroach.liardice.dice;

import java.util.Random;

public class Dice
{
    private int value;
    
    public Dice()
    {
        this.value = 0;
    }
    
    public int getValue()
    {
        return this.value;
    }
    
    public void roll()
    {
        this.value = new Random().nextInt(6) + 1;
    }
    
    public boolean hasRolled()
    {
        return this.value != 0;
    }
}
