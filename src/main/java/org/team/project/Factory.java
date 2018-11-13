package org.team.project;

public class Factory {
	public Characters makeCharacter(int n) {
		Characters newCharacter = null;
		if(n==1) {
			newCharacter = new Wizard();
		} else if(n==2) {
			newCharacter = new Warrior();
		}
		
		return newCharacter;
	}
}