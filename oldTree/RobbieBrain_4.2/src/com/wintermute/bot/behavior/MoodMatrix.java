/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wintermute.bot.behavior;

/**
 *
 * @author Matthew_G_Stemen
 */
public class MoodMatrix implements MoodInterface {

	int moodIndex = 0;
	int moodWindow = 0;
	int moodPotentialMax = -1;
	int moodPotentialMin = -1;
	Mood myMood = MoodInterface.Mood.Indifferent;

	public MoodMatrix() {
		// init the min and max mood
		moodPotentialMin = 0;
		moodPotentialMax = myMood.getHappyPotenital();
		moodPotentialMax += myMood.getIndifferentPotential();
		moodPotentialMax += myMood.getSadPotential();
		moodPotentialMax += myMood.getAngryPotential();
		moodPotentialMax += myMood.getMadPotential();
	}
	// make the over all mood "window" more dark
	public void moodDarker()
	{

	}

	public void moodLighter()
	{

	}

    @Override
    public void moreHappy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void lessHappy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mood getCurrentMood() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
