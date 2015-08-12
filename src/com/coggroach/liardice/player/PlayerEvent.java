package com.coggroach.liardice.player;

public interface PlayerEvent
{
	public void onPlayerDeclare(Object sender, PlayerEventArgs o);
	public void onPlayerRoll(Object sender, PlayerEventArgs o);
	public void onPlayerBet(Object sender, PlayerEventArgs o);	
}
