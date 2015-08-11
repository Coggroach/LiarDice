package com.coggroach.liardice.player;

import java.util.EventObject;

public class PlayerEvent extends EventObject
{
	private static final long serialVersionUID = 1L;

	public PlayerEvent(IPlayer source)
	{
		super(source);		
	}
	
	public int getPlayerId()
	{
		return ((IPlayer)this.source).getId();
	}
}
