package com.object.mentor.cleancode.chapter2;

public class MeaningfulContext {
	private void printGuessStatistics(char candidate, int count){
		String number;
		String verb;
		String plurailModifier;
		
		if(count == 0){
			number = "no";
			verb = "are";
			plurailModifier = "s";
		}else if(count == 1){
			number = "1";
			verb = "is";
			plurailModifier = "";
		}else {
			number = Integer.toString(count);
			verb = "are";
			plurailModifier = "s";
		}
		
		String guessMessage = String.format("There %s %s %s%s", verb, number, candidate, plurailModifier);
		print(guessMessage);
	}
	
	private void printGuessStatistics_short(char candidate, int count){
		String number;
		String verb;
		String plurailModifier;
		
		number = (count == 0? "no" : Integer.toString(count));
		verb = (count == 1? "is" : "are");
		plurailModifier = (count == 1? "" : "s");
		
		String guessMessage = String.format("There %s %s %s%s", verb, number, candidate, plurailModifier);
		print(guessMessage);
	}

	private void print(String message) {
		System.out.println(message);
	}
	
	private void printGuessStatistics_contextful(char candidate, int count) {
		String guessMessage = new GuessStatisticMessage().make(candidate, count);
		print(guessMessage);
	}
	
	public class GuessStatisticMessage{
		private String number;
		private String verb;
		private String plurailModifier;
		
		public String make(char candidate, int count) {
			createPluralDependentMessageParts(count);
			return String.format("There %s %s %s%s", verb, number, candidate, plurailModifier);
		}

		private void createPluralDependentMessageParts(int count) {
			if(count == 0) {
				thereAreNoLetters();
			}else if(count == 1) {
				thereIsOneLetter();
			}else {
				thereAreManyLetters(count);
			}
		}
		
		private void thereAreNoLetters() {
			number = "no";
			verb = "are";
			plurailModifier = "s";
		}

		private void thereIsOneLetter() {
			number = "1";
			verb = "is";
			plurailModifier = "";
		}

		private void thereAreManyLetters(int count) {
			number = Integer.toString(count);
			verb = "are";
			plurailModifier = "s";
		}
	}
}
