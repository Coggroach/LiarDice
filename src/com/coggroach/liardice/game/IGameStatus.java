package com.coggroach.liardice.game;

public interface IGameStatus
{
	void onStart() throws Exception;
	void onMove() throws Exception;
	void onDeclare() throws Exception;
	void onRoll() throws Exception;
	void onBet() throws Exception;
	void onStop() throws Exception;
	void onRestart() throws Exception;
	void onEnd() throws Exception;
	void onWait() throws Exception;
}
