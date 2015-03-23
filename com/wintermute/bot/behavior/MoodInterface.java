package com.wintermute.bot.behavior;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matthew_G_Stemen
 */
public interface MoodInterface {

	public enum MoodWeight {

		Never,
		Seldom,
		Sometimes,
		Often,
		Mostly,
		Always;
	}

	public enum Mood {

		Happy,
		Indifferent,
		Sad,
		Angry,
		Mad;
		static Mood myMood;

		// a composite of a particular mood, and the likelyhood (weight) of that mood occuring
		class MoodComp {

			Mood myMood = Mood.Indifferent;
			MoodWeight myWeight = MoodWeight.Sometimes;

			public MoodComp(Mood moodInit, MoodWeight moodWeightInit) {
				myMood = moodInit;
				myWeight = moodWeightInit;
			}

			public void setMoodWeight(MoodWeight newMoodWeight) {
				this.myWeight = newMoodWeight;
			}
		} // end Mood Comp class

		class MoodArray {

			MoodComp happyMood = null;
			MoodComp indifferentMood = null;
			MoodComp sadMood = null;
			MoodComp madMood = null;
			MoodComp angryMood = null;

			public MoodArray() {
				happyMood = new MoodComp(Mood.Happy, MoodWeight.Sometimes);
				indifferentMood = new MoodComp(Mood.Indifferent, MoodWeight.Sometimes);
				sadMood = new MoodComp(Mood.Sad, MoodWeight.Sometimes);
				madMood = new MoodComp(Mood.Mad, MoodWeight.Sometimes);
				angryMood = new MoodComp(Mood.Angry, MoodWeight.Sometimes);

			}

			public Mood getMoodForIndex(int index)
			{
				Mood newMood = Mood.Indifferent;


				return newMood;
			}

			public int getHappyWeight() {
				return happyMood.myWeight.ordinal();
			}

			public int getIndifferentWeight() {
				return indifferentMood.myWeight.ordinal();
			}

			public int getSadWeight() {
				return sadMood.myWeight.ordinal();
			}

			public int getAnrgyWeight() {
				return angryMood.myWeight.ordinal();
			}

			public int getMadWeight() {
				return madMood.myWeight.ordinal();
			}
		} // Mood Array class
		MoodArray myMoodArray = null;

		Mood() {
			myMoodArray = new MoodArray();
		}

		public static Mood getMood(MoodWeight moodWeight) {
			Mood newMood = Indifferent;
			int factor = moodWeight.ordinal();
			Mood currentMood = myMood;

			return newMood;
		}

		public int getHappyPotenital() {
			return myMoodArray.getHappyWeight();
		}

		public int getIndifferentPotential() {
			return myMoodArray.getIndifferentWeight();
		}

		public int getSadPotential() {
			return myMoodArray.getSadWeight();
		}

		public int getAngryPotential() {
			return myMoodArray.getAnrgyWeight();
		}

		public int getMadPotential() {
			return myMoodArray.getMadWeight();
		}
	} // end Mood enum


	// Interface Methods
	public void moreHappy();

	public void lessHappy();

	public Mood getCurrentMood();
}
