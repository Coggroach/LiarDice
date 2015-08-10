package com.coggroach.liardice.dice;

public interface IDice extends Cloneable
{
	public int getValue();
	public void roll();
	public boolean hasRolled();
	public IDice copy() throws Exception;
}
